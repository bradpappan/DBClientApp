package model;

/**
 * This class holds the model for add customer
 */
public class addCustomerModel {
    private int countryId;
    private String country;
    private int divisionId;
    private String division;


    public addCustomerModel (int countryId, String country, int divisionId, String division) {
        this.countryId = countryId;
        this.country = country;
        this.divisionId = divisionId;
        this.division = division;
    }

    /**
     *
     * @return gets countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     *
     * @param countryId sets countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     *
     * @return gets country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country sets country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return gets divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     *
     * @param division sets the division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     *
     * @return gets division
     */
    public String getDivision() {
        return division;
    }

    /**
     *
     * @param divisionId sets the divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     *
     * @return creates string of countryId and country
     */
    public String toString() {
        return (Integer.toString(countryId) + ". " + country);
    }
}
