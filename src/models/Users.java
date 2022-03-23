package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This is the Users model class that defines access to this data.
 * */
public class Users {

    private int userID;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    /**
     * Users constructor.
     *  @param userID Integer userID
     *  @param userName String name of user
     *  @param password String password of user
     *  @param createDate LocalDateTime when user was created
     *  @param createdBy String of who created user
     *  @param lastUpdate Timestamp when user was last updated
     *  @param lastUpdatedBy String who last updated user
     *  */
    public Users(int userID, String userName, String password, LocalDateTime createDate, String createdBy,
                 Timestamp lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * getUserID getter. Returns userID.
     * @return Integer userID.
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
     * getUserName getter. Returns userName.
     * @return String userName.
     * */
    public String getUserName() {
        return userName;
    }

    /**
     * setUserName setter. Sets userName.
     * @param userName String userName.
     * */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getPassword getter. Returns password.
     * @return String password.
     * */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword setter. Sets password.
     * @param password String password.
     * */
    public void setPassword(String password) {
        this.password = password;
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
     * lastUpdatedBy setter. Sets lastUpdatedBy.
     * @param lastUpdatedBy String lastUpdatedBy.
     * */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
