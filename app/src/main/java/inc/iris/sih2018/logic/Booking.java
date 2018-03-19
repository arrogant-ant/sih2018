package inc.iris.sih2018.logic;

import java.text.SimpleDateFormat;

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
    private long transactionTime,schArrival, schDeparture;
    private int bookingDuration, bookingCost;
    private ParkingSlot slot;
    private final int TIME_UNIT=1000*60*60;//FOR ONE HOUR
    private String vehicleID; //can be vehicle no;
    private BookingStatus status;


    public Booking(long transactionTime, long schArrival, long schDeparture, ParkingSlot slot, String vehicleID, BookingStatus status) {
        this.transactionTime = transactionTime;
        this.schArrival = schArrival;
        this.schDeparture = schDeparture;
        this.slot = slot;
        bookingDuration = (int)Math.ceil((double)(schDeparture -schArrival)/TIME_UNIT);
        bookingCost=bookingDuration*slot.getRate();
        this.vehicleID=vehicleID;
        this.status=status;
    }

    public long getSchArrival() {
        return schArrival;
    }

    public void setSchArrival(long schArrival) {
        this.schArrival = schArrival;
    }

    public long getSchDeparture() {
        return schDeparture;
    }

    public void setSchDeparture(long schDepature) {
        this.schDeparture = schDepature;
    }

    public String getTimeSlot()
    {
        String arrival, departure;
        SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm aaa");
        arrival= timeFormat.format(getSchArrival());
        departure=timeFormat.format(getSchDeparture());
        return arrival+" - "+departure;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public int getBookingDuration() {
        return bookingDuration;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    //slot can only be changed if car not parked
    public boolean setSlot(ParkingSlot slot) {
        if(status==BookingStatus.PARKED)
            return false;
        this.slot = slot;
        return true;
    }

    public int getBookingCost() {
        return bookingCost;
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


}
