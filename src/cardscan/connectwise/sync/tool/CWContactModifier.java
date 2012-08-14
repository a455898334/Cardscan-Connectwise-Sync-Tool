/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardscan.connectwise.sync.tool;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import javax.xml.parsers.DocumentBuilder;

/**
 *
 * @author Peter
 */
public class CWContactModifier {

    static String xmlcode = "";
    static String xmlcode2 = "";
    protected static final Map<String, String> config = UTIL.map(
            "site", "brainlink.myconnectwise.net",
            "app_root", "v4_6_release",
            "company", "Brainlink",
            "username", "rgoel", //Insert Username
            "password", "raj");   //Insert Password


    public void createContact( String type, String gender,
            String firstName, String lastName, String title, String email,
            String phone, String fax, String streetLine, String city, String state,
            String zip, String personalAddress, String company) throws ParserConfigurationException, Exception {

        Element request, result, contact;
        boolean needGod = companyExists(company);
        if (!needGod){
        createCompany(company);}
        request = newRequest("UpdatePartnerContactAction", config);
        contact = UTIL.createElement(request, "Contact");
        UTIL.createElement(contact, "Id", "0");
        UTIL.createElement(contact, "CompanyId", company);
       // UTIL.createElement(contact, "Relationship", relationship);
        UTIL.createElement(contact, "Type", type);
        UTIL.createElement(contact, "Title", title);
        UTIL.createElement(contact, "Phone", phone);
        UTIL.createElement(contact, "Fax", fax);
        UTIL.createElement(contact, "Email", email);
        UTIL.createElement(contact, "FirstName", firstName);
        UTIL.createElement(contact, "LastName", lastName);
        UTIL.createElement(contact, "Gender", gender);
        UTIL.createElement(contact, "City", city);
        UTIL.createElement(contact, "State", state);
        UTIL.createElement(contact, "Zip", zip);
    //    UTIL.createElement(contact, "School", school);
        request.appendChild(contact);
        result = sendRequest(config, request.getOwnerDocument());
    }

   public void createCompany(String companyName) throws ParserConfigurationException{
        Element request, result, company;
        request = newRequest("UpdatePartnerCompanyAction", config);
        company = UTIL.createElement(request, "Company");
        UTIL.createElement(company, "Id", "0");
        UTIL.createElement(company, "CompanyID", companyName);
        UTIL.createElement(company, "CompanyName", companyName);
        request.appendChild(company);
        UTIL.printElement(request);
        result = sendRequest(config, request.getOwnerDocument());
   }
   public void createCompany(String companyName, String phone,String streetline,String city,String state,String zip, String country,String cphone) throws ParserConfigurationException{
        Element request, result, company, address,companyaddress,streetlines;
        request = newRequest("UpdatePartnerCompanyAction", config);
        company = UTIL.createElement(request, "Company");
        UTIL.createElement(company, "Id", "0");
        UTIL.createElement(company, "CompanyID", companyName);
        UTIL.createElement(company, "CompanyName", companyName);
        UTIL.createElement(company,"Phone", cphone);
        address =  UTIL.createElement(company,"Addresses");
        companyaddress = UTIL.createElement(address,"CompanyAddress");
        UTIL.createElement(companyaddress, "Id", "0");
        streetlines = UTIL.createElement(companyaddress,"StreetLines");
        UTIL.createElement(streetlines ,"string",streetline);
        UTIL.createElement(companyaddress,"City",city);
        UTIL.createElement(companyaddress,"State",state);
        UTIL.createElement(companyaddress,"Zip",zip);
        UTIL.createElement(companyaddress,"Zip",zip);
        UTIL.createElement(companyaddress,"Country",country);
        request.appendChild(company);
        result = sendRequest(config, request.getOwnerDocument());
           }
   
   public void addContactNotes(String firstName,String lastName,String note) throws ParserConfigurationException, Exception{
        Element request, result, notes;
        request = newRequest("UpdatePartnerContactNoteAction", config);
        UTIL.createElement(request,"ContactId",getID(firstName,lastName));
        notes = UTIL.createElement(request,"Note");
        UTIL.createElement(notes,"Id","0");
        UTIL.createElement(notes,"NoteType","Comment");
        UTIL.createElement(notes,"NoteText",note);
        request.appendChild(notes);
        result = sendRequest(config, request.getOwnerDocument());

}
   
