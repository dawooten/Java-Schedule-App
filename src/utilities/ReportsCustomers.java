package utilities;

/**
 * This is the ReportsCustomers model class that defines access to this data.
 * */
public class ReportsCustomers {

    private String customerName;
    private String totalAppts;

    /**
     * ReportsCustomers constructor.
     *  @param customerName String name of customer
     *  @param totalAppts String number of total appointments
     *  */
    public ReportsCustomers(String customerName, String totalAppts) {
        this.customerName = customerName;
        this.totalAppts = totalAppts;
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
     * getTotalAppts getter. Returns totalAppts.
     * @return String totalAppts.
     * */
    public String getTotalAppts() {
        return totalAppts;
    }

    /**
     * setTotalAppts setter. Sets totalAppts.
     * @param totalAppts String totalAppts.
     * */
    public void setTotalAppts(String totalAppts) {
        this.totalAppts = totalAppts;
    }
}
