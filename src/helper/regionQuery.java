package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class regionQuery {


    public static String getCountry(String divisionId) throws SQLException {
        ResultSet rs;
        String countryName = null;
        String sql = "SELECT countries.Country FROM first_level_divisions JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, divisionId);
        rs = ps.executeQuery();
        while (rs.next()) {
            countryName = rs.getString("Country");
        }
        return countryName;
    }


    public static String getDivision(String divisionId) throws SQLException {
        ResultSet rs;
        String divisionName = null;
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, divisionId);
        rs = ps.executeQuery();
        while (rs.next()) {
            divisionName = rs.getString("Division");
        }
        return divisionName;
    }
}
