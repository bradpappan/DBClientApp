package model;

import java.sql.Timestamp;

/**
 * This class holds the model for appointments
 */
public class appointmentModel {

    private String appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private Timestamp appointmentStart;
    private Timestamp appointmentEnd;
    private int appointmentCustomerId;
    private int appointmentUserId;
    private String appointmentContactId;

    public appointmentModel(String appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType,
                            Timestamp appointmentStart, Timestamp appointmentEnd, int appointmentCustomerId, int appointmentUserId, String appointmentContactId) {

        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.appointmentCustomerId = appointmentCustomerId;
        this.appointmentUserId = appointmentUserId;
        this.appointmentContactId = appointmentContactId;
    }

    /**
     *
     * @return gets the appointmentId
     */
    public String getAppointmentId() {
        return appointmentId;
    }

    /**
     *
     * @param appointmentId sets the appointmentsId
     */
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     *
     * @return get appointmentTitle
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     *
     * @param appointmentTitle sets the appointmentTitle
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     *
     * @param appointmentDescription sets the appointmentDescription
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     *
     * @return gets the appointmentDescription
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     *
     * @return gets the appointmentLocation
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     *
     * @param appointmentLocation set appointmentLocation
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     *
     * @return gets appointmentType
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     *
     * @param appointmentType sets appointmentType
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     *
     * @return gets the appointmentStart
     */
    public Timestamp getAppointmentStart() {
        return appointmentStart;
    }

    /**
     *
     * @param appointmentStart sets the appointmentStart
     */
    public void setAppointmentStart(Timestamp appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    /**
     *
     * @return gets the appointmentEnd
     */
    public Timestamp getAppointmentEnd() {
        return appointmentEnd;
    }

    /**
     *
     * @param appointmentEnd sets the appointmentEnd
     */
    public void setAppointmentEnd(Timestamp appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     *
     * @return gets the appointmentCustomerId
     */
    public int getAppointmentCustomerId() {
        return appointmentCustomerId;
    }

    /**
     *
     * @param appointmentCustomerId sets appointmentCustomerId
     */
    public void setAppointmentCustomerId(int appointmentCustomerId) {
        this.appointmentCustomerId = appointmentCustomerId;
    }

    /**
     *
     * @return gets the appointmentUserId
     */
    public int getAppointmentUserId() {
        return appointmentUserId;
    }

    /**
     *
     * @param appointmentUserId sets appointmentUserId
     */
    public void setAppointmentUserId(int appointmentUserId) {
        this.appointmentUserId = appointmentUserId;
    }

    /**
     *
     * @return gets the appointmentContactId
     */
    public String getAppointmentContactId() {
        return appointmentContactId;
    }

    /**
     *
     * @param appointmentContactId sets the appointmentContactId
     */
    public void setAppointmentContactId(String appointmentContactId) {
        this.appointmentContactId = appointmentContactId;
    }
}
