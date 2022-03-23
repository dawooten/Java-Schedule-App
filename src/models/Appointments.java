package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This is the Appointments model class that defines access to this data.
 * */
public class Appointments {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * Appointments constructor.
     *  @param appointmentID Integer appointmentID
     *  @param title String title of appointment
     *  @param description String description of appointment
     *  @param location String location of appointment
     *  @param type String type of appointment
     *  @param start LocalDateTime start of appointment
     *  @param end, LocalDateTime end of appointment
     *  @param createDate LocalDateTime of appointment
     *  @param createdBy String who created the appointment
     *  @param lastUpdate Timestamp of lastUpdate of appointment
     *  @param lastUpdatedBy String of who lastUpdated this appointment
     *  @param customerID Integer customerID
     *  @param contactID Integer contactID
     *  @param userID Integer userID
     *  */
    public Appointments(int appointmentID, String title, String description, String location, String type,
                        LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy,
                        Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * getAppointmentID getter. Returns appointmentID.
     * @return Integer appointmentID
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
     * @return String title
     * */
    public String getTitle() {
        return title;
    }

    /**
     * setTitle setter. Sets title.
     * @param title String title.
     * */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getDescription getter. Returns description.
     * @return String description
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
     * getLocation getter. Returns location.
     * @return String location
     * */
    public String getLocation() {
        return location;
    }

    /**
     * setLocation setter. Sets location.
     * @param location String location.
     * */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * getType getter. Returns type.
     * @return String type
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
     * getStart getter. Returns start.
     * @return LocalDateTime start
     * */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * setStart setter. Sets start.
     * @param start LocalDateTime start.
     * */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * getEnd getter. Returns end.
     * @return LocalDateTime end
     * */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * setEnd setter. Sets end.
     * @param end LocalDateTime end.
     * */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * getCreateDate getter. Returns createDate.
     * @return LocalDateTime createDate
     * */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * setCreateDate setter. Sets createDate.
     * @param createDate LocalDateTime createDate.
     * */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * getCreatedBy getter. Returns createdBy.
     * @return String createdBy
     * */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * setCreatedBy setter. Sets createdBy.
     * @param createdBy String createdBy.
     * */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * getLastUpdate getter. Returns lastUpdate.
     * @return Timestamp lastUpdate
     * */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    /**
     * setLastUpdate setter. Sets lastUpdate.
     * @param lastUpdate Timestamp lastUpdate.
     * */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * getLastUpdatedBy getter. Returns lastUpdatedBy.
     * @return String lastUpdatedBy
     * */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * setLastUpdatedBy setter. Sets lastUpdatedBy.
     * @param lastUpdatedBy String lastUpdatedBy.
     * */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * getCustomerID getter. Returns customerID.
     * @return Integer customerID
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

    /**
     * getUserID getter. Returns userID.
     * @return Integer userID
     * */
    public int getUserID() {
        return userID;
    }
    /**
     * setUserID setter. Sets userID.
     * @param userID Integer userID.
     * */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * getContactID getter. Returns contactID.
     * @return Integer contactID
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
}
