package inc.iris.sih2018.logic;

/**
 * Created by Sud on 3/16/18.
 */

public class ParkingArea {
    protected String areaId, name, address, gps;
    private int capacity, available;

    public ParkingArea(String areaId, String name, String address, String gps, int capacity) {
        this.areaId = areaId;
        this.name = name;
        this.address = address;
        this.gps = gps;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getAddress() {
        return address;
    }

    public String getGps() {
        return gps;
    }
}

