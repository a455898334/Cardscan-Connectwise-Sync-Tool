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
    ArrayList<Contact> updatelist = new ArrayList<Contact>();
    CWContactModifier Fabian = new CWContactModifier();

    public Sync() throws ParserConfigurationException, Exception {
        LoadCardscanContacts loader = new LoadCardscanContacts();
        loader.parseDocument();
        contacts = loader.getContacts();
    }

    public void syncContacts() throws ParserConfigurationException, Exception {
        for (int index = 0; index < contacts.size(); index++) {
            String fname = contacts.get(index).getFname();
            String lname = contacts.get(index).getLname();
            if (contacts.get(index).getSyncStatus()) {

                if (Fabian.contactExists(fname, lname)) {
                    String zipcw = Fabian.getZip(fname, lname);
                    String zipcs = contacts.get(index).getZip();

                    if (zipcw.equals(zipcs)) {
                    } else {
                    }
                    Fabian.setAddress(fname, lname, contacts.get(index).getStreetline(),
                            contacts.get(index).getCity(), contacts.get(index).getState(), zipcs, contacts.get(index).getCountry());
                    Fabian.setField(fname, lname, "Phone", contacts.get(index).getPhone());
                    Fabian.setField(fname, lname, "Company", contacts.get(index).getCompany());
                    Fabian.setField(fname, lname, "Fax", contacts.get(index).getFax());
                    Fabian.setField(fname, lname, "Title", contacts.get(index).getTitle());
                    Fabian.setField(fname, lname, "Email", contacts.get(index).getEmail());
                } else {
                    Fabian.createContact(fname, lname, contacts.get(index).getTitle(), contacts.get(index).getEmail(), contacts.get(index).getPhone(), contacts.get(index).getFax(), contacts.get(index).getStreetline(), contacts.get(index).getCity(), contacts.get(index).getState(), contacts.get(index).getZip(), "", contacts.get(index).getCompany());
                }
                Fabian.addContactNotes(fname, lname, contacts.get(index).getNotes());
            }
        }

    }

    public void generateUpdateList() throws ParserConfigurationException, Exception {
          for (int index = 0; index < contacts.size(); index++) {
            String fname = contacts.get(index).getFname();
            String lname = contacts.get(index).getLname();
           

                if (Fabian.contactExists(fname, lname)) {
                    updatelist.add(contacts.get(index));
                    contacts.remove(index);
                 

    }
        
    }

    }
public ArrayList getUpdateList(){
    return updatelist;
}
}