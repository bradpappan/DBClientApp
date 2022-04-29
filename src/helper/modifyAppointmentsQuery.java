package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointmentModel;

import java.sql.*;

/**
 * This class hold queries for modify appointments
 */
public class modifyAppointmentsQuery {

    /**
     *
     * @param customerId passes in the customerId
     * @return Gets all the appointments that equals customerId
     * @throws SQLException
     */
    public static ObservableList<appointmentModel> getAllAppointments(String customerId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<appointmentModel> allAppointmentsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, customerId);
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

    /**
     * Deletes an appointment that equals the appointmentId
     * @param appointmentId passes in the appointmentId
     * @throws SQLException
     */
    public static void deleteAppointment(String appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, appointmentId);
        ps.executeUpdate();
    }
}
