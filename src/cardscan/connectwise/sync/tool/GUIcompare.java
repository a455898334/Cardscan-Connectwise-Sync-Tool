/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardscan.connectwise.sync.tool;

import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Peter
 */
public class GUIcompare extends JFrame {

    int page = 0;
    JLabel pagel;
    JLabel Syncstatus, names, companyl, oldphonel, newphonel, oldfaxl, newfaxl, newemaill, oldemaill, oldtitlel, newtitlel,
            oldstreetlinel, newstreetlinel, oldcityl, newcityl, oldstatel, newstatel, oldcountryl, newcountryl, oldzipl, newzipl;
    ArrayList<Contact> updatelist = new ArrayList<Contact>();
    CWContactModifier modify = new CWContactModifier();
    Sync syncer;
    Checkbox saftey;

    public GUIcompare() throws ParserConfigurationException, Exception {

        setLayout(new FlowLayout(FlowLayout.LEADING));
        syncer = new Sync();
        syncer.generateUpdateList();
        saftey = new Checkbox("Click this before clicking sync to sync your contacts.");
        saftey.setState(false);
        updatelist = syncer.getUpdateList();
        System.out.println("SIZE:" + updatelist.size());
        setTitle("Review of Upgrades to Current Contacts");
        setVisible(true);
        setResizable(true);
        setSize(500, 200);
        pack();

        //  setSize(550,210);

        createReview(updatelist.get(page).getFname(), updatelist.get(page).getLname(), updatelist.get(page).getCompany(),
                updatelist.get(page).getPhone(), updatelist.get(page).getFax(), updatelist.get(page).getEmail(),
                updatelist.get(page).getTitle(), updatelist.get(page).getStreetline(),
                updatelist.get(page).getCity(), updatelist.get(page).getState(),
                updatelist.get(page).getCountry(), updatelist.get(page).getZip(), page);


        createNavigationArrows();
        createChangeSyncStatusButtons();
        add(pagel = new JLabel("Page " + (page + 1) + "of " + updatelist.size()));
        add(saftey);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createReview(String fname, String lname, String company,
            String newphone, String newfax, String newemail,
            String newtitle, String newstreetline,
            String newcity, String newstate,
            String newcountry, String newzip, int index) throws ParserConfigurationException, Exception {



        names = new JLabel("Name: " + fname + " " + lname);
        companyl = new JLabel("Company Name: " + company);
        oldphonel = new JLabel("Old Phone: " + modify.getPhone(fname, lname));
        newphonel = new JLabel("New Phone: " + newphone);
        oldfaxl = new JLabel("Old Fax: " + modify.getFax(fname, lname));
        newfaxl = new JLabel("New Fax: " + newfax);
        newemaill = new JLabel("New Email: " + newemail);
        oldemaill = new JLabel("Old Email: " + modify.getEmail(fname, lname));
        oldtitlel = new JLabel("Old Title: " + modify.getTitle(fname, lname));
        newtitlel = new JLabel("New Title: " + newtitle);
        oldstreetlinel = new JLabel("Old Streetline: " + modify.getAddressLine(fname, lname));
        newstreetlinel = new JLabel("New Streetline: " + newstreetline);
        oldcityl = new JLabel("Old City: " + modify.getCity(fname, lname));
        newcityl = new JLabel("New City: " + newcity);
        oldstatel = new JLabel("Old State: " + modify.getState(fname, lname));
        newstatel = new JLabel("New State: " + newstate);
        oldcountryl = new JLabel("Old Country: " + modify.getCountry(fname, lname));
        newcountryl = new JLabel("New Country: " + newcountry);
        oldzipl = new JLabel("Old Zip: " + modify.getZip(fname, lname));
        newzipl = new JLabel("New Zip: " + newzip);
        Syncstatus = new JLabel("Sync:" + updatelist.get(index).getSyncStatus());
//  
        JLabel space = new JLabel("______________________");
        add(names);
        add(oldphonel);
        add(newphonel);
        add(oldemaill);
        add(newemaill);
        add(oldtitlel);
        add(newtitlel);
        add(oldstreetlinel);
        add(newstreetlinel);
        add(oldcityl);
        add(newcityl);
        add(oldstatel);
        add(newstatel);
        add(oldcountryl);
        add(newcountryl);
        add(oldzipl);
        add(newzipl);
        add(Syncstatus);
        //add(space);

        setSize(550, 210);



    }

    public void createNavigationArrows() {
        JButton next = new JButton(">");
        JButton back = new JButton("<");
        add(back);
        add(next);
        setSize(551, 211);

        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (page < updatelist.size() - 1) {
                    page++;
                }
                try {


                    Syncstatus.setText(String.valueOf("Sync:" + updatelist.get(page).getSyncStatus()));
                    names.setText("Name: " + updatelist.get(page).getFname() + " " + updatelist.get(page).getLname());
                    companyl.setText("Company: " + updatelist.get(page).getCompany());
                    oldphonel.setText("Old Phone: " + modify.getPhone(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newphonel.setText("New Phone: " + updatelist.get(page).getPhone());
                    oldfaxl.setText("Old Fax: " + modify.getFax(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newfaxl.setText("New Fax:" + updatelist.get(page).getFax());
                    newemaill.setText("New Email: " + updatelist.get(page).getEmail());
                    oldemaill.setText("Old Email: " + modify.getEmail(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    oldtitlel.setText("Old Title: " + modify.getEmail(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newtitlel.setText("New Title :" + updatelist.get(page).getTitle());
                    oldstreetlinel.setText("Old Street Line :" + modify.getAddressLine(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newstreetlinel.setText("New Street Line :" + updatelist.get(page).getStreetline());
                    oldcityl.setText("Old City: " + modify.getCity(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newcityl.setText("New City :" + updatelist.get(page).getCity());
                    oldstatel.setText("Old State :" + modify.getState(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newstatel.setText("New State :" + updatelist.get(page).getState());
                    oldcountryl.setText("Old Country : " + modify.getCountry(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newcountryl.setText("New Country :" + updatelist.get(page).getCountry());
                    oldzipl.setText("Old Zip" + modify.getZip(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newzipl.setText("New Zip :" + updatelist.get(page).getZip());
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(GUIcompare.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(GUIcompare.class.getName()).log(Level.SEVERE, null, ex);
                }
                pagel.setText(("Page " + (page + 1) + "of " + updatelist.size()));
            }
        });
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (page > 0) {
                        page--;
                    }
                    Syncstatus.setText("Sync: " + String.valueOf(updatelist.get(page).getSyncStatus()));
                    names.setText("Name: " + updatelist.get(page).getFname() + " " + updatelist.get(page).getLname());
                    companyl.setText("Company: " + updatelist.get(page).getCompany());
                    oldphonel.setText("Old Phone: " + modify.getPhone(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newphonel.setText("New Phone: " + updatelist.get(page).getPhone());
                    oldfaxl.setText("Old Fax: " + modify.getFax(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newfaxl.setText("New Fax:" + updatelist.get(page).getFax());
                    newemaill.setText("New Email: " + updatelist.get(page).getEmail());
                    oldemaill.setText("Old Email: " + modify.getEmail(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    oldtitlel.setText("Old Title: " + modify.getEmail(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newtitlel.setText("New Title :" + updatelist.get(page).getTitle());
                    oldstreetlinel.setText("Old Street Line :" + modify.getAddressLine(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newstreetlinel.setText("New Street Line :" + updatelist.get(page).getStreetline());
                    oldcityl.setText("Old City: " + modify.getCity(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newcityl.setText("New City :" + updatelist.get(page).getCity());
                    oldstatel.setText("Old State :" + modify.getState(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newstatel.setText("New State :" + updatelist.get(page).getState());
                    oldcountryl.setText("Old Country : " + modify.getCountry(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newcountryl.setText("New Country :" + updatelist.get(page).getCountry());
                    oldzipl.setText("Old Zip" + modify.getZip(updatelist.get(page).getFname(), updatelist.get(page).getLname()));
                    newzipl.setText("New Zip :" + updatelist.get(page).getZip());
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(GUIcompare.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(GUIcompare.class.getName()).log(Level.SEVERE, null, ex);
                }
                pagel.setText(("Page " + (page + 1) + "of " + updatelist.size()));

            }
        });
    }

    public void createChangeSyncStatusButtons() {
        JButton b1 = new JButton("Set this contact to sync.");
        JButton b2 = new JButton("Don't Sync this contact.");
        JButton b3 = new JButton("Sync Contacts");


        add(b1);
        add(b2);
        add(b3);

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                updatelist.get(page).setSync(true);
                Syncstatus.setText("Sync : " + String.valueOf(updatelist.get(page).getSyncStatus()));

            }
        });
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                updatelist.get(page).setSync(false);
                Syncstatus.setText("Sync: " + String.valueOf(updatelist.get(page).getSyncStatus()));
            }
        });
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    for (int i = 0; i < updatelist.size(); i++) {
                        syncer.contacts.add(updatelist.get(i));

                    }
                    if (saftey.getState()) {
                        syncer.syncContacts();
                    }
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(GUIcompare.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(GUIcompare.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });

    }
}
