
package classes;

/**
 *State Class
 * @author Mariya.Trenkina
 */
public class State { 
    int divisionId;
    String division;
    int countryId;

    /**
     *State Constructor
     * @param divisionId division Id
     * @param division division
     * @param countryId country Id
     */
    public State(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * get Division Id
     * @return get Division Id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * set Division Id
     * @param divisionId set Division Id
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     *get Division
     * @return get Division
     */
    public String getDivision() {
        return division;
    }

    /**
     * set Division
     * @param division set Division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     *get Country Id
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
       @Override
    public String toString(){
    return(division);
    
    }
}
