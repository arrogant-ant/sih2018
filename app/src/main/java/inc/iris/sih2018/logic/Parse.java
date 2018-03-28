package inc.iris.sih2018.logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sud on 3/27/18.
 */


public class Parse {
    public static Booking[] getBooking(String  response, BookingStatus status) {

        int i;
        try {
            JSONObject json=new JSONObject(response);
            JSONArray array = json.getJSONArray("server_response");
            Booking[] list = new Booking[array.length()];
            for (i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                list[i] = new Booking(
                        object.getLong("arrival"), //arrival
                        object.getLong("departure"), //
                        new ParkingSlot(
                                object.getString("slotID"),
                                object.getString("name"),
                                object.getString("gps"),
                                object.getInt("capacity"),
                                object.getInt("slotID")
                        ),
                        object.getString("vehicleID"),
                        status);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }



}
