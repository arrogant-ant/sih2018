package inc.iris.sih2018.logic;

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



    private int bookingCost;
    private ParkingSlot slot;
    private static final int TIME_UNIT = 1000 * 60 * 60;//FOR ONE HOUR
    private String vehicleID; //can be vehicle no;
    private BookingStatus status;


    public Booking(Calendar schArrival, Calendar schDeparture, ParkingSlot slot, String vehicleID, BookingStatus status) {

        this.schArrival = schArrival;
        this.schDeparture = schDeparture;
        this.slot = slot;
        bookingDuration = (int) Math.ceil((double) (schDeparture.getTimeInMillis() - schArrival.getTimeInMillis()) / TIME_UNIT);
        bookingCost = bookingDuration * slot.getRate();
        this.vehicleID = vehicleID;
        this.status = status;
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
        arrival = timeFormat.format(getSchArrival());
        departure = timeFormat.format(getSchDeparture());
        return arrival + " - " + departure;
    }


    public int getBookingDuration() {
        return bookingDuration;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    //slot can only be changed if car not parked
    public boolean setSlot(ParkingSlot slot) {
        if (status == BookingStatus.PARKED)
            return false;
        this.slot = slot;
        return true;
    }
//static method to get estimated boking cost
    public static int getBookingCost(long arrivalTime, long departureTime, int cost) {
        int time = (int) (departureTime - arrivalTime) / TIME_UNIT;
        return time * cost;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public int getBookingCost() {
        return bookingCost;
    }


}
