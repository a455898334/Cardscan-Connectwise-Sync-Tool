/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardscan.connectwise.sync.tool;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Peter
 */
public class LoadCardscanContacts {
    String csPath;
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    
    
    public LoadCardscanContacts(){
        csPath = GUIintro.getPath();
    }
    
    public void parseDocument() throws ParserConfigurationException, Exception {
        String data = "";
        String newContact;
        String firstname;
        String lastname;
        try {
            File fXmlFile = new File(csPath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("Contact");
            
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                     firstname = getTagValue("First_Name", eElement);
                     lastname = getTagValue("Last_Name", eElement);
                     contacts.add(new Contact(firstname,lastname));
                     contacts.get(temp).setCity(getTagValue("City", eElement));
                     contacts.get(temp).setCompany(getTagValue("Company_Name", eElement));
                     contacts.get(temp).setCountry(getTagValue("Country", eElement));
                     contacts.get(temp).setEmail(getTagValue("Email", eElement));
                     contacts.get(temp).setFax(getTagValue("Fax", eElement));
                     contacts.get(temp).setNotes(getTagValue("Notes", eElement));
                     contacts.get(temp).setPhone(getTagValue("Phone", eElement));
                     contacts.get(temp).setState(getTagValue("State", eElement));
                     contacts.get(temp).setStreetLine(getTagValue("Address_Line_1", eElement));
                     contacts.get(temp).setTitle(getTagValue("Title", eElement));
                     contacts.get(temp).setZip(getTagValue("Zip_code",eElement));
                     contacts.get(temp).setSyncDate(getTagValue("Creation_Date",eElement));
                     
                }
            }
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
      

    }
    
    private static String getTagValue(String sTag, Element eElement) {
	try{
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
         Node nValue = (Node) nlList.item(0);
 
	return nValue.getNodeValue();
        }
 catch(Exception e){
     return "??";
 }
       
  }
    
    public ArrayList getContacts(){
        return contacts;
    }
 
  
}
