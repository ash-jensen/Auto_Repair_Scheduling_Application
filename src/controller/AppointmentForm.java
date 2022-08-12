package controller;

import DAO.AppointmentsDAO;
import DAO.ContactDAO;
import DAO.CustomerDAO;
import DAO.UserDAO;
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
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;
// THIS IS THE NEW PROJECT

/**
 * This class creates AppointmentForm. You use this to view, add, update, and delete appointments.
 *
 * @author Ashley Jensen
 */
public class AppointmentForm implements Initializable {
    // FXML Vars
    public TableView AllApptsTable;
    public TableColumn AllApptIdCol;
    public TableColumn AllCustIdCol;
    public TableColumn AllUserIdCol;
    public TableColumn AllContactIdCol;
    public TableColumn AllTitleCol;
    public TableColumn AllLocationCol;
    public TableColumn AllStartDateTimeCol;
    public TableColumn AllEndDateTimeCol;
    public TableColumn AllTypeCol;
    public TableColumn AllDescriptionCol;
    public TableView CurrMonthTable;
    public TableColumn CurrMonthApptIdCol;
    public TableColumn CurrMonthCustIdCol;
    public TableColumn CurrMonthUserIdCol;
    public TableColumn CurrMonthContactIdCol;
    public TableColumn CurrMonthTitleCol;
    public TableColumn CurrMonthLocationCol;
    public TableColumn CurrMonthStartDateTimeCol;
    public TableColumn CurrMonthEndDateTimeCol;
    public TableColumn CurrMonthTypeCol;
    public TableColumn CurrMonthDescriptionCol;
    public TableView CurrWeekTable;
    public TableColumn CurrWeekApptIdCol;
    public TableColumn CurrWeekCustIdCol;
    public TableColumn CurrWeekUserIdCol;
    public TableColumn CurrWeekContactIdCol;
    public TableColumn CurrWeekTitleCol;
    public TableColumn CurrWeekLocationCol;
    public TableColumn CurrWeekStartDateTimeCol;
    public TableColumn CurrWeekEndDateTimeCol;
    public TableColumn CurrWeekTypeCol;
    public TableColumn CurrWeekDescriptionCol;
    public TextField ApptIdField;
    public TextField ApptTitleField;
    public ComboBox ContactComboBox;
    public ComboBox UserComboBox;
    public ComboBox CustomerComboBox;
    public ComboBox StartTimeComboBox;
    public ComboBox EndTimeComboBox;
    public TextField LocationField;
    public javafx.scene.control.DatePicker DatePicker;
    public TextField DescriptionField;
    public ComboBox ApptTypeComboBox;
    public Button ExitButton;
    private Appointment appointment;
    private int apptId;
    private int custId;
    private int userId;
    private int contactId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    ObservableList<Customer> customerList = observableArrayList();
    ObservableList<Contact> contactList = observableArrayList();
    ObservableList<User> userList = observableArrayList();
    ObservableList<String> apptTypeList = observableArrayList();

