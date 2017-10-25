package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by M4800 on 20-Dec-16.
 */
public class Location {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exists;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        this.exists = exits;
        this.exists.put("Q", 0);
    }

    /*public void addExit(String direction, int location){
        exists.put(direction, location);
    }*/

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits(){
        return new HashMap<String, Integer>(exists);
    }
}
