/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardscan.connectwise.sync.tool;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Peter
 */
public class Sync {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        CWContactModifier Fabian = new CWContactModifier();

    
    public Sync() throws ParserConfigurationException, Exception{
        LoadCardscanContacts loader = new LoadCardscanContacts();
        loader.parseDocument();
        contacts = loader.getContacts();
    }
    
    public void syncContacts() throws ParserConfigurationException, Exception{
        for(int index = 0;index < contacts.size();index++){
            String fname = contacts.get(index).getFname();
            String lname = contacts.get(index).getLname();
            if(Fabian.contactExists(fname, lname)){
               String zipcw = Fabian.getZip(fname, lname);
               String zipcs = contacts.get(index).getZip();
               
               if (zipcw.equals(zipcs))
               {}
               else{}
               Fabian.setAddress(fname, lname, contacts.get(index).getStreetline(), 
                       contacts.get(index).getCity(),contacts.get(index).getState(),zipcs, contacts.get(index).getCountry());
                
            }
                
        }
    }
    
}
