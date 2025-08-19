package BusR;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ReservationService {

    public static void reserveTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Bus ID: ");
        int busId = sc.nextInt();
        System.out.print("Enter Your Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter Number of Seats: ");
        int seats = sc.nextInt();

        try (Connection con = ConnectionDb.getConnection()) {
            // Check seat availability
            PreparedStatement ps = con.prepareStatement("SELECT SEAT_COUNT FROM bus WHERE B_ID = ?");
            ps.setInt(1, busId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("SEAT_COUNT");
                if (availableSeats >= seats) {
                    // Insert reservation
                    PreparedStatement insert = con.prepareStatement(
                            "INSERT INTO reservations (B_ID, CUSTOMER_NAME, SEATS_BOOKED) VALUES (?, ?, ?)");
                    insert.setInt(1, busId);
                    insert.setString(2, name);
                    insert.setInt(3, seats);
                    insert.executeUpdate();

                    // Update bus seat count
                    PreparedStatement update = con.prepareStatement(
                            "UPDATE bus SET SEAT_COUNT = SEAT_COUNT - ? WHERE B_ID = ?");
                    update.setInt(1, seats);
                    update.setInt(2, busId);
                    update.executeUpdate();

                    System.out.println("Reservation successful!");
                } else {
                    System.out.println("Not enough seats available.");
                }
            } else {
                System.out.println("Invalid Bus ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public static void cancelTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Reservation ID to Cancel: ");
        int resId = sc.nextInt();

        try (Connection con = ConnectionDb.getConnection()) {
            // Get booked seats & bus ID
            PreparedStatement ps = con.prepareStatement("SELECT B_ID, SEATS_BOOKED FROM reservations WHERE R_ID = ?");
            ps.setInt(1, resId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int busId = rs.getInt("B_ID");
                int seats = rs.getInt("SEATS_BOOKED");

                // Delete reservation
                PreparedStatement delete = con.prepareStatement("DELETE FROM reservations WHERE R_ID = ?");
                delete.setInt(1, resId);
                delete.executeUpdate();

                // Restore seat count
                PreparedStatement update = con.prepareStatement("UPDATE bus SET SEAT_COUNT = SEAT_COUNT + ? WHERE B_ID = ?");
                update.setInt(1, seats);
                update.setInt(2, busId);
                update.executeUpdate();

                System.out.println("Reservation cancelled successfully!");
            } else {
                System.out.println("Invalid Reservation ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
