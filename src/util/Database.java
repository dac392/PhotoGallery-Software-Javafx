package util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import main.User;

public class Database {
	private static User currentUser;
	private static Document doc;
	private static DocumentBuilder builder;
	private static Node people;
	
	
	public static void init() throws Exception{
		builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc = builder.parse(new File("src/util/users.xml"));
		doc.getDocumentElement().normalize();
		NodeList topElement = doc.getElementsByTagName("people");
	    people = topElement.item(0);	
	}
	
	public static ArrayList<String> getUsernames() {
		ArrayList<String> usernames = new ArrayList<>();
		NodeList users = people.getChildNodes();
		int totalUsers = users.getLength();
		for(int i = 0; i < totalUsers; i++) {
			Node p = users.item(i);
			NodeList pData = p.getChildNodes();
			if(p.getNodeType() == Node.ELEMENT_NODE) {
				Node username = pData.item(1);
				usernames.add(username.getTextContent());
			}

		}
		return usernames;
		
	}
	
	public static void addNewUser(String username_s) {
		Element newUser = doc.createElement("user");
		Element username = doc.createElement("username");
		username.appendChild(doc.createTextNode(username_s));
		Element albums = doc.createElement("albums");
		newUser.appendChild(username);
	    newUser.appendChild(albums);
	    people.appendChild(newUser);
	    writeToXML();
	}
	

	
	
	/*
	 * 
	 * 	Helper functions
	 * 
	 * */

	private static void restart() throws SAXException, IOException {
		doc = builder.parse(new File("src/util/users.xml"));
		doc.getDocumentElement().normalize();
		NodeList topElement = doc.getElementsByTagName("people");
	    people = topElement.item(0);	
	}
	private static void writeToXML() {
	    Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		    // initialize StreamResult with File object to save to file
		    StreamResult result = new StreamResult(new StringWriter());
		    DOMSource source = new DOMSource(doc);
		    transformer.transform(source, result);
		    String xmlString = result.getWriter().toString();
		    xmlString = xmlString.replaceAll("\n","");
//		    xmlString = xmlString.replaceAll("\t", );
			File fold = new File("src/util/users.xml");
			fold.delete();

			File fnew = new File("src/util/users.xml");
		    FileWriter f2 = new FileWriter(fnew, false);
		    f2.write(xmlString);
		    f2.close();
		    restart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 
	 * 		Ignore, will delete later
	 *
	 **/
	
	private static void getPeople() throws Exception {
	    NodeList list = doc.getElementsByTagName("user");
	    Node first = list.item(0);
	    NodeList list2 = doc.getElementsByTagName("people");
	    Node people = list2.item(0);
	    
	    
	    //new user
	    Element newUser = doc.createElement("user");
	    //username
	    Element username = doc.createElement("username");
	    username.appendChild(doc.createTextNode("User2"));
	    
	    //albums name, images
	    Element albums = doc.createElement("albums");
	    Element album = doc.createElement("album");
	    Element name = doc.createElement("name");
	    name.appendChild(doc.createTextNode("Album2"));
	    //images image-something1
	    Element images = doc.createElement("images");
	    Element img = doc.createElement("image");
	    img.appendChild(doc.createTextNode("something1.png"));
	    images.appendChild(img);
	    
	    album.appendChild(name);
	    album.appendChild(images);
	    albums.appendChild(album);
	    newUser.appendChild(username);
	    newUser.appendChild(albums);
	    people.appendChild(newUser);
	    
//	    NodeList nodeList = first.getChildNodes();
//	    int n = nodeList.getLength();
//	    Node current;
//	    for (int i=0; i<n; i++) {
//	        current = nodeList.item(i);
//	        if(current.getNodeType() == Node.ELEMENT_NODE) {
//	            System.out.println(
//	              current.getNodeName() + ": " + current.getTextContent());
//	        }
//	    }
	    printDom(doc);
	    saveDomToFile(doc, "src/util/users.xml");
//	    if(nodeList.getLength() == 1)
//	    	System.out.println("yes, you have the right amount of users");
		
	}
	
	private static void saveDomToFile(Document document,String fileName) throws Exception {
			 
			    DOMSource dom = new DOMSource(document);
			    Transformer transformer = TransformerFactory.newInstance()
			      .newTransformer();

			    StreamResult result = new StreamResult(new File(fileName));
			    transformer.transform(dom, result);
	}
	
	private static void printDom(Document document) throws Exception{
	    DOMSource dom = new DOMSource(document);
	    Transformer transformer = TransformerFactory.newInstance()
	        .newTransformer();

	    transformer.transform(dom, new StreamResult(System.out));
	}
	
	
	public static void print(String s) {
		System.out.println(s);
	}
}
