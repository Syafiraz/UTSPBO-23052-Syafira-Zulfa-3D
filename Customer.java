/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotelreservation;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Auriga
 */
public class Customer extends User {
    private List<Reservation> reservations;

    public Customer(String name, String id) {
        super(name, id);
        this.reservations = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public void viewDetails() {
        System.out.println("Customer Details:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Reservations: " + reservations.size());
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
