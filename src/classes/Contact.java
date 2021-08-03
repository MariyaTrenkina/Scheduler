
package classes;

/**
 *Contact Class
 * @author Mariya.Trenkina
 */
public class Contact {
    int contactID;
    String contactName;
    String email;

    /**
     *Contact Constructor
     * @param contactID contact ID
     * @param contactName contact name
     * @param email email
     */
    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * get Contact ID
     * @return get Contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * set Contact ID
     * @param contactID set Contact ID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * get Contact Name
     * @return get Contact Name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * set Contact Name
     * @param contactName set Contact Name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * get Email
     * @return get Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set Email
     * @param email set Email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
    return(contactName);
    
    }
    
}
