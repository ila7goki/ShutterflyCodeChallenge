import java.sql.Timestamp;

/**
 * Created by Goki_PC on 3/6/2017.
 */
public class Customer {
    private String customerId;
    private Timestamp eventTime;
    private String lastName;
    private String adrCity;
    private String adrState;

    public Customer() {
        // empty constructor
    }

    //Getters & Setters
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        //System.out.println(eventTime);
        this.eventTime = Timestamp.valueOf(eventTime);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdrCity() {
        return adrCity;
    }

    public void setAdrCity(String adrCity) {
        this.adrCity = adrCity;
    }

    public String getAdrState() {
        return adrState;
    }

    public void setAdrState(String adrState) {
        this.adrState = adrState;
    }

    public String getCustomerId() {
        return customerId;

    }

    //Equals & Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return customerId.hashCode();
    }
}

