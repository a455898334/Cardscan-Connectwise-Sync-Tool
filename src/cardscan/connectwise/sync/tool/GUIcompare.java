/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardscan.connectwise.sync.tool;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JLabel;

/**
 *
 * @author Peter
 */
public class GUIcompare extends JFrame {

    public GUIcompare() {
        setTitle("Review");
        setVisible(true);
        setResizable(false);
        setSize(200, 700);
        createReview("Peter", "Jasko", "23423423423", "23423423434", "asdfasd@gmail.com",
                "safasdf@gmail.com", "asfsd", "sfsdg", "sdgdfg", "sfsdf", "dsfsf", "dgdfgdfg", "sdfgfdg", "sfsdf", "sdfsdf", "sfdsf", "sdfsdf","sf","sdfs","sdfsd","sdfs");
        createReview("Peter", "Jasko", "23423423423", "23423423434", "asdfasd@gmail.com",
                "safasdf@gmail.com", "asfsd", "sfsdg", "sdgdfg", "sfsdf", "dsfsf", "dgdfgdfg", "sdfgfdg", "sfsdf", "sdfsdf", "sfdsf", "sdfsdf","sf","sdfs","sdfsd","sdfs");
       
        createNavigationArrows();
        setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    public void createReview(String fname, String lname, String company, String oldphone,
            String newphone,String oldfax,String newfax, String oldemail, String newemail, String oldtitle,
            String newtitle, String oldstreetline, String newstreetline,
            String oldcity, String newcity, String oldstate, String newstate,
            String oldcountry, String newcountry,String oldzip,String newzip) {
        JLabel names = new JLabel("Name: " + fname + " " + lname);
        JLabel companyl = new JLabel("Company Name: " + company);
        JLabel oldphonel = new JLabel("Old Phone: " + oldphone);
        JLabel newphonel = new JLabel("New Phone: " + newphone);
        JLabel oldfaxl = new JLabel("Old Fax: " + oldfax);
        JLabel newfaxl = new JLabel("New Fax: " + newfax);
        JLabel oldemaill = new JLabel("Old Email: " + oldemail);
        JLabel newemaill = new JLabel("New Emai: " + newemail);
        JLabel oldtitlel = new JLabel("Old Title: " + oldtitle);
        JLabel newtitlel = new JLabel("New Title: " + newtitle);
        JLabel oldstreetlinel = new JLabel("Old Streetline: " + oldstreetline);
        JLabel newstreetlinel = new JLabel("New Streetline: " + newstreetline);
        JLabel oldcityl = new JLabel("Old City: " + oldcity);
        JLabel newcityl = new JLabel("New City: " + newcity);
        JLabel oldstatel = new JLabel("Old Country: " + oldcountry);
        JLabel newstatel = new JLabel("New State: " + newcountry);
        JLabel oldzipl = new JLabel("Old Zip: " + oldzip);
        JLabel newzipl = new JLabel("New Zip: " + newzip);
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
        add(oldzipl);
        add(newzipl);
        add(space);




    }

    public void createNavigationArrows() {
        JButton next = new JButton(">");
        JButton back = new JButton("<");
        add(back);
        add(next);
    }
}