   public void addDeletionNote(String firstName,String lastName) throws ParserConfigurationException, Exception{
       Calendar cal = Calendar.getInstance();
       addContactNotes(firstName,lastName,"DELETEME" + cal.get(Calendar.YEAR)+ cal.get(Calendar.MONTH)+ cal.get(Calendar.DATE));
    
}
   public String getPhone(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String phone = parseDocument(firstName, lastName, "Phone");
        xmlcode = "";
        return phone;
    }
    public String getCompanyName(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String name =  parseDocument(firstName, lastName, "CompanyName");
        xmlcode = "";
        return name;
    }
    public String getCompanyRecId(String firstName, String lastName) throws ParserConfigurationException, Exception {
       String companyrecid =  parseDocument(firstName, lastName, "CompanyRecID");
        xmlcode = "";
                return companyrecid;
    }
    public String getEmail(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String email =  parseDocument(firstName, lastName, "Email");
        xmlcode = "";
        return email;
    }
    public String getAddressLine(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String line = parseDocument(firstName, lastName, "AddressLine1");
        xmlcode = "";
        return line;}
    public String getZip(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String line = parseDocument(firstName, lastName, "Zip");
        xmlcode = "";
        return line;}
    public String getCity(String firstName, String lastName) throws ParserConfigurationException, Exception {
 String city =  parseDocument(firstName,lastName,"City");
 xmlcode = "";
 return city;}
    public String getState(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String state =  parseDocument(firstName, lastName, "State");
        xmlcode = "";
                return state;
    }
    public String getCountry(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String country =  parseDocument(firstName, lastName, "Country");
  xmlcode = "";
  return country;
    }
    public String getID(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String id =  parseDocument(firstName, lastName, "Id");
        xmlcode = "";
        return id;}
    public String getGender(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String gender = parseDocument2(firstName, lastName, "Gender");
        xmlcode2 = "";
        return gender;
        
    }
    public String getCompanyID(String firstName, String lastName) throws ParserConfigurationException, Exception {
        String companyid =  parseDocument2(firstName, lastName, "CompanyId");
        xmlcode2 = "";
        return companyid;
    }
    public void setField(String firstName, String lastName, String field,String newContent) throws ParserConfigurationException, Exception {
        Element request, result, contact;
        String id = getID(firstName, lastName);
        String companyid = getCompanyID(firstName,lastName);
        String gender = getGender(firstName,lastName);
        request = newRequest("UpdatePartnerContactAction", config);
        contact = UTIL.createElement(request, "Contact");
        UTIL.createElement(contact, "Id", id);
        UTIL.createElement(contact, "CompanyId", companyid);
        UTIL.createElement(contact, field, newContent);
        UTIL.createElement(contact,"FirstName",firstName);
        UTIL.createElement(contact,"LastName",lastName);
        request.appendChild(contact);
        result = sendRequest(config, request.getOwnerDocument());
}
    public void setAddress(String firstName, String lastName,String newline,String city,String state,String zip,String country) throws ParserConfigurationException, Exception {
        Element request, result, contact, personaladdress,streetlines;
        String id = getID(firstName, lastName);
        String companyid = getCompanyID(firstName,lastName);
        String gender = getGender(firstName,lastName);
        request = newRequest("UpdatePartnerContactAction", config);
        contact = UTIL.createElement(request, "Contact");
        UTIL.createElement(contact, "Id", id);
        UTIL.createElement(contact, "CompanyId", companyid);
        UTIL.createElement(contact, "Gender", gender);
        UTIL.createElement(contact,"FirstName",firstName);
        UTIL.createElement(contact,"LastName",lastName);
       personaladdress =  UTIL.createElement(contact,"PersonalAddress");
       streetlines = UTIL.createElement(personaladdress,"StreetLines");
        UTIL.createElement(streetlines ,"string",newline);
        UTIL.createElement(personaladdress,"City",city);
        UTIL.createElement(personaladdress,"State",state);
        UTIL.createElement(personaladdress,"Zip",zip);
        UTIL.createElement(personaladdress,"Zip",zip);
        UTIL.createElement(personaladdress,"Country",country);
        request.appendChild(contact);
        result = sendRequest(config, request.getOwnerDocument());}
    public void setCity(String firstName, String lastName,String newCity) throws ParserConfigurationException, Exception {
        Element request, result, contact, personaladdress,streetlines;
        String id = getID(firstName, lastName);
        String companyid = getCompanyID(firstName,lastName);
        String gender = getGender(firstName,lastName);
        request = newRequest("UpdatePartnerContactAction", config);
        contact = UTIL.createElement(request, "Contact");
        UTIL.createElement(contact, "Id", id);
        UTIL.createElement(contact, "CompanyId", companyid);
        UTIL.createElement(contact, "Gender", gender);
        UTIL.createElement(contact,"FirstName",firstName);
        UTIL.createElement(contact,"LastName",lastName);
       personaladdress =  UTIL.createElement(contact,"PersonalAddress");
       UTIL.createElement(personaladdress,"City",newCity);
        request.appendChild(contact);
        result = sendRequest(config, request.getOwnerDocument());
}
    public String findContact(String firstName, String lastName) throws ParserConfigurationException, Exception {
        Element request, result, contact;
        request = newRequest("FindPartnerContactsAction", config);
        String condstring = "FirstName = " + "\"" + firstName + "\"" + " and " + "LastName = " + "\"" + lastName + "\"";
        UTIL.createElement(request, "Conditions", condstring);
        result = sendRequest(config, request.getOwnerDocument());
        UTIL.recordElement(result, 0, 10);
        String xml = xmlcode;
        xmlcode = "";
        return xml;
    }
    public String getContact(String firstName, String lastName) throws ParserConfigurationException, Exception {
        Element request, result, contact;
        request = newRequest("GetPartnerContactAction", config);
        String id = getID(firstName, lastName);
        UTIL.createElement(request, "ContactId", id);
        result = sendRequest(config, request.getOwnerDocument());
        UTIL.recordElement2(result, 0, 10);
        return xmlcode2;
    }
    
