package inc.iris.sih2018.logic;

/**
 * Created by Sud on 3/16/18.
 */

public class Booking {
    /**
     * transactionTime = time at which booking was made
     * schArrival- schduled arrival
     * schDepature= scheduled depature
     * TIME_UNIT= smallest parking time measure unit
     */
    private long transactionTime,schArrival,schDepature;
    private int bookingDuration, bookingCost;
    private ParkingSlot slot;
    private final int TIME_UNIT=1000*60*60;//FOR ONE HOUR


    public Booking(long transactionTime, long schArrival, long schDepature, ParkingSlot slot) {
        this.transactionTime = transactionTime;
        this.schArrival = schArrival;
        this.schDepature = schDepature;
        this.slot = slot;
        bookingDuration = (int) ((schDepature-schArrival)/TIME_UNIT);
        bookingCost=bookingDuration*slot.getRate();
    }

    public long getSchArrival() {
        return schArrival;
    }

    public void setSchArrival(long schArrival) {
        this.schArrival = schArrival;
    }

    public long getSchDepature() {
        return schDepature;
    }

    public void setSchDepature(long schDepature) {
        this.schDepature = schDepature;
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

    public int getBookingCost() {
        return bookingCost;
    }
}
