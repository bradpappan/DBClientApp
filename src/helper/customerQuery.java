package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.addCustomerModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class holds queries for customer views
 */
public class customerQuery {

    /**
     * gets the divisions
     * @return the divisions
     * @throws SQLException
     */
    public static ObservableList<addCustomerModel> getDivision() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<addCustomerModel> allDivisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions INNER JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String division = resultSet.getString("Division");
            int divisionId = resultSet.getInt("Division_ID");
            int countryId = resultSet.getInt("Country_ID");
            String country = resultSet.getString("Country");
            addCustomerModel dr = new addCustomerModel (countryId, country, divisionId, division);
            allDivisions.add(dr);
        }
        return allDivisions;
    }

    /**
     * Inserts a customer into the table view and database
     * @param name passes in the name
     * @param address passes in the address
     * @param postalCode passes in the postalCode
     * @param phone passes the phone
     * @param divisionId passes in the divisionId
     * @throws SQLException
     */
    public static void insertCustomer(String name, String address, int postalCode, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setInt(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        ps.executeUpdate();
    }

    /**
     * Updates a selected customer record in the table view and database
     * @param customerId passes in the customerId
     * @param name passes in the name
     * @param address passes in the address
     * @param postalCode passes in the postalCode
     * @param phone passes in the phone
     * @param divisionId passes in the divisionId
     * @throws SQLException
     */
    public static void updateCustomer(int customerId, String name, String address, int postalCode, String phone, int divisionId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setInt(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);
        ps.executeUpdate();
    }
}
