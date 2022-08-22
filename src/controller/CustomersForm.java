package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.DivisionDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class creates CustomersForm. You use this to view, add, update, and delete customers.
 *
 * @author Ashley Jensen
 */
public class CustomersForm implements Initializable {
    // FXML Vars
    public TableView CustTable;
    public TableColumn CustTableId;
    public TableColumn CustTableName;
    public TableColumn CustTableAddress;
    public TableColumn CustTablePostalCode;
    public TableColumn CustTablePhoneNumber;
    public TableColumn CustTableDivId;
    public ComboBox CustCountryIdComboBox;
    public TextField CustAddressField;
    public TextField CustPostalCodeField;
    public ComboBox CustDivIdComboBox;
    public TextField CustPhoneNumberField;
    public TextField CustIdField;
    public TextField CustNameField;
    public TableColumn CustTableVin;
    public TextField CustConcernField;
    public TextField CustVinField;
    public TextField SearchField;
    private Customer customer;
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String vin;
    private int countryId;
    private int divId;
    private ObservableList<Country> countryList;
    private ObservableList<Division> divisionList;
    private ObservableList<Customer> searchedCustomers;
    private Country selectedCountry;

    /**
     * CONTAINS LAMBDA EXPRESSION: This lambda expression gets a selected customer from CustTable and puts it's
     * information into the CustomerForm fields. It takes parameters of object, oldSelection, and newSelection and if
     * there is a new selection, it fills in the customer fields.
     *
     * This method initializes CustomerForm.
     * @param url is the location
     * @param resourceBundle is resources used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill customer table with customer data
        populateCustTable();

        // Initialize combo boxes
        fillComboBoxes();

        // Lambda function for putting selected customer in Customer Details form
        CustTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                customer = (Customer)newSelection;
                id = customer.getId();
                CustIdField.setText(Integer.toString(id));
                name = customer.getName();
                CustNameField.setText(name);
                address = customer.getAddress();
                CustAddressField.setText(address);
                postalCode = customer.getPostalCode();
                CustPostalCodeField.setText(postalCode);
                phoneNumber = customer.getPhoneNumber();
                CustPhoneNumberField.setText(phoneNumber);
                vin = customer.getVin();
                CustVinField.setText(vin);
                divId = customer.getDivId();
                // Find country that division belongs to and get it's id
                Country matchingCountry = CountryDAO.getCountryByDiv(divId);
                countryId = matchingCountry.getId();
                // Find country in country combo box and set to show
                for (int i = 0; i < CountryDAO.getCountryData().size(); i++) {
                    Country countryCustomer = (Country)CustCountryIdComboBox.getItems().get(i);
                    if(countryId == countryCustomer.getId()) {
                        CustCountryIdComboBox.setValue(countryCustomer);
                        break;
                    }
                }
                // Find division in division combo box and set to show
                for (int i = 0; i < DivisionDAO.getDivData().size(); i++) {
                    Division divCustomer = (Division)CustDivIdComboBox.getItems().get(i);
                    if(divId == divCustomer.getDivId()) {
                        CustDivIdComboBox.setValue(divCustomer);
                        break;
                    }
                }
            }
        });
    }

    /**
     * This method populates CustTable with customer objects constructed from the database.
     */
    private void populateCustTable() {
        // Populate Customer Table on Customers form
        CustTable.setItems(CustomerDAO.getCustomerData());
        CustTableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        CustTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        CustTableAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        CustTablePostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        CustTablePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        CustTableVin.setCellValueFactory(new PropertyValueFactory<>("vin"));
        CustTableDivId.setCellValueFactory(new PropertyValueFactory<>("divId"));
    }

    /**
     * This method fills the country and division combo boxes.  Country combo box uses country name and division combo box
     * uses division name.
     */
    private void fillComboBoxes() {
        // Fill country combo box
        countryList = CountryDAO.getCountryData();
        CustCountryIdComboBox.setVisibleRowCount(5);
        CustCountryIdComboBox.setItems(countryList);

        // Fill division combo box
        divisionList = DivisionDAO.getDivData();
        CustDivIdComboBox.setVisibleRowCount(5);
        CustDivIdComboBox.setItems(divisionList);
    }

