package com.company;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by M4800 on 18-May-17.
 */
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<Integer, IndexRecord>();
    private static RandomAccessFile rafRead;

    public static void main(String[] args) {
        try(RandomAccessFile raf = new RandomAccessFile("locations.dat", "rwd")){
            raf.writeInt(locations.size());
            int indexSize = locations.size() * 3 * Integer.BYTES;
            int locationStart = (int)(indexSize + raf.getFilePointer() + Integer.BYTES);
            raf.writeInt(locationStart);

            long indexStart = raf.getFilePointer();

            int startPointer = locationStart;
            raf.seek(startPointer);

            for(Location location: locations.values()){
                raf.writeInt(location.getLocationID());
                raf.writeUTF(location.getDescription());
                StringBuilder sb = new StringBuilder();
                for(String direction: location.getExits().keySet()){
                    sb.append(direction);
                    sb.append(",");
                    sb.append(location.getExits().get(direction));
                    sb.append(",");
                }
                raf.writeUTF(sb.toString());
                IndexRecord record = new IndexRecord(startPointer, (int) (raf.getFilePointer() - startPointer));
                index.put(location.getLocationID(), record);
                startPointer =(int) raf.getFilePointer();
            }

            raf.seek(indexStart);
            for(Integer locationID: index.keySet()){
                raf.writeInt(locationID);
                raf.writeInt(index.get(locationID).getStartByte());
                raf.writeInt(index.get(locationID).getLength());
            }
        }catch (IOException e){

        }
    }

    // 1. This first 4 bytes will contain the number of locations (Bytes 0 - 3)
    // 2. The next 4 bytes will contain the start offset of the locations section (bytes 4 - 7)
    // 3. The next section of the file will contain the index (the index is 1692 bytes long -> It will start at byte 8 and end at byte 1699)
    // 4. The final section of the file will contain the location record (data). It will start at byte 1700

    static {
        try {
            rafRead = new RandomAccessFile("locations_rand.dat", "rwd");
            int numLocation = rafRead.readInt();
            long locationStartPoint = rafRead.readInt();

            while (rafRead.getFilePointer() < locationStartPoint){
                int locationId = rafRead.readInt();
                int locationStart = rafRead.readInt();
                int locationLength = rafRead.readInt();

                IndexRecord record = new IndexRecord(locationStart, locationLength);
                index.put(locationId, record);
            }
        }catch (IOException e){
            System.out.println("IOException in static initializer " + e.getMessage());
        }
    }

    public Location getLocation(int locationId) throws IOException{
        IndexRecord record = index.get(locationId);
        rafRead.seek(record.getStartByte());
        int id = rafRead.readInt();
        String description = rafRead.readUTF();
        String exits = rafRead.readUTF();
        String[] exitPart = exits.split(",");

        Location location = new Location(locationId, description, null);

        if(locationId != 0){
            for(int i = 0; i < exitPart.length; i++){
                System.out.println("exitPart = " + exitPart[i]);
                System.out.println("exitPart[+1] = " + exitPart[i + 1]);
                String direction = exitPart[i];
                int destination = Integer.parseInt(exitPart[++i]);
                location.addExit(direction, destination);
            }
        }

        return location;
    }

    public void close() throws IOException{
        rafRead.close();
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
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
