package model;

import java.util.Date;

public class appointmentModel {

    private String appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private Date appointmentStart;
    private Date appointmentEnd;
    private int appointmentCustomerId;
    private int appointmentUserId;
    private int appointmentContactId;

    public appointmentModel(String appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType,
                            Date appointmentStart, Date appointmentEnd, int appointmentCustomerId, int appointmentUserId, int appointmentContactId) {

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

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Date getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(Date appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public Date getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(Date appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public int getAppointmentCustomerId() {
        return appointmentCustomerId;
    }

    public void setAppointmentCustomerId(int appointmentCustomerId) {
        this.appointmentCustomerId = appointmentCustomerId;
    }

    public int getAppointmentUserId() {
        return appointmentUserId;
    }

    public void setAppointmentUserId(int appointmentUserId) {
        this.appointmentUserId = appointmentUserId;
    }

    public int getAppointmentContactId() {
        return appointmentContactId;
    }

    public void setAppointmentContactId(int appointmentContactId) {
        this.appointmentContactId = appointmentContactId;
    }
}
