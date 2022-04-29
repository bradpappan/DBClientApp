package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class holds the queries for pulling the country data
 */
public class regionQuery {

    /**
     *
     * @param divisionId passes in the divisionId
     * @return gets the country
     * @throws SQLException
     */
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

    /**
     *
     * @param divisionId passes in the divisionId
     * @return gets the division names
     * @throws SQLException
     */
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
