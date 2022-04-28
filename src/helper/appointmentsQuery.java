package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.addAppointmentModel;
import model.appointmentModel;
import model.customerModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public static void updateAppointment(String appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end, int contactId, int customerId, int userId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
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
        ps.setString(10, appointmentId);
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

public static appointmentModel editAppointment(String appointmentId) throws SQLException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String appointmentTitle = null;
    String appointmentDescription = null;
    String appointmentLocation = null;
    String appointmentType = null;
    Timestamp appointmentStart = null;
    Timestamp appointmentEnd = null;
    int appointmentCustomerId = 0;
    int appointmentUserId = 0;
    String appointmentContactId = null;

    String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM appointments WHERE Appointment_ID = ?";
    preparedStatement = JDBC.connection.prepareStatement(sql);
    preparedStatement.setString(1, appointmentId);
    resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        appointmentTitle = resultSet.getString("Title");
        appointmentDescription = resultSet.getString("Description");
        appointmentLocation = resultSet.getString("Location");
        appointmentType = resultSet.getString("Type");
        appointmentStart = resultSet.getTimestamp("Start");
        appointmentEnd = resultSet.getTimestamp("End");
        appointmentCustomerId = resultSet.getInt("Customer_ID");
        appointmentUserId = resultSet.getInt("User_ID");
        appointmentContactId = resultSet.getString("Contact_ID");
    }

    return new appointmentModel(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType,
            appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentContactId);
}

    public static String getContactName(String contactId) throws SQLException {
        ResultSet rs;
        String contactName = null;
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, contactId);
        rs = ps.executeQuery();
        while (rs.next()) {
            contactName = rs.getString("Contact_Name");
        }
        return contactName;
    }

    public static ObservableList<appointmentModel> checkForOverlap(Timestamp start, Timestamp end, String customerId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<appointmentModel> allAppointmentsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE ? BETWEEN Start AND End OR ? BETWEEN Start AND End OR ? < Start AND ? > End AND Customer_ID = ?";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setTimestamp(1, start);
        preparedStatement.setTimestamp(2, end);
        preparedStatement.setTimestamp(3, start);
        preparedStatement.setTimestamp(4, end);
        preparedStatement.setString(5, customerId);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String appointmentId = resultSet.getString("Appointment_ID");
            String appointmentTitle = resultSet.getString("Title");
            String appointmentDescription = resultSet.getString("Description");
            String appointmentLocation = resultSet.getString("Location");
            String appointmentType = resultSet.getString("Type");
            Timestamp appointmentStart = resultSet.getTimestamp("Start");
            Timestamp appointmentEnd = resultSet.getTimestamp("End");
            int appointmentCustomerId = resultSet.getInt("Customer_ID");
            int appointmentUserId = resultSet.getInt("User_ID");
            String appointmentContactId = resultSet.getString("Contact_ID");

            appointmentModel appointmentResult = new appointmentModel(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart,
                    appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentContactId);
            allAppointmentsObservableList.add(appointmentResult);
        }
        return allAppointmentsObservableList;
    }

    public static void deleteAllAppointments(int customerId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }
}