    /**
     * CONTAINS MULTIPLE LAMBDA EXPRESSIONS: The first three lambda expressions gets a selected appointment from AllApptsTable, CurrMonthTable,
     * or CurrWeekTable and put it's information into the AppointmentForm fields. they takes parameters of object,
     * oldSelection, and newSelection and if there is a new selection, it fills in the appointment fields.
     * The fourth lambda expression takes in an event from Exit button click and confirms the user would like to quit,
     * then closes the program.
     *
     * This method initializes AppointmentForm.
     * @param url is the location
     * @param resourceBundle is resources used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill All Appointment tables
        populateApptsTables();

        // Fill combo boxes
        fillComboBoxes();

        // Lambda function for putting selected customer in Customer Details form
        AllApptsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                appointment = (Appointment)newSelection;
                apptId = appointment.getId();
                ApptIdField.setText(Integer.toString(apptId));
                title = appointment.getTitle();
                ApptTitleField.setText(title);
                custId = appointment.getCustId();
                Customer customer = CustomerDAO.getCustomerById(custId);
                CustomerComboBox.setValue(customer);
                contactId = appointment.getContactId();
                Contact contact = ContactDAO.getContactById(contactId);
                ContactComboBox.setValue(contact);
                userId = appointment.getUserId();
                User user = UserDAO.getUserById(userId);
                UserComboBox.setValue(user);
                type = appointment.getType();
                ApptTypeComboBox.setValue(type);
                description = appointment.getDescription();
                DescriptionField.setText(description);
                date = (appointment.getStartDateTime().toLocalDateTime()).toLocalDate();
                DatePicker.setValue(null);
                DatePicker.setValue(date);
                startTime = (appointment.getStartDateTime().toLocalDateTime()).toLocalTime();
                StartTimeComboBox.setValue(startTime);
                endTime = (appointment.getEndDateTime().toLocalDateTime()).toLocalTime();
                EndTimeComboBox.setValue(endTime);
                location = appointment.getLocation();
                LocationField.setText(location);
            }
        });
        CurrMonthTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                appointment = (Appointment)newSelection;
                apptId = appointment.getId();
                ApptIdField.setText(Integer.toString(apptId));
                title = appointment.getTitle();
                ApptTitleField.setText(title);
                custId = appointment.getCustId();
                Customer customer = CustomerDAO.getCustomerById(custId);
                CustomerComboBox.setValue(customer);
                contactId = appointment.getContactId();
                Contact contact = ContactDAO.getContactById(contactId);
                ContactComboBox.setValue(contact);
                userId = appointment.getUserId();
                User user = UserDAO.getUserById(userId);
                UserComboBox.setValue(user);
                type = appointment.getType();
                ApptTypeComboBox.setValue(type);
                description = appointment.getDescription();
                DescriptionField.setText(description);
                date = (appointment.getStartDateTime().toLocalDateTime()).toLocalDate();
                DatePicker.setValue(date);
                startTime = (appointment.getStartDateTime().toLocalDateTime()).toLocalTime();
                StartTimeComboBox.setValue(startTime);
                endTime = (appointment.getEndDateTime().toLocalDateTime()).toLocalTime();
                EndTimeComboBox.setValue(endTime);
                location = appointment.getLocation();
                LocationField.setText(location);
            }
        });
        CurrWeekTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                appointment = (Appointment)newSelection;
                apptId = appointment.getId();
                ApptIdField.setText(Integer.toString(apptId));
                title = appointment.getTitle();
                ApptTitleField.setText(title);
                custId = appointment.getCustId();
                Customer customer = CustomerDAO.getCustomerById(custId);
                CustomerComboBox.setValue(customer);
                contactId = appointment.getContactId();
                Contact contact = ContactDAO.getContactById(contactId);
                ContactComboBox.setValue(contact);
                userId = appointment.getUserId();
                User user = UserDAO.getUserById(userId);
                UserComboBox.setValue(user);
                type = appointment.getType();
                ApptTypeComboBox.setValue(type);
                description = appointment.getDescription();
                DescriptionField.setText(description);
                date = (appointment.getStartDateTime().toLocalDateTime()).toLocalDate();
                DatePicker.setValue(date);
                startTime = (appointment.getStartDateTime().toLocalDateTime()).toLocalTime();
                StartTimeComboBox.setValue(startTime);
                endTime = (appointment.getEndDateTime().toLocalDateTime()).toLocalTime();
                EndTimeComboBox.setValue(endTime);
                location = appointment.getLocation();
                LocationField.setText(location);
            }
        });

        // Exit button action lambda expression
        ExitButton.setOnAction( event ->
        {
            Alert alert;

            // Confirm user wants to exit program
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit the program?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                // Close Program
                Platform.exit();
            }

        } );
    }

    /**
     * This method populates all appointments, current month appointments, and current week appointments tables on
     * AppointmentForm under their respective tabs, with appointment objects constructed from the database.
     */
    private void populateApptsTables() {
        // Populate All Appointments table on schedule form
        AllApptsTable.setItems(AppointmentsDAO.getAllApptData());
        AllApptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        AllCustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        AllUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        AllContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        AllTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        AllLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        AllStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        AllEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        AllTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        AllDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Populate Current Month table on schedule form
        CurrMonthTable.setItems(AppointmentsDAO.getCurrMonthApptData());
        CurrMonthApptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        CurrMonthCustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        CurrMonthUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        CurrMonthContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        CurrMonthTitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        CurrMonthLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        CurrMonthStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        CurrMonthEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        CurrMonthTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        CurrMonthDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Populate Current Week table on schedule form
        CurrWeekTable.setItems(AppointmentsDAO.getCurrWeekApptData());
        CurrWeekApptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        CurrWeekCustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        CurrWeekUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        CurrWeekContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        CurrWeekTitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        CurrWeekLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        CurrWeekStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        CurrWeekEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        CurrWeekTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        CurrWeekDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    /**
     * This method fills the customer, contact, user, and start/end time combo boxes.  Customer combo box uses customer
     * id and name, contact combo box uses contact id and name, user combo box uses user id and name, and appoint type
     * uses appointment type list. The start and end time combo boxes show the eastern time business hours converted
     * into local time.
     */
    private void fillComboBoxes() {
        // Fill customer combo box
        customerList = CustomerDAO.getCustomerData();
        CustomerComboBox.setVisibleRowCount(5);
        CustomerComboBox.setItems(customerList);

        // Fill contact combo box
        contactList = ContactDAO.getContactData();
        ContactComboBox.setVisibleRowCount(5);
        ContactComboBox.setItems(contactList);

        // Fill user combo box
        userList = UserDAO.getUserData();
        UserComboBox.setVisibleRowCount(5);
        UserComboBox.setItems(userList);

        // Fill start time combo box
        LocalTime startLocal = Appointment.updateDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0))).toLocalTime();
        LocalTime endLocal = Appointment.updateDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0))).toLocalTime();
        LocalTime start = startLocal;
        LocalTime end = endLocal.minusMinutes(10);
        while(start.isBefore(end.plusSeconds(1))) {
            StartTimeComboBox.getItems().add(start);
            start = start.plusMinutes(10);
        }
        StartTimeComboBox.getSelectionModel().select(startLocal);

        // Fill end time combo box
        start = startLocal.plusMinutes(10);
        end = endLocal;
        while(start.isBefore(end.plusSeconds(1))) {
            EndTimeComboBox.getItems().add(start);
            start = start.plusMinutes(10);
        }
        EndTimeComboBox.getSelectionModel().select(startLocal.plusMinutes(10));

        // Fill appointment type combo box
        apptTypeList = Appointment.getAllApptTypes();
        ApptTypeComboBox.setVisibleRowCount(5);
        ApptTypeComboBox.setItems(apptTypeList);

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
        Scene scene = new Scene(root, 1100, 585);
        stage.setTitle("Customers");
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
     * This method calls emptyFieldCheck, if none empty it gets info from user input, adds the appointment, then updates
     * the appointment tables, otherwise informs user of error.
     * @param actionEvent on Add button click
     */
    public void AddApptButtonAction(ActionEvent actionEvent) {
        Alert alert;

        // Check that all fields/combo boxes have been filled out, add customer
        if (emptyFieldCheck()) {
            // Get new field values
            title = ApptTitleField.getText();
            custId = ((Customer)CustomerComboBox.getSelectionModel().getSelectedItem()).getId();
            contactId = ((Contact)ContactComboBox.getSelectionModel().getSelectedItem()).getId();
            userId = ((User)UserComboBox.getSelectionModel().getSelectedItem()).getId();
            type = (String)ApptTypeComboBox.getSelectionModel().getSelectedItem();
            description = DescriptionField.getText();
            date = DatePicker.getValue();
            startTime = (LocalTime)StartTimeComboBox.getSelectionModel().getSelectedItem();
            endTime = (LocalTime)EndTimeComboBox.getSelectionModel().getSelectedItem();
            startTimestamp = Timestamp.valueOf(LocalDateTime.of(date, startTime));
            endTimestamp = Timestamp.valueOf(LocalDateTime.of(date, endTime));
            location = LocationField.getText();

            // If apptId > 0 returned, repopulate table and inform user of success
            int apptId = AppointmentsDAO.addAppt(custId, userId, contactId, title, description, location, type, startTimestamp, endTimestamp);
            if (apptId > 0) {
                populateApptsTables();
                // Confirm customer added
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Customer");
                alert.setContentText("Appointment #" + apptId + " has been added.");
                alert.showAndWait();

                // Clear form fields
                ClearButtonAction(null);
            }
            // Alert user: customer has not been added
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Appointment Error");
                alert.setContentText("Error: Appointment has NOT been added");
                alert.showAndWait();
            }
        }
    }

    /**
     * This method calls emptyFieldCheck, if none empty, it gets info from user input, updates selected appointment, then
     * updates the appointment tables, otherwise informs user of error.
     * @param actionEvent on Update button click
     */
    public void UpdateApptButtonAction(ActionEvent actionEvent) {
        Alert alert;

        // Check that all fields/combo boxes have been filled out, update appointment
        if (emptyFieldCheck()) {
            title = ApptTitleField.getText();
            custId = ((Customer)CustomerComboBox.getSelectionModel().getSelectedItem()).getId();
            contactId = ((Contact)ContactComboBox.getSelectionModel().getSelectedItem()).getId();
            userId = ((User)UserComboBox.getSelectionModel().getSelectedItem()).getId();
            type = (String)ApptTypeComboBox.getSelectionModel().getSelectedItem();
            description = DescriptionField.getText();
            date = DatePicker.getValue();
            startTime = (LocalTime)StartTimeComboBox.getSelectionModel().getSelectedItem();
            endTime = (LocalTime)EndTimeComboBox.getSelectionModel().getSelectedItem();
            startTimestamp = Timestamp.valueOf(LocalDateTime.of(date, startTime));
            endTimestamp = Timestamp.valueOf(LocalDateTime.of(date, endTime));
            location = LocationField.getText();

            // If appointment updated in database, repopulate table and inform user of success
            if (AppointmentsDAO.updateAppt(apptId, custId, userId, contactId, title, description, location, type, startTimestamp, endTimestamp) > 0) {
                // Repopulate table
                populateApptsTables();

                // Confirm customer updated
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Appointment");
                alert.setContentText("Appointment #" + apptId + " has been updated.");
                alert.showAndWait();

                // Clear form fields
                ClearButtonAction(null);
            }
            // Alert user: customer has not been added
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Error");
                alert.setContentText("Error: Appointment has NOT been updated");
                alert.showAndWait();
            }
        }
    }

    /**
     * This method checks that an appointment is selected, calls deleteAppt from AppointmentsDAO, then informs user of
     * successful delete or otherwise.
     * @param actionEvent on Delete button click
     */
    public void DeleteApptButtonAction(ActionEvent actionEvent) {
        Alert alert;

        // If nothing selected, alert user to select appointment
        if (appointment == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Error");
            alert.setContentText("Please select an appointment to delete.");
            alert.showAndWait();
            return;
        }
        else {
            // If delete is unsuccessful,notify user
            if (AppointmentsDAO.deleteAppt(appointment) <= 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete Error");
                alert.setContentText("Delete unsuccessful.");
                alert.showAndWait();
            }
            else {
                ClearButtonAction(null);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Successful");
                alert.setContentText("Appointment #" + apptId + " of type: " + type + ", has been deleted.");
                alert.showAndWait();
                populateApptsTables();
            }
        }
    }

    /**
     * This method clears all form fields
     * @param actionEvent on Clear button click
     */
    public void ClearButtonAction(ActionEvent actionEvent) {
        // Clear fields
        ApptIdField.clear();
        ApptTitleField.clear();
        CustomerComboBox.getSelectionModel().clearSelection();
        ContactComboBox.getSelectionModel().clearSelection();
        UserComboBox.getSelectionModel().clearSelection();
        ApptTypeComboBox.getSelectionModel().clearSelection();
        DescriptionField.clear();
        DatePicker.getEditor().clear();
        StartTimeComboBox.getSelectionModel().clearSelection();
        EndTimeComboBox.getSelectionModel().clearSelection();
        LocationField.clear();
    }

    /**
     * This method checks all form fields to make sure they hold value, returns true if all full, false otherwise.
     * @return boolean hasText
     */
    public boolean emptyFieldCheck() {
        boolean hasText = true;
        if ((ApptTitleField.getText().isBlank())
                || (CustomerComboBox.getValue() == null)
                || (ContactComboBox.getValue() == null)
                || (UserComboBox.getValue() == null)
                || (ApptTypeComboBox.getValue() == null)
                || (DescriptionField.getText().isBlank())
                || (DatePicker.getValue() == null)
                || (StartTimeComboBox.getValue() == null)
                || (EndTimeComboBox.getValue() == null)
                || (LocationField.getText().isBlank())
                ) {
            Alert alert;

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setContentText("Please make sure all fields are complete.");
            alert.show();
            hasText = false;
        }
        return hasText;
    }
}
