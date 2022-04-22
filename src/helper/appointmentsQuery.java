package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointmentModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class appointmentsQuery {

    public static void insertAppointment(String title, String description, String location, String type, Date start, Date end, int customerId, int userId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setDate(5, start);
        ps.setDate(6, end);
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
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

    public static ObservableList<appointmentModel> getContacts() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<appointmentModel> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String appointmentContactId = resultSet.getString("Contact_Name");
            //appointmentModel dr = new appointmentModel(appointmentContactId);
            //allContacts.add(dr);
        }
        return allContacts;
    }
}
