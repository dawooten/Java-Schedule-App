package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This is the Customers model class that defines access to this data.
 * */
public class Customers {

    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;

    /**
     * Customers constructor.
     *  @param customerID Integer customerID
     *  @param customerName String name of customer
     *  @param address String of customer's address
     *  @param postalCode String of customer's postal code
     *  @param phone String of customer's phone number
     *  @param createDate LocalDateTime when customer was created
     *  @param createdBy String of who created customer
     *  @param lastUpdate Timestamp when customer was last updated
     *  @param lastUpdatedBy String who last updated customer
     *  @param divisionID Integer divisionID
     *  */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone,
                     LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy,
                     int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
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

    /**
     * getCustomerName getter. Returns customerName.
     * @return String customerName.
     * */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * setCustomerName setter. Sets customerName.
     * @param customerName String customerName.
     * */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * getAddress getter. Returns address.
     * @return String address.
     * */
    public String getAddress() {
        return address;
    }

    /**
     * setAddress setter. Sets address.
     * @param address String address.
     * */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getPostalCode getter. Returns postalCode.
     * @return String postalCode.
     * */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * setPostalCode setter. Sets postalCode.
     * @param postalCode String postalCode.
     * */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * getPhone getter. Returns phone.
     * @return String phone.
     * */
    public String getPhone() {
        return phone;
    }

    /**
     * setPhone setter. Sets phone.
     * @param phone String phone.
     * */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getCreateDate getter. Returns createDate.
     * @return LocalDateTime createDate.
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
     * @return String createdBy.
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
     * @return Timestamp lastUpdate.
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
     * getDivisionID getter. Returns divisionID.
     * @return Integer divisionID
     * */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * setDivisionID setter. Sets divisionID.
     * @param divisionID Integer divisionID.
     * */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
