import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.function.Function;

/**
 * Created by Goki_PC on 3/7/2017.
 */
public class EventIngester {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args)
    {
        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse(new FileReader("./input/events.txt"));
            JSONObject jsonObj = (JSONObject)obj;
            JSONArray events = (JSONArray) jsonObj.get("events");

            Iterator<JSONObject> it = events.iterator();

            ArrayList<Customer> customerEvents = new ArrayList<Customer>();
            ArrayList<Order> orderEvents = new ArrayList<Order>();

            while(it.hasNext())
            {
                JSONObject eventObj = it.next();
              // System.out.println(eventObj.get("verb"));
             //   System.out.println(eventObj.get("type").toString() + ", " + EventType.CUSTOMER.toString());
                if(eventObj.get("type").toString().equals(EventType.CUSTOMER.getEventType()))
                {
                    Customer c = new Customer();
                    c.setCustomerId(eventObj.get("key").toString());
                    c.setEventTime(eventObj.get("event_time").toString().replace("T", " ").replace("Z", ""));
                    c.setLastName(eventObj.get("last_name").toString());
                    c.setAdrCity(eventObj.get("adr_city").toString());
                    c.setAdrState(eventObj.get("adr_state").toString());
                    customerEvents.add(c);
                }

                if(eventObj.get("type").toString().equals(EventType.ORDER.getEventType()))
                {
                    Order o = new Order();
                    o.setOrderId(eventObj.get("key").toString());
                    o.setCustomerId(eventObj.get("customer_id").toString());
                    o.setEventTime(eventObj.get("event_time").toString().replace("T", " ").replace("Z", ""));
                    String totalAmountStr = eventObj.get("total_amount").toString().replace(" USD", "");
               //     System.out.println(Double.parseDouble(totalAmountStr.isEmpty()? "0.0": totalAmountStr));
                    o.setTotalAmount(Double.parseDouble(totalAmountStr.isEmpty()? "0.0": totalAmountStr));
                    orderEvents.add(o);
                }
               // System.out.println(eventObj.get("customer_id"));
            }

//           for(Customer a : customerEvents)
//            {
//                System.out.println(a.getCustomerId());
//                System.out.println(a.getLastName());
//                System.out.println(a.getAdrCity() + ", " + a.getAdrState());
//                System.out.println(a.eventTime);
//            }

            for(Order o : orderEvents)
            {
                //System.out.println(o.getOrderId());
                System.out.println(o.getCustomerId());
//                System.out.println(o.getEventTime());
                System.out.println(o.getTotalAmount());
            }

            Map<String, Double> customerTotalOrderAmount = orderEvents.stream().filter(distinctByKey(key-> key.getOrderId())).collect(Collectors.groupingBy(Order::getCustomerId, Collectors.summingDouble(Order::getTotalAmount)));

            System.out.println(customerTotalOrderAmount);

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }

    }
}
