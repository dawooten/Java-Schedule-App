package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This is the Countries model class that defines access to this data.
 * */
public class Countries {

    private int countryID;
    private String country;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    /**
     * Contacts constructor.
     *  @param countryID Integer countryID
     *  @param country String name of country
     *  @param createDate LocalDateTime when country was created
     *  @param createdBy String who created country
     *  @param lastUpdate Timestamp when country was last updated
     *  @param lastUpdatedBy String who last updated country
     *  */
    public Countries(int countryID, String country, LocalDateTime createDate, String createdBy,
                     Timestamp lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * getCountryID getter. Returns countryID.
     * @return Integer countryID
     * */
    public int getCountryID() {
        return countryID;
    }

    /**
     * setCountryID setter. Sets countryID.
     * @param countryID Integer countryID.
     * */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * getCountry getter. Returns country.
     * @return String country
     * */
    public String getCountry() {
        return country;
    }

    /**
     * setCountry setter. Sets country.
     * @param country String country.
     * */
    public void setCountry(String country) {
        this.country = country;
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
}
