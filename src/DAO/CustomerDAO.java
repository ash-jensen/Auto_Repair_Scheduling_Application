package DAO;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This abstract class is a data access object that gets Customer data from the database.
 *
 * @author Ashley Jensen
 */
public abstract class CustomerDAO {
    private static ObservableList<Customer> customerList = observableArrayList();
    private static Customer customer;

    /**
     * This method makes an ObservableList of customers using data from the database. It gets Customer_ID, Customer_Name,
     * Address, Postal_Code, Phone, and Division_ID and makes a Customer object with the information. The Appointment is
     * then put into ObservableList customerList which is then returned.
     * @return ObservableList customerList
     */
    public static ObservableList<Customer> getCustomerData() {
        try {
            // SQL statement to get all customers from customer table
            String sql = "SELECT * FROM customers";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Clear customerList
            customerList.clear();

            // Set bind variables to create customer object, add customer to list
            while(rs.next()) {
                int custId = rs.getInt("Customer_ID");
                String custName = rs.getString("Customer_Name");
                String custAddress = rs.getString("Address");
                String custPostalCode = rs.getString("Postal_Code");
                String custPhoneNumber = rs.getString("Phone");
                String custVin = rs.getString("VIN");
                int custDivId = rs.getInt("Division_ID");
                Customer cust = new Customer(custId, custName, custAddress, custPostalCode, custPhoneNumber, custVin, custDivId);
                customerList.add(cust);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return customerList from db
        return customerList;
    }

    /**
     * This method adds a customer to the database using information gotten from the Advisor and returns the new customer's
     * customer id.  It takes in name, address, postalCode, phoneNumber and divId.
     * @param name String name to add to the customer in the database
     * @param address String address to add to the customer in the database
     * @param postalCode String postal code to add to the customer in the database
     * @param phoneNumber String phone number to add to the customer in the database
     * @param divId integer division id to add to the customer in the database
     * @return integer custId
     */
    public static int addCustomer(String name, String address, String postalCode, String phoneNumber, String vin, int divId) {
        int custId = 0;

        try {
            // SQL statement to insert customer in customers table
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, VIN, Division_ID) VALUES (?, ?, ?, ?, ?)";

            // Get connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Call prepared statement setter method to assign bind variables value
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setString(5, vin);
            ps.setInt(6, divId);

            // Execute the insert, get returned customer id
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            custId = rs.getInt(1);

        }
        catch (SQLException throwables) {
            // Catch if errors with SQL
            throwables.printStackTrace();
        }

        // return rowsAffected;
        return custId;
    }

    /**
     * This method updates an existing customer in the database using information gotten from the user and returns the number of
     * rows affected.  It takes in customer Id to find in the database and the name, address, postalCode, phoneNumber
     * and divId input by the user.
     * @param name String name to update the customer in the database
     * @param address String address to update the customer in the database
     * @param postalCode String postal code to update the customer in the database
     * @param phoneNumber String phone number to update the customer in the database
     * @param divId integer division id to update the customer in the database
     * @return integer rowsAffected
     */
    public static int updateCustomer(int custId, String name, String address, String postalCode, String phoneNumber, String vin, int divId) {
        Alert alert;
        int rowsAffected = 0;

        alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to edit customer #" + custId + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // SQL statement to update customer with given customer id
                String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, VIN = ?, Division_ID = ? WHERE Customer_ID = ?";

                // Get connection to DB and send over the SQL
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

                // Call prepared statement setter method to assign bind variable values
                ps.setString(1, name);
                ps.setString(2, address);
                ps.setString(3, postalCode);
                ps.setString(4, phoneNumber);
                ps.setString(5, vin);
                ps.setInt(6, divId);
                ps.setInt(7, custId);

                // Execute the update, assign num of rows affected to var to return
                rowsAffected = ps.executeUpdate();
                return rowsAffected;
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        // Return number of rows affected
        return rowsAffected;
    }

    /**
     * This method deletes a row in the database, after confirmation, using a found customer id and returns the number of
     * rows affected. It takes in a customer customerToDelete and gets the id, then finds that customer in the database
     * and deletes the row.
     * @param customerToDelete customer to find and delete
     * @return integer rowsAffected
     */
    public static int deleteCustomer(Customer customerToDelete) {
        Alert alert;
        int rowsAffected = 0;
        int custId = customerToDelete.getId();

        // Confirm user wants to delete customer & delete
        alert = new Alert(Alert.AlertType.CONFIRMATION, "If you delete this customer, any associated appointments will also be deleted." +
                " Are you sure you want to delete customer #" + custId + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // SQL statement to run
                String sqla = "DELETE FROM appointments WHERE Customer_ID = ?";

                // Get a connection to DB and send over the SQL
                PreparedStatement psa = JDBC.getConnection().prepareStatement(sqla);

                // Call prepared statement setter method to assign bind variables value
                psa.setInt(1, custId);

                // Var of updated rows to return
                rowsAffected = psa.executeUpdate();

                // SQL statement to run
                String sql = "DELETE FROM customers WHERE Customer_ID = ?";

                // Get a connection to DB and send over the SQL
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

                // Call prepared statement setter method to assign bind variables value
                ps.setInt(1, custId);

                // Var of updated rows to return
                rowsAffected = ps.executeUpdate();
                return rowsAffected;

            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rowsAffected;
    }

    /**
     * This method finds a customer by their id in the database and returns the customer. It takes in an integer
     * custIdToFind and searches the database, then makes Customer object and returns it.
     * @param custIdToFind the integer custId to find in the database
     * @return Customer customer
     */
    public static Customer getCustomerById(int custIdToFind) {
        try {
            // SQL statement to get all customers from customer table
            String sql = "SELECT * FROM customers WHERE Customer_ID = ?";

            // Get a connection to DB and send over the SQL
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, custIdToFind);

            // Get results of query
            ResultSet rs = ps.executeQuery();

            // Set bind variables to create customer object, add customer to list
            rs.next();
            String custName = rs.getString("Customer_Name");
            String custAddress = rs.getString("Address");
            String custPostalCode = rs.getString("Postal_Code");
            String custPhoneNumber = rs.getString("Phone");
            String custVin = rs.getString("VIN");
            int custDivId = rs.getInt("Division_ID");
            customer = new Customer(custIdToFind, custName, custAddress, custPostalCode, custPhoneNumber, custVin, custDivId);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Return customer from db
        return customer;
    }
}



