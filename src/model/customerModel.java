package model;

/**
 * This class holds the model for customer
 */
public class customerModel {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private String customerDivisionId;

    public customerModel(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, String customerDivisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivisionId = customerDivisionId;
    }

    /**
     *
     * @return gets the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId sets the customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return gets the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName sets the customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return gets the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *
     * @param customerAddress sets the customerAddress
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     *
     * @param customerPostalCode sets the customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     *
     * @return gets the customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     *
     * @return gets the customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     *
     * @param customerPhone sets the customerPhone
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     *
     * @return gets the customerDivisionId
     */
    public String getCustomerDivisionId() {
        return customerDivisionId;
    }

    /**
     *
     * @param customerDivisionId sets the customerDivisionId
     */
    public void setCustomerDivisionId(String customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }
}
