package inc.iris.sih2018.logic;

/**
 * Created by Sud on 3/16/18.
 */

public class ParkingSlot extends ParkingArea {
    private int slotID;
    private boolean empty;

    public ParkingSlot(String areaId, String name, String address, String gps, int capacity, int slotID, boolean empty) {
        super(areaId, name, address, gps, capacity);
        this.slotID = slotID;
        this.empty = empty;
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
