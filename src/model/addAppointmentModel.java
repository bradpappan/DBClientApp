package model;

public class addAppointmentModel {
    private String contactName;
    private int contactId;

    public addAppointmentModel (String contactName, int contactId) {
        this.contactName = contactName;
        this.contactId = contactId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String toString() {
        return ((contactName) + ". " + contactId);
    }
}
