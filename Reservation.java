/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotelreservation;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author Auriga
 */
public class Reservation {
    private Customer customer;
    private Room room;
    private Date checkIn;
    private Date checkOut;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Reservation(Customer customer, Room room, Date checkIn, Date checkOut) {
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Customer getCustomer() { return customer; }
    public Room getRoom() { return room; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }

    @Override
    public String toString() {
        return "Reservation for " + customer.name + " - " + room + 
               "\nCheck-in: " + dateFormat.format(checkIn) +
               "\nCheck-out: " + dateFormat.format(checkOut);
    }
}
