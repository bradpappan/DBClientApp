package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.customerModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerRecordsQuery {

    public static void delete(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }

    public static void select(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
    }

    public static ObservableList<customerModel> getAllCustomers() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<customerModel> allCustomersObservableList = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int customerId = resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String customerAddress = resultSet.getString("Address");
            String customerPostalCode = resultSet.getString("Postal_Code");
            String customerPhone = resultSet.getString("Phone");
            String customerDivisionId = resultSet.getString("Division_ID");
            customerModel customerResult = new customerModel(customerId, customerName, customerAddress, customerPostalCode, customerPhone, customerDivisionId);
            allCustomersObservableList.add(customerResult);
        }
        return allCustomersObservableList;
    }
    
    public static customerModel editCustomer(int customerId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String customerName = null;
        String customerAddress = null;
        String customerPostalCode = null;
        String customerPhone = null;
        String customerDivisionId = null;

        ObservableList<customerModel> allCustomersObservableList = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers WHERE Customer_ID = ?";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerId);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            customerName = resultSet.getString("Customer_Name");
            customerAddress = resultSet.getString("Address");
            customerPostalCode = resultSet.getString("Postal_Code");
            customerPhone = resultSet.getString("Phone");
            customerDivisionId = resultSet.getString("Division_ID");
        }
        
        return new customerModel(customerId, customerName, customerAddress, customerPostalCode, customerPhone, customerDivisionId);
    }
}
