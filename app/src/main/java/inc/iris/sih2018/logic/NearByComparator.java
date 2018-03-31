package inc.iris.sih2018.logic;

import java.util.Comparator;

import inc.iris.sih2018.nearby_parking_bean;

/**
 * Created by Sud on 3/31/18.
 */

public class NearByComparator implements Comparator<nearby_parking_bean> {
    @Override
    public int compare(nearby_parking_bean p1, nearby_parking_bean p2) {

        return (int)(Double.valueOf(p1.getDistance())-Double.valueOf(p2.getDistance()));    }
}
