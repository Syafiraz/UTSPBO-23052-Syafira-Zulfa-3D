/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotelreservation;

/**
 *
 * @author Auriga
 */
public class Room {
    private String roomNumber;
    private String type;
    private boolean isAvailable;

    public Room(String roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = true;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - " + (isAvailable ? "Available" : "Occupied");
    }
}
