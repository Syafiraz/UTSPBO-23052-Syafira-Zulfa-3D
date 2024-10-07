/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.hotelreservation;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.util.Calendar;
/**
 *
 * @author Auriga
 */
public class HotelReservationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Hotel hotel = new Hotel();
    private static User currentUser;

    public static void main(String[] args) {
        initializeHotel();
        
        while (true) {
            if (currentUser == null) {
                login();
            } else if (currentUser instanceof Customer) {
                customerMenu();
            } else if (currentUser instanceof Admin) {
                adminMenu();
            }
        }
    }

    private static void initializeHotel() {
        hotel.addRoom(new Room("101", "Single"));
        hotel.addRoom(new Room("102", "Double"));
        hotel.addRoom(new Room("201", "Suite"));
        try {
            hotel.initializeReservations();
        } catch (ParseException e) {
            System.out.println("Error initializing reservations: " + e.getMessage());
        }
    }

    private static void login() {
        System.out.println("1. Login as Customer");
        System.out.println("2. Login as Admin");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        if (choice == 1) {
            currentUser = new Customer(name, id);
        } else if (choice == 2) {
            currentUser = new Admin(name, id);
        }
    }

    private static void customerMenu() {
        Customer customer = (Customer) currentUser;
        while (true) {
            System.out.println("\n1. View available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation(customer);
                    break;
                case 3:
                    viewCustomerReservations(customer);
                    break;
                case 4:
                    currentUser = null;
                    return;
            }
        }
    }

    private static void adminMenu() {
        Admin admin = (Admin) currentUser;
        while (true) {
            System.out.println("\n1. View all reservations");
            System.out.println("2. View available rooms");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    admin.viewAllReservations(hotel.getReservations());
                    break;
                case 2:
                    viewAvailableRooms();
                    break;
                case 3:
                    currentUser = null;
                    return;
            }
        }
    }

    private static void viewAvailableRooms() {
        List<Room> availableRooms = hotel.getAvailableRooms();
        System.out.println("Available Rooms:");
        for (Room room : availableRooms) {
            System.out.println(room);
        }
    }

     private static void makeReservation(Customer customer) {
        viewAvailableRooms();
        System.out.print("Enter room number to reserve: ");
        String roomNumber = scanner.nextLine();
        
        Room selectedRoom = hotel.getRoomByNumber(roomNumber);
        
        if (selectedRoom != null && hotel.getAvailableRooms().contains(selectedRoom)) {
            System.out.print("Enter check-in date (yyyy-MM-dd): ");
            String checkInStr = scanner.nextLine();
            System.out.print("Enter check-out date (yyyy-MM-dd): ");
            String checkOutStr = scanner.nextLine();
            
            try {
                Date checkIn = hotel.getDateFormat().parse(checkInStr + " 14:00");
                Date checkOut = hotel.getDateFormat().parse(checkOutStr + " 12:00");
                Reservation reservation = hotel.makeReservation(customer, selectedRoom, checkIn, checkOut);
                if (reservation != null) {
                    System.out.println("Reservation made successfully!");
                    System.out.println("Check-in time is at 14:00 on " + checkInStr);
                    System.out.println("Check-out time is at 12:00 on " + checkOutStr);
                    System.out.println("You can check in anytime after 14:00 on your check-in date.");
                } else {
                    System.out.println("Failed to make reservation. Room might not be available for the selected dates.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        } else {
            System.out.println("Invalid room number or room not available.");
        }
    }

    private static void viewCustomerReservations(Customer customer) {
        List<Reservation> reservations = customer.getReservations();
        if (reservations.isEmpty()) {
            System.out.println("You have no reservations.");
        } else {
            System.out.println("Your Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}
