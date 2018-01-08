package com.wwa.resource;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Path;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wwa.dto.User;
import com.wwa.dto.UserCard;
import com.wwa.dto.Wallet;

@Path("/oxygen-ecash-nextgen")
public class OxygenECashNextGenAgent extends AbstractOxygenECashAgent {

	@Override
	public StreamingOutput createWallet(InputStream in)
			throws ParserConfigurationException, SAXException, IOException {
		int walletID = 0;
		User user = null;
		Wallet wallet = null;
		DataStreamingOuput walletStreamingOutput;

		user = buildUser(in);
		walletID = autoWalletID.incrementAndGet();
		wallet = new Wallet();
		wallet.setWalletID(walletID);
		wallet.setUser(user);
		wallet.setBalance(0.0f);
		wallet.setStatus("Active");
		walletMap.put(walletID, wallet);

		walletStreamingOutput = new DataStreamingOuput(convertWallet(wallet));
		return walletStreamingOutput;
	}

	@Override
	protected User buildUser(InputStream in)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		UserCard user = null;

		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		doc = builder.parse(in);
		user = new UserCard();
		NodeList children = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				String nodeValue = child.getFirstChild().getNodeValue();
				if (child.getNodeName().equals("username")) {
					user.setUsername(nodeValue);
				} else if (child.getNodeName().equals("mobile")) {
					user.setMobile(nodeValue);
				} else if (child.getNodeName().equals("email")) {
					user.setEmail(nodeValue);
				} else if (child.getNodeName().equals("cardNo")) {
					user.setCardNo(nodeValue);
				}
			}
		}
		return user;
	}

	@Override
	protected String convertWallet(Wallet wallet) {
		String walletInfo = super.convertWallet(wallet);
		StringBuffer buffer = new StringBuffer();
		buffer.append(walletInfo.substring(0, walletInfo.indexOf("</wallet>")))
				.append("<cardNo>")
				.append(((UserCard) wallet.getUser()).getCardNo())
				.append("</cardNo>").append("</wallet>");
		return buffer.toString();
	}
}
