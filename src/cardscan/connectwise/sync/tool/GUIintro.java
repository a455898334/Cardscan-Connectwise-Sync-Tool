/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardscan.connectwise.sync.tool;

/**
 *
 * @author Peter
 */

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.xml.parsers.ParserConfigurationException;

public class GUIintro extends JFrame {
        JLabel path;
       static String pwd = "";
    
    public GUIintro() throws ParserConfigurationException, Exception{
        setTitle("Cardscan Connectwise Sync Tool");
    //    JFrame f = new JFrame("Basic GUI"); 
        
        setVisible(true);
    //    setSize(500,300);
    //    path = new JLabel("Enter the path to your cardscan file below.");
      //  path.setVerticalTextPosition(1);
        
        setLayout(new FlowLayout(FlowLayout.LEADING));
        pwd = JOptionPane.showInputDialog(null,"Enter the exact path(eg:C:/Users/Peter/Desktop/test.xml) to your xml generated in the previous program below.");
        if (pwd == null) {
        System.exit(0);
        }
        else{
        GUIcompare review = new GUIcompare();

        
        
       
    }}
        public static String getPath(){
            return pwd;
        }
}
 

