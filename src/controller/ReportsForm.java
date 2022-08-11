package controller;

import DAO.AppointmentsDAO;
import DAO.ContactDAO;
import DAO.CustomerDAO;
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
import model.Appointment;
import model.Contact;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This class creates ReportsForm. You use this form to view reports about the number of appointments scheduled by contact
 * and type, all appointments for one contact, and the number of total customers in the database.
 *
 * @author Ashley Jensen
 */
public class ReportsForm implements Initializable {
    public ComboBox ApptTypeComboBox;
    public ComboBox MonthComboBox;
    public Label NumberOfAppointmentsLabel;
    public TableView ApptsTable;
    public TableColumn ApptIdCol;
    public TableColumn CustIdCol;
    public TableColumn UserIdCol;
    public TableColumn ContactIdCol;
    public TableColumn TitleCol;
    public TableColumn LocationCol;
    public TableColumn StartDateTimeCol;
    public TableColumn EndDateTimeCol;
    public TableColumn TypeCol;
    public TableColumn DescriptionCol;
    public ComboBox ContactComboBox;
    public Label CustomerNumbers;
    private ObservableList<String> monthsOfYear = observableArrayList();
    private ObservableList<String> apptTypeList = observableArrayList();
    private ObservableList<Contact> contactList = observableArrayList();
    private ObservableList<Customer> customerList = observableArrayList();

    /**
     * This method initializes ReportsForm.
     * @param url is the location
     * @param resourceBundle is resources used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill combo boxes
        fillComboBoxes();

        // Fill customer table
        getNumOfCustomers();

    }

    /**
     * This method fills the months, appointment type, and contact combo boxes.  Month combo box uses months of year,
     * appointment type combo box uses list of appointment type, contact combo box uses contact id and name.
     */
    private void fillComboBoxes() {
        // Fill month combo box
        monthsOfYear = Appointment.getMonthsOfYear();
        MonthComboBox.setVisibleRowCount(12);
        MonthComboBox.setItems(monthsOfYear);

        // Fill appointment type comob box
        apptTypeList = Appointment.getAllApptTypes();
        ApptTypeComboBox.setVisibleRowCount(5);
        ApptTypeComboBox.setItems(apptTypeList);

        // Fill contact combo box
        contactList = ContactDAO.getContactData();
        ContactComboBox.setVisibleRowCount(5);
        ContactComboBox.setItems(contactList);
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
        Scene scene = new Scene(root, 900, 653);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method takes you to CustomersForm on Customer button click.
     * @param actionEvent on Customer button click
     * @throws IOException if the stage is unable to change scene
     */
    public void CustButtonAction(ActionEvent actionEvent) throws IOException {
        // Load Customers Page
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 585);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
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
     * This method gets input from month/appointment type combo boxes and calls getNumAppts from AppointmentsDAO to
     * return number of appointments matching that month and appointment type, otherwise alerts nothing found.
     * @param actionEvent on OK button click
     */
    public void NumApptsOkayButtonAction(ActionEvent actionEvent) {

        Alert alert;
        String month;
        String apptType;

        // Get field values
        month = (String)MonthComboBox.getSelectionModel().getSelectedItem();
        apptType = (String)ApptTypeComboBox.getSelectionModel().getSelectedItem();


        // If appointment found, show in number of appointments label
        int apptNum = AppointmentsDAO.getNumAppts(month, apptType);
        if (apptNum > 0) {
            NumberOfAppointmentsLabel.setText(String.valueOf(apptNum));
        }
        // Alert user: no appointments found
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointments Found Error");
            alert.setContentText("No appointments found");
            alert.showAndWait();
        }
    }

    /**
     * This method gets the contactId of the chosen contact and gets the appointments scheduled for them, then fills
     * the table with the information.
     * @param actionEvent on OK button click
     */
    public void ContactSchedOkayButtonAction(ActionEvent actionEvent) {
        Alert alert;
        ObservableList<Appointment> contactList = observableArrayList();

        // Get selection
        int contactId = ((Contact)ContactComboBox.getSelectionModel().getSelectedItem()).getId();

        // Get returned contact list by id
        contactList = AppointmentsDAO.getContactApptData(contactId);

        // If contacts returned, fill table, else warn user none found
        if (contactList.size() > 0) {
            // Populate Appointments table on schedule form
            ApptsTable.setItems(contactList);
            ApptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            CustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
            UserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
            ContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            LocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            StartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
            EndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
            TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        }
        else {
            // Alert user: no appointments found
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointments Found Error");
            alert.setContentText("No appointments found");
            alert.showAndWait();
        }
    }

    /**
     * This method shows the number of customers in the database in the CustomerNumbers label.
     */
    public void getNumOfCustomers() {
        // Get customer list size
        customerList = CustomerDAO.getCustomerData();
        int numCustomers = customerList.size();

        // Set label to show number of customers in database
        CustomerNumbers.setText(String.valueOf(numCustomers));
    }
}
