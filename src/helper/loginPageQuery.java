package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.loginPageModel;




public class loginPageQuery {


    // Need to compare with data entered on loginPageController
    public select(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = '" + username + "'Password = '" + password + "'";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        if (rs.next()) {

        }
        else {

        }
    }


}
