package inc.iris.sih2018.logic;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sud on 3/16/18.
 */


public class Booking {
    /**
     * transactionTime = time at which booking was made
     * schArrival- schduled arrival
     * schDeparture= scheduled depature
     * TIME_UNIT= smallest parking time measure unit
     */
    private Calendar schArrival;
    private Calendar schDeparture;
    private int bookingDuration;
    private String bookingID;

    private int bookingCost;
    private ParkingSlot slot;
    private static final int TIME_UNIT = 1000 * 60 * 60;//FOR ONE HOUR
    private String vehicleID; //can be vehicle no;
    private String status;
    private String type;
    private static final String TAG = "Booking";


    public Booking(String bookingID,Calendar schArrival, Calendar schDeparture, ParkingSlot slot, String vehicleID, String type,String status,int bookingCost) {

        this.bookingID=bookingID;
        this.schArrival = schArrival;
        this.schDeparture = schDeparture;
        this.slot = slot;
        //bookingDuration = (int) Math.ceil((double) (schDeparture.getTimeInMillis() - schArrival.getTimeInMillis()) / TIME_UNIT);
        this.bookingCost = bookingCost;
        this.vehicleID = vehicleID;
        this.status = status;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public Calendar getSchArrival() {
        return schArrival;
    }

    public void setSchArrival(Calendar schArrival) {
        this.schArrival = schArrival;
    }

    public Calendar getSchDeparture() {
        return schDeparture;
    }

    public void setSchDeparture(Calendar schDepature) {
        this.schDeparture = schDepature;
    }

    public String getTimeSlot() {
        String arrival, departure;
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aaa");
        arrival = timeFormat.format(getSchArrival().getTimeInMillis());
        departure = timeFormat.format(getSchDeparture().getTimeInMillis());
        return arrival + " - " + departure;
    }


    public int getBookingDuration() {
        return bookingDuration;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    //slot can only be changed if car not parked
 /*   public boolean setSlot(ParkingSlot slot) {
        if (status == BookingStatus.PARKED)
            return false;
        this.slot = slot;
        return true;
    }*/
//static method to get estimated boking cost
    public static int getBookingCost(long arrivalTime, long departureTime, int cost) {
        int time = (int) Math.ceil((double) (departureTime - arrivalTime) / TIME_UNIT);
        Log.d(TAG, "getBookingCost: "+cost+" time "+time+" ar "+arrivalTime+" dep "+departureTime);
        return time * cost;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBookingCost() {
        return bookingCost;
    }


}
