package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by M4800 on 19-May-17.
 */
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    static {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations.txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()){
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String, Integer> tempExit = new HashMap<String, Integer>();
                locations.put(loc, new Location(loc, description, tempExit));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(scanner != null){
                scanner.close();
            }
        }
    }

    public static void main(String[] args) {
        try(FileWriter locFile = new FileWriter("locations.txt");
            FileWriter dirFile = new FileWriter("direction.txt")) {
            for(Location location: locations.values()){
                locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
                for(String direction: location.getExits().keySet()){
                    dirFile.write(location.getLocationID() + ", " + direction + ", " + location.getExits().get(direction) + "\n");
                }
            }
        }catch (IOException e){

        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return null;
    }
}
