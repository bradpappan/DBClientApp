package model;

/**
 * This class holds the model for login
 */
public class loginModel {
    private String username;
    private String password;

    public void user(int userId, String userName, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @return gets the userName
     */
    public String getUserName() {
        return username;
    }

    /**
     *
     * @return gets the password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param username sets the userName
     */
    public void setUserName(String username) {
        this.username = username;
    }

    /**
     *
     * @param password sets the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
