import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Goki_PC on 3/7/2017.
 */
public class EventIngester {

    public ArrayList<Customer> customerEvents = new ArrayList<>();
    public ArrayList<SiteVisit> siteVisitEvents = new ArrayList<>();
    public ArrayList<Image> imageEvents = new ArrayList<>();
    public ArrayList<Order> orderEvents = new ArrayList<>();


//    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
//        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
//        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
//    }

    private void setEventList(EventType eventType, JSONObject eventObj) {
        String eventTime = eventObj.get("event_time").toString().replace("T", " ").replace("Z", "");
        String customerId = eventType.equals(EventType.CUSTOMER) ? eventObj.get("key").toString() : eventObj.get("customer_id").toString();

        switch (eventType) {
            case CUSTOMER:
                Customer c = new Customer();
                c.setEventTime(eventTime);
                c.setCustomerId(customerId);
                c.setLastName(eventObj.get("last_name").toString());
                c.setAdrCity(eventObj.get("adr_city").toString());
                c.setAdrState(eventObj.get("adr_state").toString());
                customerEvents.add(c);
                break;
            case SITE_VISIT:
                SiteVisit s = new SiteVisit();
                s.setEventTime(eventTime);
                s.setCustomerId(customerId);
                s.setPageId(eventObj.get("key").toString());
                s.setTags((HashMap<String, String>) eventObj.get("tags"));
                siteVisitEvents.add(s);
                break;
            case IMAGE:
                Image i = new Image();
                i.setEventTime(eventTime);
                i.setCustomerId(customerId);
                i.setImageId(eventObj.get("key").toString());
                i.setCameraMake(eventObj.get("camera_make").toString());
                i.setCameraModel(eventObj.get("camera_model").toString());
                imageEvents.add(i);
                break;
            case ORDER:
                Order o = new Order();
                o.setEventTime(eventTime);
                o.setCustomerId(customerId);
                o.setOrderId(eventObj.get("key").toString());
                o.setTotalAmount(Double.parseDouble(eventObj.get("total_amount").toString().replace(" USD", "")));
                orderEvents.add(o);
                break;
            //Code default behavior
        }
    }


    public void IngestEvents(String fileName) {
        JSONParser parser = new JSONParser();
        //"./input/events.txt"
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray events = (JSONArray) jsonObj.get("events");
            Iterator<JSONObject> it = events.iterator();
            while (it.hasNext()) {
                JSONObject eventObj = it.next();
                this.setEventList(EventType.valueOf(eventObj.get("type").toString()), eventObj);
            }

//            Map<String, Double> customerTotalOrderAmount = orderEvents.stream().filter(distinctByKey(key-> key.getOrderId())).collect(Collectors.groupingBy(Order::getCustomerId, Collectors.summingDouble(Order::getTotalAmount)));
//
//            System.out.println(customerTotalOrderAmount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
