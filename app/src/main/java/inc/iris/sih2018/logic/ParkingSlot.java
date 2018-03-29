package inc.iris.sih2018.logic;

/**
 * Created by Sud on 3/16/18.
 */

public class ParkingSlot extends ParkingArea {
    /**
     * empty store parking state
     */
    private int slotID;
    private boolean empty;


    //gps is , separated lat n lng
    //capacity store available
    public ParkingSlot(String areaId, String name, double lat, double lng, int capacity, int slotID) {
        super(areaId, name, lat,lng, capacity);
        this.slotID = slotID;
        this.empty = true;
    }

    public ParkingSlot(String areaId, String name,double lat, double lng, int capacity, int rate, int slotID) {
        super(areaId, name,lat,lng, capacity, rate);
        this.slotID = slotID;
        this.empty=true;
    }

    public int getSlotID() {
        return slotID;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
