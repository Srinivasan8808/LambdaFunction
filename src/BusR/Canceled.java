package BusR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Canceled {

    public static void cancel() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Booking ID to cancel: ");
        int bookingIdToCancel = sc.nextInt();

        try (Connection con = ConnectionDb.getConnection()) { // ✅ Use shared connection method
            if (con == null) {
                System.out.println("Database connection failed.");
                return;
            }

            con.setAutoCommit(false); // Start transaction

            // 1️⃣ Check if booking exists
            String checkBookingSql = "SELECT * FROM BOOKING WHERE BOOKING_ID = ?";
            try (PreparedStatement checkBookingStatement = con.prepareStatement(checkBookingSql)) {
                checkBookingStatement.setInt(1, bookingIdToCancel);

                try (ResultSet rs = checkBookingStatement.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("❌ Booking with ID " + bookingIdToCancel + " not found.");
                        return; // Exit without committing
                    }

                    int canceledSeats = rs.getInt("NUMBER_SEAT");
                    int busId = rs.getInt("B_ID");

                    // 2️⃣ Update bus seat count
                    String updateBusSql = "UPDATE BUS SET SEAT_COUNT = SEAT_COUNT + ? WHERE B_ID = ?";
                    try (PreparedStatement updateBusStatement = con.prepareStatement(updateBusSql)) {
                        updateBusStatement.setInt(1, canceledSeats);
                        updateBusStatement.setInt(2, busId);
                        updateBusStatement.executeUpdate();
                    }

                    // 3️⃣ Delete booking record
                    String deleteBookingSql = "DELETE FROM BOOKING WHERE BOOKING_ID = ?";
                    try (PreparedStatement deleteBookingStatement = con.prepareStatement(deleteBookingSql)) {
                        deleteBookingStatement.setInt(1, bookingIdToCancel);
                        deleteBookingStatement.executeUpdate();
                    }

                    con.commit();
                    System.out.println("✅ Booking successfully canceled!");
                }
            } catch (SQLException e) {
                con.rollback(); // Rollback if something goes wrong
                System.out.println("⚠ Transaction rolled back due to error.");
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