    /**
     * This method takes you to AppointmentForm on Appointment button click.
     * @param actionEvent on Appointment button click
     * @throws IOException if the stage is unable to change scene
     */
    public void AppointmentButtonAction(ActionEvent actionEvent) throws IOException {
        // Load Schedule Page
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 624);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method takes you to ReportsForm on Reports button click.
     * @param actionEvent on Reports button click
     * @throws IOException if the stage is unable to change scene
     */
    public void ReportsButtonAction(ActionEvent actionEvent) throws IOException {
        // Load Schedule Page
        Parent root = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 653);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method calls emptyFieldCheck, if none empty it gets info from user input, adds the customer, then updates
     * the customer table, otherwise informs user of error.
     * @param actionEvent on Add button click
     */
    public void AddButtonAction(ActionEvent actionEvent) {
        Alert alert;

        // Check that all fields/combo boxes have been filled out, add customer
        if (emptyFieldCheck()) {
            // Get new field values
            name = CustNameField.getText();
            address = CustAddressField.getText();
            postalCode = CustPostalCodeField.getText();
            phoneNumber = CustPhoneNumberField.getText();
            vin = CustVinField.getText();
            divId = ((Division)CustDivIdComboBox.getSelectionModel().getSelectedItem()).getDivId();


            // If custId > 0 returned, repopulate table and inform user of success
            int custId = CustomerDAO.addCustomer(name, address, postalCode, phoneNumber, vin, divId);
            if (custId > 0) {
                populateCustTable();
                // Confirm customer added
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Customer");
                alert.setContentText("Customer #" + custId + " has been added.");
                alert.showAndWait();

                // Clear form fields
                ClearButtonAction(null);
            }
            // Alert user: customer has not been added
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Customer Error");
                alert.setContentText("Error: Customer has NOT been added");
                alert.showAndWait();
            }
        }
    }

    /**
     * This method calls emptyFieldCheck, if none empty, it gets info from user input, updates selected customer, then
     * updates the customer table, otherwise informs user of error.
     * @param actionEvent on Update button click
     */
    public void UpdateButtonAction(ActionEvent actionEvent)  {
        Alert alert;

        // Check that all fields/combo boxes have been filled out, update customer
        if (emptyFieldCheck()) {
            // Get new field values
            name = CustNameField.getText();
            address = CustAddressField.getText();
            postalCode = CustPostalCodeField.getText();
            phoneNumber = CustPhoneNumberField.getText();
            vin = CustVinField.getText();
            divId = ((Division)CustDivIdComboBox.getSelectionModel().getSelectedItem()).getDivId();

            // If customer updated in database, repopulate table and inform user of success
            if (CustomerDAO.updateCustomer(id, name, address, postalCode, phoneNumber, vin, divId) > 0) {
                // Repopulate table
                populateCustTable();

                // Confirm customer updated
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Customer");
                alert.setContentText("Customer #" + id + " has been updated.");
                alert.showAndWait();

                // Clear form fields
                ClearButtonAction(null);
            }
            // Alert user: customer has not been added
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Customer Error");
                alert.setContentText("Error: Customer has NOT been updated");
                alert.showAndWait();
            }
        }
    }

    /**
     * This method checks that a customer is selected, calls deleteCustomer from CustomerDAO, then informs user of
     * successful delete or otherwise.
     * @param actionEvent on Delete button click
     */
    public void DeleteButtonAction(ActionEvent actionEvent)  {
        Alert alert;

        // Get selected customer from table
        Customer selected = (Customer)CustTable.getSelectionModel().getSelectedItem();

        // If nothing selected, alert user to select customer
        if (selected == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Error");
            alert.setContentText("Please select a customer to delete.");
            alert.showAndWait();
            return;
        }
        else {
            // If delete is unsuccessful,notify user
            if (CustomerDAO.deleteCustomer(selected) <= 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete Error");
                alert.setContentText("Delete unsuccessful.");
                alert.showAndWait();
            }
            else {
                ClearButtonAction(null);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Successful");
                alert.setContentText("Customer #" + id + " has been deleted.");
                alert.showAndWait();
                populateCustTable();
            }
        }
    }

    /**
     * This method clears all form fields
     * @param actionEvent on Clear button click
     */
    public void ClearButtonAction(ActionEvent actionEvent) {
        CustIdField.clear();
        CustNameField.clear();
        CustAddressField.clear();
        CustPhoneNumberField.clear();
        CustVinField.clear();
        CustCountryIdComboBox.getSelectionModel().clearSelection();
        CustDivIdComboBox.getItems().clear();
        CustPostalCodeField.clear();
    }

    /**
     * This method exits the program, after confirmation, on Exit button click.
     * @param actionEvent on Exit button click
     */
    public void ExitButtonAction(ActionEvent actionEvent) {
        Alert alert;

        // Confirm user wants to exit program
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the program?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            // Close Program
            Platform.exit();
        }
    }

    /**
     * This method checks that a country has been selected, then fills the division combo box with divisions in the
     * selected country.
     * @param actionEvent on country combo box selection
     */
    public void countrySelectedAction(ActionEvent actionEvent) {
        selectedCountry = (Country) CustCountryIdComboBox.getSelectionModel().getSelectedItem();
        if (selectedCountry == null) {
            return;
        }
        int countryId = selectedCountry.getId();

        if (divisionList == null) {
            divisionList = DivisionDAO.getDivsByCountry(countryId);
            CustDivIdComboBox.setVisibleRowCount(5);
            CustDivIdComboBox.setItems(divisionList);
        }
        else {
            divisionList.clear();
            divisionList = DivisionDAO.getDivsByCountry(countryId);
            CustDivIdComboBox.setVisibleRowCount(5);
            CustDivIdComboBox.setItems(divisionList);
        }
    }

    /**
     * This method checks all form fields to make sure they hold value, returns true if all full, false otherwise.
     * @return boolean hasText
     */
    public boolean emptyFieldCheck() {
        boolean hasText = true;
        if ((CustNameField.getText().isBlank()) || (CustPhoneNumberField.getText().isBlank())
            || (CustVinField.getText().isBlank()) || (CustAddressField.getText().isBlank())
            || (CustCountryIdComboBox.getSelectionModel().isEmpty()) || (CustDivIdComboBox.getSelectionModel().isEmpty())
            || (CustPostalCodeField.getText().isBlank())) {
            Alert alert;

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setContentText("Please make sure all fields are complete.");
            alert.show();
            hasText = false;
        }
        return hasText;
    }

    /**
     * This method searches executes a search for customer name or customer id number.
     * @param actionEvent on enter in search field
     */
    public void SearchCustNameAction(ActionEvent actionEvent) {
        // Get text from search box
        String toSearch = SearchField.getText();

        // Search part list for Part Name
        searchedCustomers = CustomerDAO.getCustomersByName(toSearch);

        // if string match not found, try changing to int and search for Part ID
        if(searchedCustomers.size() == 0) {
            try{
                int custId = Integer.parseInt(toSearch);
                Customer foundCustomer = CustomerDAO.getCustomerById(custId);
                if (foundCustomer != null) {
                    searchedCustomers.add(foundCustomer);
                }
            }
            catch (NumberFormatException e) {
                //ignore
            }
        }


        // If searched item not found, alert user and reload table
        if (searchedCustomers.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Found");
            alert.setContentText("Customer not found.");
            alert.showAndWait();

            // Populate Main Form Part Table
            populateCustTable();
            return;
        }

            // Set part table to display list of matching parts
            CustTable.setItems(searchedCustomers);
            CustTableId.setCellValueFactory(new PropertyValueFactory<>("id"));
            CustTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
            CustTableAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            CustTablePostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            CustTablePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            CustTableVin.setCellValueFactory(new PropertyValueFactory<>("vin"));
            CustTableDivId.setCellValueFactory(new PropertyValueFactory<>("divId"));
    }
}
