import java.awt.*;

/**
 * Created by Goki_PC on 3/7/2017.
 */
public enum EventType {
    CUSTOMER("CUSTOMER"),
    SITE_VISIT("SITE_VISIT"),
    IMAGE("IMAGE"),
    ORDER("ORDER");

    private String eventType;

    EventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getEventType()
    {
        return eventType;
    }
}
