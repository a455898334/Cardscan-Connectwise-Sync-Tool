/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardscan.connectwise.sync.tool;

/**
 *
 * @author Peter
 */
public class Contact {
    String fname = "";
    String lname = ""; 
    String company = "";
    String phone = "";
    String fax = "";
    String email = "";
    String title = "";
    String streetline = "";
    String city = ""; 
    String state = "";
    String country = "";
    String zip = "";
    String type = "";
    String notes = "";
    String syncdate = "";
    Boolean Sync = true;
    
    public Contact(String firstname,String lastname){
        fname = firstname;
        lname = lastname;
    }
    
    public void setSync(Boolean syncs){
        Sync = syncs;
    }
    
    public boolean getSyncStatus(){
        return Sync;
    }
    
    public void setSyncDate(String date){
        syncdate = date;
    }
    public void setFname(String name){
        fname = name;
    }
    public void setLname(String name){
        lname = name;
    }
    public void setCompany(String newcompany){
        company = newcompany;
    }
    public void setPhone(String newphone){
       phone = newphone;
    }
    public void setFax(String newfax){
        fax = newfax;
    }
    public void setEmail(String newemail){
        email = newemail;
    }
    public void setTitle(String newtitle){
        title = newtitle;
    }
    public void setStreetline(String newline){
        streetline = newline;
    }
    public void setStreetLine(String newline){
        streetline = newline;
    }
    public void setCity(String newline){
        city = newline;
    }
    public void setState(String newline){
        state = newline;
    }
    public void setCountry(String newline){
        country = newline;
    }
    public void setZip(String newline){
        zip = newline;
    }
    public void setType(String newline){
        type = newline;
    }
    public void setNotes(String newline){
        notes = newline;
    }
  
    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
    public String getCompany(){
       return company ;
    }
    public String getPhone(){
       return phone;
    }
    public String getFax(){
        return fax;
    }
    public String getEmail(){
        return email;
    }
    public String getTitle(){
       return title;
    }
    public String getStreetline(){
       return streetline;
    }
    public String getCity(){
       return city;
    }
    public String getState(){
        return state;
    }
    public String getCountry(){
       return country;
    }
    public String getZip(){
       return  zip;
    }
    public String getType(){
        return type;
    }
    public String getNotes(){
       return notes;
    }
    
    
}
