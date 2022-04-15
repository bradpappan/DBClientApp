package helper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerRecordsQuery {

    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM users WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void select(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
    }


    /*public static int insert(String userName, String password) throws SQLException {
        String sql = "SELECT (User_Name, Password) FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        //ps.setString(1,);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }*/
}
