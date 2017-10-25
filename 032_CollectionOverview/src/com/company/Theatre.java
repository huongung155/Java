package com.company;

import java.util.*;

/**
 * Created by M4800 on 14-Dec-16.
 */
public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<Seat>();

    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numRows - 1);
        for(char row = 'A'; row <= lastRow; row++){
            for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++){
                double price = 12;

                if((row < 'D') && (seatNum >= 4 || seatNum <= 9)){
                    price = 14;
                }else if(row > 'F' && (seatNum < 4 && seatNum > 9)){
                    price = 7;
                }
                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber){
        Seat requestSeat = new Seat(seatNumber);
        int foundSeat = Collections.binarySearch(seats, requestSeat, null);
        if(foundSeat >= 0){
            return seats.get(foundSeat).reserve();
        }else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }

    //testing
    public Collection<Seat> getSeats(){
        return seats;
        }
    }

    public class Seat implements Comparable<Seat>{
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public boolean reserve(){
            if(!this.reserved){
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            }else {
                return false;
            }
        }

        /*public boolean cancel(){
            if(this.reserved){
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " is cancelled");
                return true;
            }else {
                return false;
            }
        }*/

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }
    }
}
