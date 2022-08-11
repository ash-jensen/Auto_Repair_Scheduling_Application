package DAO;

import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This abstract class is a data access object that gets Country data from the database.
 *
 * @author Ashley Jensen
 */
public abstract class CountryDAO {
    private static ObservableList<Country> countryList = observableArrayList();

    /**
     * This method makes an ObservableList of Countries using data from the database. It gets Country_ID and Country_Name
     * and makes a Country object with the information. The Country is then put into ObservableList countryList
     * which is then returned.
     * @return ObservableList countrytList
     */
    public static ObservableList<Country> getCountryData() {
        if (!countryList.isEmpty()) {
            return countryList;
        }
        try {
            // SQL statement to get all countries from ccountry table
            String sql = "SELECT * FROM countries";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create country object, add country to list
            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country country = new Country(countryId, countryName);
                countryList.add(country);
            }
        }
        // Catch SQL Exception
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return countryList from db
        return countryList;
    }

    /**
     * This method makes a Country object found by a matching division id using data from the database and then returns it.
     * @return Country country
     */
    public static Country getCountryByDiv(int divId) {
        Country country = null;
        try {
            // SQL statement to get country by division ID
            String sql = "SELECT * FROM countries INNER JOIN first_level_divisions " +
                            "ON countries.Country_ID = first_level_divisions.Country_ID " +
                            "WHERE first_level_divisions.Division_ID = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, divId);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create country object
            rs.next();
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            country = new Country(countryId, countryName);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return customerList from db
        return country;
    }
}
