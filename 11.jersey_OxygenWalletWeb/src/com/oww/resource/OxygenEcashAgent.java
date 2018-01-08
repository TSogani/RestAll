package com.oww.resource;

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
import javax.ws.rs.Path;
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

import com.oww.dto.AddMoney;
import com.oww.dto.FinalSattelment;
import com.oww.dto.User;
import com.oww.dto.Wallet;

@Path("/oxygen-ecash")
public class OxygenEcashAgent {
	private Map<Integer,Wallet> walletMap;
	private AtomicInteger autoWalletId;    //its thredsafe so we use AtomicInteger,bcoz in every request its thredsafe.
	
	public OxygenEcashAgent() {
		walletMap = new ConcurrentHashMap<Integer,Wallet>();
		autoWalletId = new AtomicInteger(1);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)            //It's not recommended to pass String value in mime type. It's recommended to pass mime enum type.
	public float getWalletBalance(@QueryParam("walletId")int walletId){
		Wallet wallet = null;
		wallet = walletMap.get(walletId);
		if(wallet!=null){
			return wallet.getBalance();
		}
		return 0.0f;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)    //In GET request don't have any request body so we never  use @Consumes annotation
	@Produces(MediaType.APPLICATION_XML)
	public StreamingOutput createWallet(InputStream in) throws SAXException, IOException, ParserConfigurationException{
		
		User user = null;
		Wallet wallet = null;
		int walletId = 0;
		
		user = buildUser(in);
		wallet = new Wallet();
		walletId = autoWalletId.getAndIncrement();
		
		wallet.setWalletId(walletId);
		wallet.setUser(user);
		wallet.setBalance(0.0f);
		wallet.setStatus("Active");
		
		walletMap.put(walletId, wallet);
		DataStreamingOutput dataStreamingOutput = new DataStreamingOutput(convertWallet(wallet));//callback machnism
		
		return dataStreamingOutput;
		
	}//end of createWallet
	
	private User buildUser(InputStream in) throws SAXException, IOException, ParserConfigurationException{
		User user = null;
		user = new User();
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		document = builder.parse(in);
		
		NodeList children = document.getFirstChild().getChildNodes(); //All nodes comes in NodeList bcoz DOM represent tree structure format.
		
		for(int i=0;i<children.getLength();i++){
			Node child = children.item(i);
			if(child.getNodeType()==Node.ELEMENT_NODE){
				if(child.getNodeName().equals("userName")){
					user.setUserName(child.getFirstChild().getNodeValue());     /*<userName>john</userName>  get firstchild means textnode and get nodevalue means value*/	
				}else if(child.getNodeName().equals("mobile")){                 
					user.setMobile(child.getFirstChild().getNodeValue());       /*we can use child.getTextContent insteadof child.getFirstChild().getNodeValue()*/
				}else if(child.getNodeName().equals("email")){
					user.setEmail(child.getFirstChild().getNodeValue());
				}
			}
		}
		
		return user;
	}//end of buildUser method
	
	private String convertWallet(Wallet wallet){
		
		/*how does the wallet look like
		 * <wallet>
		 * 		<walletId></walletId>
		 * 		<user>
		 * 			<userName>john</userName>
		 * 			<mobile>9090909090</mobile>
		 * 			<email>john@gmail.com</email>
		 * 		</user>
		 * 		<balance></balance>
		 * 		<status></status>
		 * </wallet>
		 * 
		 * 
		 **/ 
		
		
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		if(wallet!=null){
			buffer.append("<wallet>").append("<walletId>").append(wallet.getWalletId()).append("</walletId>").
			append("<user>").append("<userName>").append(wallet.getUser().getUserName()).append("</userName>").
			append("<mobile>").append(wallet.getUser().getMobile()).append("</mobile>").append("<email>").
			append(wallet.getUser().getEmail()).append("</email>").append("</user>").append("<balance>").
			append(wallet.getBalance()).append("</balance>").append("<status>").append(wallet.getStatus()).
			append("</status>").append("</wallet>");
			
			
		}
		return buffer.toString();
	}
	//inner class
	private final class DataStreamingOutput implements StreamingOutput{
		String dataInfo = null;
		
