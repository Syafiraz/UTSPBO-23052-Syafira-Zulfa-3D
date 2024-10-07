/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotelreservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
/**
 *
 * @author Auriga
 */
public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private SimpleDateFormat dateFormat;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (isRoomAvailable(room)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    private boolean isRoomAvailable(Room room) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                // If the room is currently reserved, it's not available
                if (new Date().before(reservation.getCheckOut())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Reservation makeReservation(Customer customer, Room room, Date checkIn, Date checkOut) {
        if (isRoomAvailable(room)) {
            // Set check-in time to 14:00
            Calendar calIn = Calendar.getInstance();
            calIn.setTime(checkIn);
            calIn.set(Calendar.HOUR_OF_DAY, 14);
            calIn.set(Calendar.MINUTE, 0);
            
            // Set check-out time to 12:00
            Calendar calOut = Calendar.getInstance();
            calOut.setTime(checkOut);
            calOut.set(Calendar.HOUR_OF_DAY, 12);
            calOut.set(Calendar.MINUTE, 0);
            
            Reservation reservation = new Reservation(customer, room, calIn.getTime(), calOut.getTime());
            reservations.add(reservation);
            customer.addReservation(reservation);
            return reservation;
        }
        return null;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void initializeReservations() throws ParseException {
        Customer customer1 = new Customer("John Doe", "C001");
        Customer customer2 = new Customer("Jane Smith", "C002");
        Customer customer3 = new Customer("Bob Johnson", "C003");

        Room room101 = rooms.get(0);
        Room room102 = rooms.get(1);
        Room room201 = rooms.get(2);

        makeReservation(customer1, room101, dateFormat.parse("2024-10-01 15:00"), dateFormat.parse("2024-10-03 12:00"));
        makeReservation(customer2, room102, dateFormat.parse("2024-10-02 16:00"), dateFormat.parse("2024-10-04 12:00"));
        makeReservation(customer3, room201, dateFormat.parse("2024-10-03 14:00"), dateFormat.parse("2024-10-05 12:00"));
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public Room getRoomByNumber(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }
}
