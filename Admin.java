/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotelreservation;
import java.util.List;
/**
 *
 * @author Auriga
 */
public class Admin extends User {
    public Admin(String name, String id) {
        super(name, id);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public void viewDetails() {
        System.out.println("Admin Details:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
    }

    public void viewAllReservations(List<Reservation> reservations) {
        System.out.println("All Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
