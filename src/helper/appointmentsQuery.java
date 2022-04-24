package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.addAppointmentModel;
import model.appointmentModel;

import java.sql.*;

public class appointmentsQuery {

    public static void insertAppointment(String title, String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.executeUpdate();
    }

    public static void updateAppointment(int appointmentId, String title, String description, String location, String type, Date start, Date end, int customerId, int userId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setDate(5, start);
        ps.setDate(6, end);
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(6, appointmentId);
        ps.executeUpdate();
    }

    public static ObservableList<addAppointmentModel> getContacts() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<addAppointmentModel> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String contactName = resultSet.getString("Contact_Name");
            int contactId = resultSet.getInt("Contact_ID");
            addAppointmentModel dr = new addAppointmentModel (contactName, contactId);
            allContacts.add(dr);
        }
        return allContacts;
    }


}
