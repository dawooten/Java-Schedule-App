package utilities;

/**
 * This is the ReportsTypeMonth model class that defines access to this data.
 * */
public class ReportsTypeMonth {
    private int numberOfAppointments;
    private String month;
    private String appointmentType;

    /**
     * ReportsTypeMonth constructor.
     *  @param numberOfAppointments Integer number of appointments
     *  @param month String month of appointment
     *  @param appointmentType String type of appointment
     *  */
    public ReportsTypeMonth(int numberOfAppointments, String month, String appointmentType) {
        this.numberOfAppointments = numberOfAppointments;
        this.month = month;
        this.appointmentType = appointmentType;
    }

    /**
     * getNumberOfAppointments getter. Returns numberOfAppointments.
     * @return Integer numberOfAppointments.
     * */
    public int getNumberOfAppointments() {
        return numberOfAppointments;
    }

    /**
     * setNumberOfAppointments setter. Sets numberOfAppointments.
     * @param numberOfAppointments Integer numberOfAppointments.
     * */
    public void setNumberOfAppointments(int numberOfAppointments) {
        this.numberOfAppointments = numberOfAppointments;
    }

    /**
     * getMonth getter. Returns month.
     * @return String month.
     * */
    public String getMonth() {
        return month;
    }

    /**
     * setMonth setter. Sets month.
     * @param month String month.
     * */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * getAppointmentType getter. Returns appointmentType.
     * @return String appointmentType.
     * */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * setAppointmentType setter. Sets appointmentType.
     * @param appointmentType String appointmentType.
     * */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

}
