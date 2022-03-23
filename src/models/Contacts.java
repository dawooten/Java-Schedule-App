package models;

/**
 * This is the Contacts model class defines access to this data.
 * */
public class Contacts {

    private int contactID;
    private String contactName;
    private String email;

    /**
     * Contacts constructor.
     *  @param contactID Integer contactID
     *  @param contactName String name of contact
     *  @param email String email of contact
     *  */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
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

    /**
     * getContactName getter. Returns contactName.
     * @return String contactName
     * */
    public String getContactName() {
        return contactName;
    }

    /**
     * setContactName setter. Sets contactName.
     * @param contactName String contactName.
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * getEmail getter. Returns email.
     * @return String email
     * */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail setter. Sets email.
     * @param email String email.
     * */
    public void setEmail(String email) {
        this.email = email;
    }
}
