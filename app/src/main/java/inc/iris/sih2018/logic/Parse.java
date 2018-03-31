package inc.iris.sih2018.logic;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Sud on 3/27/18.
 */


public class Parse {
    private static final String TAG = "Parse";
    public static Booking[] getBooking(String response) {

        int i;
        try {
            JSONObject json = new JSONObject(response);
            JSONArray array = json.getJSONArray("server_response");
            Calendar arrival, departure;
            arrival = Calendar.getInstance();
            departure = Calendar.getInstance();
            Booking[] list = new Booking[array.length()];
            for (i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                arrival.setTimeInMillis(Long.valueOf(object.getString("arrival")));
                departure.setTimeInMillis(object.getLong("departure"));

                list[i] = new Booking(
                        object.getString("booking_id"),
                        arrival, //arrival
                        departure, //
                        new ParkingSlot(
                                object.getString("name"),
                                object.getString("location"),
                                0.0,0.0,
                                object.getInt("capacity"),//available
                                object.getInt("slotID")
                        ),
                        object.getString("vehicleID"),
                        object.getString("type"),
                        object.getString("status"),
                        object.getInt("price"));
                Log.d(TAG, "getBooking:i "+list[i]);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
      /*  Booking booking=new Booking(Calendar.getInstance(),Calendar.getInstance(),
                new ParkingSlot("Nagpur","Station",22.0,22.0,100,12),
                "MH010A1234",BookingStatus.CONFIRMED);*/

        Booking[] list=new Booking[0];
        return list;

    }


}
