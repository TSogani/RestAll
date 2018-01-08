package com.wwa.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wwa.dto.AddMoney;
import com.wwa.dto.FinalSettlement;
import com.wwa.dto.User;
import com.wwa.dto.Wallet;

abstract public class AbstractOxygenECashAgent {
	protected AtomicInteger autoWalletID;
	protected Map<Integer, Wallet> walletMap;

	public AbstractOxygenECashAgent() {
		autoWalletID = new AtomicInteger(1);
		walletMap = new ConcurrentHashMap<Integer, Wallet>();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public float getWalletBalance(@QueryParam("walletID") int walletID) {
		Wallet wallet = null;

		wallet = walletMap.get(walletID);
		if (wallet != null) {
			return wallet.getBalance();
		}
		return 0.0f;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public StreamingOutput addCash(InputStream in)
			throws ParserConfigurationException, SAXException, IOException {
		AddMoney addMoney = null;
		Wallet wallet = null;

		addMoney = buildAddMoney(in);
		wallet = walletMap.get(addMoney.getWalletID());
		wallet.setBalance(wallet.getBalance() + addMoney.getAmount());
		return new DataStreamingOuput(convertWallet(wallet));
	}

	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public StreamingOutput deactivateWallet(@QueryParam("walletID") int walletID) {
		Wallet wallet = null;
		FinalSettlement settlement = null;

		wallet = walletMap.get(walletID);
		if (wallet != null) {
			wallet.setStatus("closed");
			settlement = new FinalSettlement();
			settlement.setWalletID(walletID);
			settlement.setUsername(wallet.getUser().getUsername());
			settlement.setBalance(wallet.getBalance());
			settlement.setStatus("disbursed");
		}
		return new DataStreamingOuput(convertFinalSettlement(settlement));
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	abstract public StreamingOutput createWallet(InputStream in)
			throws ParserConfigurationException, SAXException, IOException;

	abstract protected User buildUser(InputStream in)
			throws ParserConfigurationException, SAXException, IOException;

	private AddMoney buildAddMoney(InputStream in)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		AddMoney addMoney = null;

		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		doc = builder.parse(in);
		addMoney = new AddMoney();
		NodeList children = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				String nodeValue = child.getFirstChild().getNodeValue();
				if (child.getNodeName().equals("walletID")) {
					addMoney.setWalletID(Integer.parseInt(nodeValue));
				} else if (child.getNodeName().equals("fromAccount")) {
					addMoney.setFromAccount(nodeValue);
				} else if (child.getNodeName().equals("amount")) {
					addMoney.setAmount(Float.parseFloat(nodeValue));
				}
			}
		}
		return addMoney;
	}

	protected String convertWallet(Wallet wallet) {
		StringBuffer buffer = null;

		buffer = new StringBuffer();
		if (wallet != null) {
			buffer.append("<wallet>").append("<walletID>")
					.append(wallet.getWalletID()).append("</walletID>")
					.append("<user>").append("<username>")
					.append(wallet.getUser().getUsername())
					.append("</username>").append("<mobile>")
					.append(wallet.getUser().getMobile()).append("</mobile>")
					.append("<email>").append(wallet.getUser().getEmail())
					.append("</email>").append("</user>").append("<balance>")
					.append(wallet.getBalance()).append("</balance>")
					.append("<status>").append(wallet.getStatus())
					.append("</status>").append("</wallet>");
		}

		return buffer.toString();
	}

	private String convertFinalSettlement(FinalSettlement settlement) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("<settlement>").append("<walletID>")
				.append(settlement.getWalletID()).append("</walletID>")
				.append("<username>").append(settlement.getUsername())
				.append("</username>").append("<balance>")
				.append(settlement.getBalance()).append("</balance>")
				.append("<status>").append(settlement.getStatus())
				.append("</status>").append("</settlement>");
		return buffer.toString();
	}

	protected final class DataStreamingOuput implements StreamingOutput {
		private String data;

		public DataStreamingOuput(String data) {
			this.data = data;
		}

		@Override
		public void write(OutputStream out) throws IOException,
				WebApplicationException {
			PrintWriter writer = new PrintWriter(out);
			writer.print(data);
			writer.close();
			out.close();
		}
	}

}
