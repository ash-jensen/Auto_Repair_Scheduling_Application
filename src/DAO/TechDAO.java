package DAO;

import javafx.collections.ObservableList;
import model.Tech;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This abstract class is a data access object that gets Tech data from the database.
 *
 * @author Ashley Jensen
 */
public abstract class TechDAO {
    private static ObservableList<Tech> techList = observableArrayList();
    private static Tech tech;

    /**
     * This method makes an ObservableList of techs using data from the database. It gets tech_Id, tech_Name,
     * and Email, and makes a Tech object with the information. The Tech is then put into ObservableList techList
     * which is then returned.
     * @return ObservableList techList
     */
    public static ObservableList<Tech> getTechData() {
        // If tech list already filled, return techList
        /*if (!techList.isEmpty()) {
            return techList;
        }

         */
        techList.clear();
        try {
            // SQL statement to get all techs from techs table
            String sql = "SELECT * FROM technicians";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create tech object, add tech to list
            while (rs.next()) {
                int techId = rs.getInt("Tech_ID");
                String techName = rs.getString("Tech_Name");
                String techType = rs.getString("Tech_Type");
                String email = rs.getString("Email");
                Tech tech = new Tech(techId, techName, techType, email);
                techList.add(tech);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return techList from db
        return techList;
    }

    /**
     * This method makes and returns a Tech found by techId using data from the database. It gets tech_Name,
     * and Email, and makes a Tech object with the information and is then returned.
     * @return Tech tech
     */
    public static Tech getTechById(int techIdToFind) {
        try {
            // SQL statement to get tech from techs table
            String sql = "SELECT * FROM technicians WHERE Tech_ID = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, techIdToFind);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create tech object
            rs.next();
            String techName = rs.getString("Tech_Name");
            String techType = rs.getString("Tech_Type");
            String email = rs.getString("Email");
            tech = new Tech(techIdToFind, techName, techType, email);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return tech from db
        return tech;
    }

    public static ObservableList<Tech> getTechByType(String type) {
        techList.clear();
        try {
            // SQL statement to get tech from techs table
            String sql = "SELECT * FROM technicians WHERE Tech_Type = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, type);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create tech object
            while (rs.next()) {
                int techId = rs.getInt("Tech_ID");
                String techName = rs.getString("Tech_Name");
                String techType = rs.getString("Tech_Type");
                String email = rs.getString("Email");
                Tech tech = new Tech(techId, techName, techType, email);
                techList.add(tech);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return tech from db
        return techList;
    }
}
