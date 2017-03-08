import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by Goki_PC on 3/8/2017.
 */
public class SiteVisit {
    private String pageId;
    private Timestamp eventTime;
    private String customerId;
    private HashMap<String, String> tags;

    public SiteVisit() {
    }

    //Getters & Setters
    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = Timestamp.valueOf(eventTime);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public HashMap<String, String> getTags() {
        return tags;
    }

    public void setTags(HashMap<String, String> tags) {
        this.tags = tags;
    }

    //Equals & Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteVisit siteVisit = (SiteVisit) o;

        if (!pageId.equals(siteVisit.pageId)) return false;
        if (!eventTime.equals(siteVisit.eventTime)) return false;
        return customerId.equals(siteVisit.customerId);
    }

    @Override
    public int hashCode() {
        int result = pageId.hashCode();
        result = 31 * result + eventTime.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }
}
