package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This is the FirstLevelDivisions model class that defines access to this data.
 * */
public class FirstLevelDivisions {

    private int divisionID;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /**
     * FirstLevelDivisions constructor.
     *  @param divisionID Integer divisionID
     *  @param division String name of division
     *  @param createDate LocalDateTime when division was created
     *  @param createdBy String of who created division
     *  @param lastUpdate Timestamp when division was last updated
     *  @param lastUpdatedBy String who last updated division
     *  @param countryID Integer countryID
     *  */
    public FirstLevelDivisions(int divisionID, String division, LocalDateTime createDate, String createdBy,
                               Timestamp lastUpdate, String lastUpdatedBy, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /**
     * getDivisionID getter. Returns divisionID.
     * @return Integer divisionID.
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

    /**
     * getDivision getter. Returns division.
     * @return String division.
     * */
    public String getDivision() {
        return division;
    }

    /**
     * setDivision setter. Sets division.
     * @param division String division.
     * */
    public void setDivision(String division) {
        this.division = division;
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
     * @return String lastUpdatedBy.
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
     * getCountryID getter. Returns countryID.
     * @return Integer countryID.
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
}
