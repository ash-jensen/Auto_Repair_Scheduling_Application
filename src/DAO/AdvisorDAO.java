package DAO;

import javafx.collections.ObservableList;
import model.Advisor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This abstract class is a data access object that gets Advisor data from the database.
 * @author Ashley Jensen
 */
public abstract class AdvisorDAO {
    private static ObservableList<Advisor> advisorList = observableArrayList();
    private static Advisor advisor;
    private static Advisor currentAdvisor;

    /**
     * This method makes an ObservableList of advisors using data from the database. It gets advisor_ID, advisor_Name, and
     * Password and makes a Advisor object with the information. The advisor is then put into ObservableList advisorList which
     * is then returned.
     * @return ObservableList of Advisor objects
     */
    public static ObservableList<Advisor> getAdvisorData() {
        if (!advisorList.isEmpty()) {
            return advisorList;
        }
        try {
            // SQL statement to get all advisors from advisor table
            String sql = "SELECT * FROM advisors";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            advisorList.clear();

            // Set bind variables to create advisor object, add advisor to list
            while (rs.next()) {
                int advisorId = rs.getInt("Advisor_ID");
                String advisorName = rs.getString("Advisor_Name");
                String password = rs.getString("Password");
                Advisor advisor = new Advisor(advisorId, advisorName, password);
                advisorList.add(advisor);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return advisorList from db
        return advisorList;
    }

    /**
     * This method takes in an integer of advisorId and finds and returns the advisor it belongs to.
     * @param advisorIdToFind integer of advisorId to find in the database
     * @return Advisor advisor that matches the advisorId
     */
    public static Advisor getAdvisorById(int advisorIdToFind) {
        try {
            // SQL statement to get advisor from advisors table
            String sql = "SELECT * FROM advisors WHERE Advisor_ID = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, advisorIdToFind);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create advisor object
            rs.next();
            String advisorName = rs.getString("Advisor_Name");
            advisor = new Advisor(advisorIdToFind, advisorName);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return advisor from db
        return advisor;
    }

    /**
     * This method checks the database for a advisorName and password match. It takes in two Strings, the logged-in advisor's
     * advisorName and password, and checks for the advisorName is in the db and if it is, checks for a matching password. If
     * there is a successfull match, returns true. otherwise false.
     * @param loginAdvisorName String variable that holds the advisor's advisorname
     * @param loginPassword String variable that holds the advisor's password
     * @return boolean isMatch
     */
    public static boolean checkLoginInfo(String loginAdvisorName, String loginPassword) {
        Boolean isMatch = false;

        // Connect to db, get password associated with advisorName, check if it matches input password
        try {
            // SQL statement to select row by advisor name
            String sql = "SELECT * FROM advisors WHERE Advisor_Name = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, loginAdvisorName);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // If there is a returned password, set bind variable of password, check for match with input
            if (rs.next()) {
                int advisorId = rs.getInt("Advisor_ID");
                String advisorName = rs.getString("Advisor_Name");
                String password = rs.getString("Password");

                // Check advisorname and password match
                if (loginPassword.equals(password)) {
                    isMatch = true;
                }

                // Build currentAdvisor object
                currentAdvisor = new Advisor(advisorId, advisorName);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return result of isMatch;
        return isMatch;
    }

    /**
     * This method returns the Advisor that is currently logged-in to the application.
     * @return Advisor advisor
     */
    public static Advisor getCurrentAdvisor() {
        return currentAdvisor;
    }

}
