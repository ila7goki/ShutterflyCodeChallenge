import java.sql.Timestamp;

/**
 * Created by Goki_PC on 3/8/2017.
 */
public class Order {

    private String orderId;
    private String customerId;
    private Timestamp eventTime;
    private double totalAmount;

    public Order() {
    }

    //Getters & Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        // System.out.println(eventTime);
        this.eventTime = Timestamp.valueOf(eventTime);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    //Equals & Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!orderId.equals(order.orderId)) return false;
        if (!customerId.equals(order.customerId)) return false;
        return eventTime.equals(order.eventTime);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + customerId.hashCode();
        result = 31 * result + eventTime.hashCode();
        return result;
    }


}
