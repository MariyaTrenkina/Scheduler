
package classes;

/**
 *Customer Class
 * @author Mariya.Trenkina
 */
public class Customer {
// Customer's address

    /**
     *Address
     */
    public String address;
    
    // Unique ID

    /**
     *Customer ID
     */
    public int customerId;
    
    // Customer name

    /**
     *Customer Name
     */
    public String customerName;
    
    /**
     *Postal Code
     */
    public String postalCode;
    
    /**
     *Phone Number
     */
    public String phoneNumber;

    /**
     *State
     */
    public State state;

    /**
     *Country
     */
    public Country country;
    
    /**
     *Division ID
     */
    public int divisionID;

    /**
     *Customer Constructor
     * @param address address
     * @param customerId customer Id
     * @param customerName customer Name
     * @param postalCode postal Code
     * @param phoneNumber phone Number
     * @param state state
     * @param country country
     */
    public Customer(String address, int customerId, String customerName, String postalCode, String phoneNumber, State state,Country country) {
        this.address = address; 
        this.customerId = customerId;
        this.customerName = customerName;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.country = country;
    }

    /**
     * get Address
     * @return get Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * set Address
     * @param address set Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get Customer Id
     * @return get Customer Id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * set Customer Id
     * @param customerId set Customer Id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * get Customer Name
     * @return get Customer Name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * set Customer Name
     * @param customerName set Customer Name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * get Postal Code
     * @return get Postal Code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * set Postal Code
     * @param postalCode set Postal Code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * get Phone Number
     * @return get Phone Number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * set Phone Number
     * @param phoneNumber set Phone Number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * get State
     * @return get State
     */
    public State getState() {
        return state;
    }

    /**
     *set State
     * @param state set State
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * get Country
     * @return get Country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * set Country
     * @param country set Country
     */
    public void setCountry(Country country) {
        this.country = country;
    }
    
    
}
