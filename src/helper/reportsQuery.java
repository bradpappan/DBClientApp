package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointmentModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class reportsQuery {

    public static ObservableList<String> contactAppointmentList(String contactId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<String> appointmentsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, contactId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String appointmentId = resultSet.getString("Appointment_ID");
            String appointmentTitle = resultSet.getString("Title");
            String appointmentDescription = resultSet.getString("Description");
            String appointmentType = resultSet.getString("Type");
            Timestamp appointmentStart = resultSet.getTimestamp("Start");
            Timestamp appointmentEnd = resultSet.getTimestamp("End");
            int appointmentCustomerId = resultSet.getInt("Customer_ID");

            String appointment = ("ID: " + appointmentId + "  Title: " + appointmentTitle +  "  Description: " +  appointmentDescription + "  Type: " + appointmentType + "  Start: " + appointmentStart + "  End: " + appointmentEnd + "  CustomerID: " + appointmentCustomerId);
            appointmentsObservableList.add(appointment);
        }
        return appointmentsObservableList;
    }

    public static String contactIds(String contactName) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String contactId = null;

        String typeSql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
        preparedStatement = JDBC.connection.prepareStatement(typeSql);
        preparedStatement.setString(1, contactName);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            contactId = resultSet.getString("Contact_ID");
        }
        return contactId;
    }

    public static ObservableList<String> contactNames() throws SQLException {
        ResultSet rs;
        String contactName = null;

        ObservableList<String> appointmentsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT Contact_Name FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            contactName = rs.getString("Contact_Name");
            appointmentsObservableList.add(contactName);
        }
        return appointmentsObservableList;
    }

    public static ObservableList<String> typeList() throws SQLException {
        PreparedStatement ps = null;
        ResultSet typeResultSet = null;

        ObservableList<String> appointmentsObservableList = FXCollections.observableArrayList();
        String typeSql = "SELECT Type, COUNT(Type) AS \"Total\" FROM appointments GROUP BY Type";
        ps = JDBC.connection.prepareStatement(typeSql);
        typeResultSet = ps.executeQuery();

        int typeNumber = 0;
        while (typeResultSet.next()) {

            String type = "Type " + typeNumber + ": " + typeResultSet.getString("Type") + " Total Number: " + typeResultSet.getString("Total") + "\n";
            appointmentsObservableList.add(type);
            typeNumber++;
        }
        return appointmentsObservableList;
    }

    public static ObservableList<String> monthList() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet monthResultSet = null;

        ObservableList<String> appointmentsObservableList = FXCollections.observableArrayList();
        String monthSql = "SELECT MONTHNAME(Start) AS \"Month\", COUNT(MONTH(Start)) AS \"Total\" FROM appointments GROUP BY Month";
        preparedStatement = JDBC.connection.prepareStatement(monthSql);
        monthResultSet = preparedStatement.executeQuery();

        while (monthResultSet.next()) {
            String month = "Month: " + monthResultSet.getString("Month") + " Total Number: " + monthResultSet.getString("Total") + "\n";
            appointmentsObservableList.add(month);
        }
        return appointmentsObservableList;
    }

    public static String countCustomers() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String customer = null;

        String sql = "SELECT COUNT(Customer_ID) AS \"Total Customers\" FROM customers";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            customer = resultSet.getString("Total Customers");
        }
        return customer;
    }
}