    public String findCompany(String cname) throws ParserConfigurationException{
        Element request, result, company;
        request = newRequest("FindPartnerCompaniesAction", config);
        String condstring = "CompanyName = " + "\"" + cname + "\"";
        UTIL.createElement(request, "Conditions", condstring);
        result = sendRequest(config, request.getOwnerDocument());
        UTIL.recordElement(result, 0, 10);
        String xml = xmlcode;
        xmlcode = "";
        return xml;}
    
    public boolean companyExists(String cname) throws ParserConfigurationException, Exception{
    if (parseDocument3(cname,"CompanyRecID") == "")
    return false;
    else
    return true; }
   
    public boolean contactExists(String firstname,String lastname) throws ParserConfigurationException, Exception{
    if (parseDocument(firstname,lastname,"Contacts") == "")
    return false;
    else
    return true; }
    
    public static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
    public String parseDocument(String firstName, String lastName, String tagName) throws ParserConfigurationException, Exception {
        String data = "";
        try {
            Document doc = loadXMLFromString(findContact(firstName, lastName));
            NodeList nList = doc.getElementsByTagName("Contact");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    data = getTagValue(tagName, eElement);
                }
            }
            
            return data;
           
        } catch (Exception e) {
            e.printStackTrace();
        }
      
                return data;

    }
    //used for get contact
    public String parseDocument2(String firstName, String lastName, String tagName) throws ParserConfigurationException, Exception {
        String data = "";
        try {

            Document doc = loadXMLFromString(getContact(firstName, lastName));
            NodeList nList = doc.getElementsByTagName("Contact");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    data = getTagValue2(tagName, eElement);


                }

            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }
    public String parseDocument3(String cname, String tagName) throws ParserConfigurationException, Exception {
        String data = "";
        try {

            Document doc = loadXMLFromString(findCompany(cname));
            NodeList nList = doc.getElementsByTagName("Companies");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    data = getTagValue2(tagName, eElement);


                }

            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }
    private static String getTagValue(String sTag, Element eElement) {
try{        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();}
catch(Exception e){
return "??";    
}


    }
    private static String getTagValue2(String sTag, Element eElement) {
        try{
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();}
        catch(Exception e){
            return "";
        }
    }
    protected static final String SERVICE_PATH = "/services/system_io/integration_io/processClientAction.rails";
    protected static final String CHARSET = "UTF-8";
    protected static final String XML_ENCODING = "UTF-8";

    public static Element sendRequest(Map<String, String> settings, Document doc) {
        try {

            URL url = new URL("https://"
                    + settings.get("site") + "/"
                    + settings.get("app_root")
                    + SERVICE_PATH);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", CHARSET);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Language", "en-US");
            conn.setUseCaches(false);
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                wr.append("actionString=");

                UTIL.saveToWriter(wr, doc);
                wr.flush();
            }

            if (conn.getResponseCode() != 200) {
                InputStream is = new GZIPInputStream(conn.getErrorStream());
                try (BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }
                    //Bad debugging code to show the error on the console - you really want to
                    System.out.println(response.toString());
                }
            } else {
                InputStream is = new BufferedInputStream(conn.getInputStream());
                InputSource inputSource = new InputSource(is);
                inputSource.setEncoding(XML_ENCODING);

                Element retValue = UTIL.loadStream(inputSource);
                UTIL.removeWhitespaceNodes(retValue);

                is.close();

                return retValue;
            }

        } catch (IOException ex) {
            Logger.getLogger(CWContactModifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static Element newRequest(String requestType, Map<String, String> settings) throws ParserConfigurationException {
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        d.setXmlStandalone(true);
        Element e = d.createElement(requestType);

        e.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        e.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");

        UTIL.createElement(e, "CompanyName", settings.get("company"));
        UTIL.createElement(e, "IntegrationLoginId", settings.get("username"));
        UTIL.createElement(e, "IntegrationPassword", settings.get("password"));

        d.appendChild(e);

        return e;
    }

    public static class UTIL {

        public static void saveToWriter(Writer writer, Document root) {
            {
                if (writer == null) {
                    return;
                }
                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    StreamResult result = new StreamResult(writer);
                    DOMSource source = new DOMSource(root);
                    transformer.transform(source, result);

                } catch (TransformerException ex) {
                    //     Logger.getLogger(org.gppa.common.UTIL.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                }
            }
        }

        public static Element loadStream(InputSource is) {

            Document doc = null;
            try {
                DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                doc = newInstance.newDocumentBuilder().parse(is);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            NodeList node = doc.getChildNodes();
            for (int i = 0; i < node.getLength(); ++i) {
                if (node.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element root = (Element) node.item(i);
                    root.normalize();
                    return root;
                }
            }
            System.err.println("Unable to locate root node");
            return null;

        }

        public static void removeWhitespaceNodes(Element e) {
            NodeList children = e.getChildNodes();
            for (int i = children.getLength() - 1; i >= 0; i--) {
                Node child = children.item(i);
                if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
                    e.removeChild(child);
                } else if (child instanceof Element) {
                    removeWhitespaceNodes((Element) child);
                }
            }
        }

        public static Element getChildByTag(Element e, String tag) {
            if (e == null) {
                return null;
            }
            Node node = e.getFirstChild();
            while (node != null) {
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element ch = (Element) node;
                    if (ch.getTagName().equals(tag)) {
                        return ch;
                    }
                }
                node = node.getNextSibling();
            }
            return null;
        }

        public static Element createElement(Element parent, String name, String content) {
            Element e = createElement(parent, name);
            if (e != null) {
                e.setTextContent(content);
            }
            return e;
        }

        public static Element createElement(Element parent, String name) {
            try {
                if (parent == null) {
                    parent = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument().createElement("root");
                }
                Document d = parent.getOwnerDocument();
                Element e = d.createElement(name);
                parent.appendChild(e);
                return e;
            } catch (Exception e) {
            }
            return null;
        }

        public static Map<String, String> map(String... values) {
            Map<String, String> m = new HashMap<String, String>();
            for (int i = 0; i + 1 < values.length; i += 2) {
                m.put(values[i], values[i + 1]);
            }
            return m;
        }

        public static void printElement(Node node) {
            printElement(node, 0, 10);
        }

        public static boolean printElement(Node node, int depth, int max) {
            if (node == null) {
                System.out.println("Null");
                return false;
            }
            if (node instanceof Element) {
                System.out.print("\n");
                for (int i = 0; i < depth; i++) {
                    System.out.print("\t");
                }
                Element element = (Element) node;
                NamedNodeMap attr = element.getAttributes();
                System.out.print("<");
                System.out.print(element.getTagName());
                if (attr != null) {
                    for (int i = 0; i < attr.getLength(); ++i) {
                        Node a = attr.item(i);
                        System.out.print(" " + a.getNodeName() + "=\"" + a.getTextContent() + "\"");
                    }
                }
                System.out.print(">");
                boolean lineBreak = false;
                if (max != depth) {
                    NodeList nl = element.getChildNodes();
                    for (int i = 0; i < nl.getLength(); i++) {
                        lineBreak = printElement(nl.item(i), depth + 1, max) || lineBreak;
                    }
                    if (lineBreak) {
                        System.out.println();
                    }
                }
                if (lineBreak) {
                    for (int i = 0; i < depth; i++) {
                        System.out.print("\t");
                    }
                }
                System.out.print("</");
                System.out.print(element.getTagName());
                System.out.print(">");
                if (lineBreak) {
                    System.out.println();
                }
                return true;
            } else {
                System.out.print(node.getNodeValue());
            }
            return false;
        }

        public static boolean recordElement(Node node, int depth, int max) {
            if (node == null) {
                xmlcode = xmlcode + "Null";
                return false;
            }
            if (node instanceof Element) {
                xmlcode = xmlcode + ("\n");
                for (int i = 0; i < depth; i++) {
                    xmlcode = xmlcode + ("\t");
                }
                Element element = (Element) node;
                NamedNodeMap attr = element.getAttributes();
                xmlcode = xmlcode + ("<");
                xmlcode = xmlcode + (element.getTagName());
                if (attr != null) {
                    for (int i = 0; i < attr.getLength(); ++i) {
                        Node a = attr.item(i);
                        xmlcode = xmlcode + (" " + a.getNodeName() + "=\"" + a.getTextContent() + "\"");
                    }
                }
                xmlcode = xmlcode + (">");
                boolean lineBreak = false;
                if (max != depth) {
                    NodeList nl = element.getChildNodes();
                    for (int i = 0; i < nl.getLength(); i++) {
                        lineBreak = recordElement(nl.item(i), depth + 1, max) || lineBreak;
                    }
                    if (lineBreak) {
                        xmlcode = xmlcode;
                    }
                }
                if (lineBreak) {
                    for (int i = 0; i < depth; i++) {
                        xmlcode = xmlcode;
                    }
                }
                xmlcode = xmlcode + ("</");
                xmlcode = xmlcode + (element.getTagName());
                xmlcode = xmlcode + (">");
                if (lineBreak) {
                    xmlcode = xmlcode;
                }
                return true;
            } else {
                xmlcode = xmlcode + (node.getNodeValue());
            }
            return false;
        }

        public static boolean recordElement2(Node node, int depth, int max) {
            if (node == null) {
                xmlcode2 = xmlcode2 + "Null";
                return false;
            }
            if (node instanceof Element) {
                xmlcode2 = xmlcode2 + ("\n");
                for (int i = 0; i < depth; i++) {
                    xmlcode2 = xmlcode2 + ("\t");
                }
                Element element = (Element) node;
                NamedNodeMap attr = element.getAttributes();
                xmlcode2 = xmlcode2 + ("<");
                xmlcode2 = xmlcode2 + (element.getTagName());
                if (attr != null) {
                    for (int i = 0; i < attr.getLength(); ++i) {
                        Node a = attr.item(i);
                        xmlcode2 = xmlcode2 + (" " + a.getNodeName() + "=\"" + a.getTextContent() + "\"");
                    }
                }
                xmlcode2 = xmlcode2 + (">");
                boolean lineBreak = false;
                if (max != depth) {
                    NodeList nl = element.getChildNodes();
                    for (int i = 0; i < nl.getLength(); i++) {
                        lineBreak = recordElement2(nl.item(i), depth + 1, max) || lineBreak;
                    }
                    if (lineBreak) {
                        xmlcode2 = xmlcode2;
                    }
                }
                if (lineBreak) {
                    for (int i = 0; i < depth; i++) {
                        xmlcode2 = xmlcode2;
                    }
                }
                xmlcode2 = xmlcode2 + ("</");
                xmlcode2 = xmlcode2 + (element.getTagName());
                xmlcode2 = xmlcode2 + (">");
                if (lineBreak) {
                    xmlcode2 = xmlcode2;
                }
                return true;
            } else {
                xmlcode2 = xmlcode2 + (node.getNodeValue());
            }
            return false;
        }
    }
}
