package inc.iris.sih2018.logic;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Sud on 3/16/18.
 */

public class ParkingArea {
    /**
     * rate= parking rate
     */
    private String areaId, name;
    private double lat,lng;
    private int capacity, available,rate;
    private final int DEFAULT_RATE=10;

    public ParkingArea(String areaId, String name, double lat, double lng, int capacity) {
        this.areaId = areaId;
        this.name = name;
        this.lat = lat;
        this.lng=lng;
        this.capacity = capacity;
        this.rate=DEFAULT_RATE;
    }

    public ParkingArea(String areaId, String name, double lat, double lng, int capacity, int rate) {
        this.areaId = areaId;
        this.name = name;
        this.lat = lat;
        this.lng=lng;
        this.capacity = capacity;
        this.rate = rate;
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

    public LatLng getLatLng() {
        return new LatLng(lat,lng);
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}

