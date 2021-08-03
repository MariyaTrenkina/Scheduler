
package classes;

/**
 *Country Class
 * @author Mariya.Trenkina
 */
public class Country {
    int countryId;
    String country;

    /**
     *Country Constructor
     * @param countryId country Id
     * @param country country
     */
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * get Country Id
     * @return get Country Id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * set Country Id
     * @param countryId set Country Id
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * get Country
     * @return get Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * set Country
     * @param country set Country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString(){
    return(country);
    
    }
}