		public DataStreamingOutput(String dataInfo) {
			this.dataInfo = dataInfo;
		}
		
		@Override
		public void write(OutputStream out) throws IOException, WebApplicationException {
			PrintWriter writer = null;
			writer = new PrintWriter(out); //printWriter will write the data in outputStream so we pass outputStream here.
			writer.print(dataInfo); 
			//out.close(); //It is not recommnended to close it. (Doubt:-If we are closing then also then also working how? bcoz runtime will read one by one from output steaming and append to response object)
			writer.close();
		}
		
	}//end of WalletStreamingOutput class
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public StreamingOutput addMoney(InputStream in) throws ParserConfigurationException, SAXException, IOException{
		AddMoney addMoney = null;
		Wallet wallet = null;
		
		addMoney = buildAddMoney(in);
		
		wallet = walletMap.get(addMoney.getWalletId());
		if(wallet!=null){
			wallet.setBalance(wallet.getBalance()+addMoney.getAmount());
		}		
		DataStreamingOutput dataStreamingOutput = new DataStreamingOutput(convertWallet(wallet));
		return dataStreamingOutput;
	}//end of addMoney
	
	
	
	private AddMoney buildAddMoney(InputStream in) throws ParserConfigurationException, SAXException, IOException{
		AddMoney addMoney = null;
		addMoney = new AddMoney();
		/*xml will looks like
		 * 
		 * <addMoney>
		 * 		<walletId>3<walletId>
		 * 		<fromAccount>acc3</fromAccount>
		 * 		<amount>99.99</amount>
		 *	</addMoney>
		*/
		
		
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		document = builder.parse(in);
		
		NodeList children = document.getFirstChild().getChildNodes();
		
		for(int i=0;i<children.getLength();i++){
			Node child = children.item(i);
			if(child.getNodeType()==Node.ELEMENT_NODE){
				if(child.getNodeName().equals("walletId")){
					addMoney.setWalletId(Integer.parseInt(child.getFirstChild().getNodeValue()));
				}else if(child.getNodeName().equals("fromAccount")){
					addMoney.setFromAccount(child.getFirstChild().getNodeValue());
				}else if(child.getNodeName().equals("amount")){
					addMoney.setAmount(Float.parseFloat(child.getFirstChild().getNodeValue()));
				}
			}
		}
		return addMoney;
	}//end of buildAddMoney method
	
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public StreamingOutput deActivateAccount(@QueryParam("walletId")int walletId){
		Wallet wallet = null;
		FinalSattelment finalSattelment = null;
		
		
		
		wallet = walletMap.get(walletId);
		if(wallet!=null){
			wallet.setStatus("closed");       //we never remove account we only deactive account or set the status as closed
			finalSattelment = new FinalSattelment();
			finalSattelment.setWalletId(walletId);
			finalSattelment.setUserName(wallet.getUser().getUserName());
			finalSattelment.setBalance(wallet.getBalance());
			finalSattelment.setStatus("disghus");
		}
		
		return new DataStreamingOutput(convertFinalSattlement(finalSattelment));
	}//end of finalSattelement
	
	
	private String convertFinalSattlement(FinalSattelment finalSattelment){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		
		/*  This is how the finalsattelement looks like.     
			<finalSattelement>
				<walletId></walletId>
				<userName></userName>
				<balance></balance>
				<status></status>
			<finalsattelement>
		*/
		
		
		buffer.append("<finalSattelement>").append("<walletId>").append(finalSattelment.getWalletId()).append("</walletId>").
		append("<userName>").append(finalSattelment.getUserName()).append("</userName>").append("<balance>").
		append(finalSattelment.getBalance()).append("</balance>").append("<status>").append(finalSattelment.getStatus()).
		append("</status>").append("</finalSattelement>");
		
		return buffer.toString();
	}
	
}

