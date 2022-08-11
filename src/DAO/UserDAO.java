package DAO;

import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This abstract class is a data access object that gets User data from the database.
 * @author Ashley Jensen
 */
public abstract class UserDAO {
    private static ObservableList<User> userList = observableArrayList();
    private static User user;
    private static User currentUser;

    /**
     * This method makes an ObservableList of users using data from the database. It gets User_ID, User_Name, and
     * Password and makes a User object with the information. The user is then put into ObservableList userList which
     * is then returned.
     * @return ObservableList of User objects
     */
    public static ObservableList<User> getUserData() {
        if (!userList.isEmpty()) {
            return userList;
        }
        try {
            // SQL statement to get all users from user table
            String sql = "SELECT * FROM users";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            userList.clear();

            // Set bind variables to create user object, add user to list
            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                User user = new User(userId, userName, password);
                userList.add(user);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return userList from db
        return userList;
    }

    /**
     * This method takes in an integer of userId and finds and returns the user it belongs to.
     * @param userIdToFind integer of userId to find in the database
     * @return User user that matches the userId
     */
    public static User getUserById(int userIdToFind) {
        try {
            // SQL statement to get user from users table
            String sql = "SELECT * FROM users WHERE User_ID = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, userIdToFind);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create user object
            rs.next();
            String userName = rs.getString("User_Name");
            user = new User(userIdToFind, userName);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return user from db
        return user;
    }

    /**
     * This method checks the database for a userName and password match. It takes in two Strings, the logged-in user's
     * username and password, and checks for the username is in the db and if it is, checks for a matching password. If
     * there is a successfull match, returns true. otherwise false.
     * @param loginUserName String variable that holds the user's username
     * @param loginPassword String variable that holds the user's password
     * @return boolean isMatch
     */
    public static boolean checkLoginInfo(String loginUserName, String loginPassword) {
        Boolean isMatch = false;

        // Connect to db, get password associated with username, check if it matches input password
        try {
            // SQL statement to select row by user name
            String sql = "SELECT * FROM users WHERE User_Name = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, loginUserName);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // If there is a returned password, set bind variable of password, check for match with input
            if (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                // Check username and password match
                if (loginPassword.equals(password)) {
                    isMatch = true;
                }

                // Build currentUser object
                currentUser = new User(userId, userName);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return result of isMatch;
        return isMatch;
    }

    /**
     * This method returns the User that is currently logged-in to the application.
     * @return User user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

}
