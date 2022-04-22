package model;

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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivision() {
        return division;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String toString() {
        return (Integer.toString(countryId) + ". " + country);
    }
}
