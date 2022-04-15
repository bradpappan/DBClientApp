package helper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerAppointmentsQuery {

    /*public static int insert(String userName, String password) throws SQLException {
        String sql = "SELECT (User_Name, Password) FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        //ps.setString(1,);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }*/

    public static void select(int customerId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentContact = rs.getString("Contact_ID");
            String appointmentType = rs.getString("Type");
            Date appointmentStart = rs.getDate("Start");
            Date appointmentEnd = rs.getDate("End");
            int appointmentCustomerId = rs.getInt("Customer_ID");
            int appointmentUserId = rs.getInt("User_ID");
        }
    }
}
