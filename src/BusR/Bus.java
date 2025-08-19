package BusR;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bus {

    public static void displayBusDetails() {
        String sql = "SELECT B_ID, B_NAME, B_TYPE, SEAT_TYPE, SEAT_COUNT FROM bus";

        try (Connection con = ConnectionDb.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nB_ID | B_NAME        | B_TYPE  | SEAT_TYPE | SEAT_COUNT");
            System.out.println("------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%d | %-12s | %-7s | %-9s | %d%n",
                        rs.getInt("B_ID"),
                        rs.getString("B_NAME"),
                        rs.getString("B_TYPE"),
                        rs.getString("SEAT_TYPE"),
                        rs.getInt("SEAT_COUNT"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
