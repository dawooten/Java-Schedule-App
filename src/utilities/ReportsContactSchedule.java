package utilities;

import java.sql.Timestamp;

/**
 * This is the ReportsContactSchedule model class that defines access to this data.
 * */
public class ReportsContactSchedule {
    private int contactID;
    private int appointmentID;
    private String title;
    private String type;
    private String description;
    private Timestamp start;
    private Timestamp end;
    private int customerID;

    /**
     * ReportsContactSchedule constructor.
     *  @param contactID Integer contactID
     *  @param appointmentID Integer appointmentID
     *  @param title String title of appointment
     *  @param type String type of appointment
     *  @param description String description of appointment
     *  @param start Timestamp when appointment started
     *  @param end Timestamp when appointment end
     *  @param customerID Integer customerID
     *  */
    public ReportsContactSchedule(int contactID, int appointmentID, String title, String type, String description, Timestamp start, Timestamp end, int customerID) {
        this.contactID = contactID;
        this.appointmentID = appointmentID;
        this.title = title;
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
    }

    /**
     * getContactID getter. Returns contactID.
     * @return Integer contactID.
     * */
    public int getContactID() {
        return contactID;
    }

    /**
     * setContactID setter. Sets contactID.
     * @param contactID Integer contactID.
     * */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * getAppointmentID getter. Returns appointmentID.
     * @return Integer appointmentID.
     * */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * setAppointmentID setter. Sets appointmentID.
     * @param appointmentID Integer appointmentID.
     * */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * getTitle getter. Returns title.
     * @return String title.
     * */
    public String getTitle() {
        return title;
    }

    /**
     * setTitle setter. Sets appointmentID.
     * @param title String title.
     * */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getType getter. Returns type.
     * @return String type.
     * */
    public String getType() {
        return type;
    }

    /**
     * setType setter. Sets type.
     * @param type String type.
     * */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * getDescription getter. Returns description.
     * @return String description.
     * */
    public String getDescription() {
        return description;
    }

    /**
     * setDescription setter. Sets description.
     * @param description String description.
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getStart getter. Returns start.
     * @return Timestamp start.
     * */
    public Timestamp getStart() {
        return start;
    }

    /**
     * setStart setter. Sets start.
     * @param start Timestamp start.
     * */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * getEnd getter. Returns end.
     * @return Timestamp end.
     * */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * setEnd setter. Sets end.
     * @param end Timestamp end.
     * */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * getCustomerID getter. Returns customerID.
     * @return Integer customerID.
     * */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * setCustomerID setter. Sets customerID.
     * @param customerID Integer customerID.
     * */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}

