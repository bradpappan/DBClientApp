package model;

/**
 * This class is the model for add appointment
 */
public class addAppointmentModel {
    private String contactName;
    private int contactId;

    public addAppointmentModel (String contactName, int contactId) {
        this.contactName = contactName;
        this.contactId = contactId;
    }

    /**
     *
     * @return gets the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     *
     * @param contactId sets contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     *
     * @return gets contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName sets contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @return creates string of contactName and contactId
     */
    public String toString() {
        return ((contactName) + ". " + contactId);
    }
}
