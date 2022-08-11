package DAO;

import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This abstract class is a data access object that gets Division data from the database.
 *
 * @author Ashley Jensen
 */
public abstract class DivisionDAO {
    private static ObservableList<Division> divList = observableArrayList();
    private static ObservableList<Division> divsByCountry = observableArrayList();

    /**
     * This method makes an ObservableList of Divisions using data from the database. It gets Division_ID, Division,
     * and Country_ID and makes a Division object with the information. The Division is then put into ObservableList
     * divList which is then returned.
     * @return ObservableList divList
     */
    public static ObservableList<Division> getDivData() {
        if (!divList.isEmpty()) {
            return divList;
        }
        try {
            // SQL statement to get all customers from customer table
            String sql = "SELECT * FROM first_level_divisions";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create customer object, add customer to list
            while (rs.next()) {
                int divId = rs.getInt("Division_ID");
                String divName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Division division = new Division(divId, divName, countryId);
                divList.add(division);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return customerList from db
        return divList;
    }

    /**
     * This method makes an Observable list of Divisions found with a matching Country_ID using data from the database and
     * then returns it.
     * @param countryIdToFind integer countryId passed in to search for in database
     * @return ObservableList divsByCountry
     */
    public static ObservableList<Division> getDivsByCountry (int countryIdToFind) {
        try {
            // SQL statement to get all customers from customer table
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Call prepared statement setter method to assign bind variables value
            ps.setInt(1, countryIdToFind);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create division object, add division to list
            while (rs.next()) {
                int  divId= rs.getInt("Division_ID");
                String divName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Division division = new Division(divId, divName, countryId);
                divsByCountry.add(division);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return customerList from db
        return divsByCountry;
    }
}
