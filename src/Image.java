import java.sql.Timestamp;

/**
 * Created by Goki_PC on 3/8/2017.
 */
public class Image {

    private String imageId;
    private Timestamp eventTime;
    private String customerId;
    private String cameraMake;
    private String cameraModel;

    public Image() {
    }

    //Getters & Setters
    public String getImageId() {
        return imageId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = Timestamp.valueOf(eventTime);
    }

    public String getCameraMake() {
        return cameraMake;
    }

    public void setCameraMake(String cameraMake) {
        this.cameraMake = cameraMake;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    //Equals & Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (!imageId.equals(image.imageId)) return false;
        if (!eventTime.equals(image.eventTime)) return false;
        return customerId.equals(image.customerId);
    }

    @Override
    public int hashCode() {
        int result = imageId.hashCode();
        result = 31 * result + eventTime.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }
}
