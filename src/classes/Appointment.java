
package classes;

/**
 *Appointment Class
 * @author Mariya.Trenkina
 */
public class Appointment {
    int appointmentID;
    String title;
    String description;
    String location;
    String contact;
    String type;
    String startDateTime;
    String endDateTime;
    int customerID;
    int userID;

    /**
     *Appointment Constructor
     * @param appointmentID appointment ID
     * @param title title
     * @param description description
     * @param location location
     * @param contact contact
     * @param type type
     * @param startDateTime start date and time
     * @param endDateTime end date and time
     * @param customerID customer ID
     * @param userID user ID
     */
    public Appointment(int appointmentID, String title, String description, String location, String contact, String type, String startDateTime, String endDateTime, int customerID, int userID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerID = customerID;
        this.userID = userID;
    }
     
    /**
     *get User ID
     * @return get User ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     *set User ID
     * @param userID set User ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    /**
     *get Appointment ID
     * @return get Appointment ID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     *set Appointment ID
     * @param appointmentID set Appointment ID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * get Title
     * @return get Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set Title
     * @param title set Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get Description
     * @return get Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set Description
     * @param description set Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get Location
     * @return get Location
     */
    public String getLocation() {
        return location;
    }

    /**
     * set Location
     * @param location set Location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * get Contact
     * @return get Contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * set Contact
     * @param contact set Contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * get Type
     * @return get Type
     */
    public String getType() {
        return type;
    }

    /**
     * set Type
     * @param type set Type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get StartDateTime
     * @return get StartDateTime
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     * set StartDateTime
     * @param startDateTime set StartDateTime
     */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * get EndDateTime
     * @return get EndDateTime
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     * set EndDateTime
     * @param endDateTime set EndDateTime
     */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * get CustomerID
     * @return get CustomerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * set CustomerID
     * @param customerID set CustomerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
      
}
