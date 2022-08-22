package controller;

import DAO.AppointmentsDAO;
import DAO.TechDAO;
import DAO.CustomerDAO;
import DAO.AdvisorDAO;
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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.security.Provider;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

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
    public TableColumn AllAdvisorIdCol;
    public TableColumn AllTechIdCol;
    public TableColumn AllStartDateTimeCol;
    public TableColumn AllEndDateTimeCol;
    public TableView CurrMonthTable;
    public TableColumn CurrMonthApptIdCol;
    public TableColumn CurrMonthCustIdCol;
    public TableColumn CurrMonthAdvisorIdCol;
    public TableColumn CurrMonthTechIdCol;
    public TableColumn CurrMonthStartDateTimeCol;
    public TableColumn CurrMonthEndDateTimeCol;
    public TableView CurrWeekTable;
    public TableColumn CurrWeekApptIdCol;
    public TableColumn CurrWeekCustIdCol;
    public TableColumn CurrWeekAdvisorIdCol;
    public TableColumn CurrWeekTechIdCol;
    public TableColumn CurrWeekStartDateTimeCol;
    public TableColumn CurrWeekEndDateTimeCol;
    public TextField ApptIdField;
    public TextField ApptTitleField;
    public ComboBox TechComboBox;
    public ComboBox AdvisorComboBox;
    public ComboBox CustomerComboBox;
    public ComboBox StartTimeComboBox;
    public ComboBox EndTimeComboBox;
    public javafx.scene.control.DatePicker DatePicker;
    public Button ExitButton;
    public TableColumn AllConcernCol;
    public TableColumn CurrMonthConcernCol;
    public TableColumn CurrWeekConcernCol;
    public TextField ApptConcernsField;
    public TableColumn AllConcernsCol;
    public TableColumn CurrMonthConcernsCol;
    public TableColumn CurrWeekConcernsCol;
    public RadioButton ServiceRB;
    public RadioButton DiagnosticRB;
    public Label ConcernLabel;
    public TableColumn AllServiceCol;
    public TableColumn CurrMonthServiceCol;
    public TableColumn CurrWeekServiceCol;
    public ComboBox ApptServiceTypeComboBox;
    // Vars
    private Appointment appointment;
    private int apptId;
    private int custId;
    private int advisorId;
    private int techId;
    private String type;
    private String concerns;
    private String service;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    ObservableList<Customer> customerList = observableArrayList();
    ObservableList<Tech> techList = observableArrayList();
    ObservableList<Advisor> advisorList = observableArrayList();
    ObservableList<String> serviceTypes = observableArrayList();

    /**
     * CONTAINS MULTIPLE LAMBDA EXPRESSIONS: The first three lambda expressions gets a selected appointment from AllApptsTable, CurrMonthTable,
     * or CurrWeekTable and put it's information into the AppointmentForm fields. they takes parameters of object,
     * oldSelection, and newSelection and if there is a new selection, it fills in the appointment fields.
     * The fourth lambda expression takes in an event from Exit button click and confirms the Advisor would like to quit,
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
                custId = appointment.getCustId();
                Customer customer = CustomerDAO.getCustomerById(custId);
                CustomerComboBox.setValue(customer);
                techId = appointment.getTechId();
                Tech tech = TechDAO.getTechById(techId);
                TechComboBox.setValue(tech);
                advisorId = appointment.getAdvisorId();
                Advisor advisor = AdvisorDAO.getAdvisorById(advisorId);
                AdvisorComboBox.setValue(advisor);
                date = (appointment.getStartDateTime().toLocalDateTime()).toLocalDate();
                DatePicker.setValue(null);
                DatePicker.setValue(date);
                startTime = (appointment.getStartDateTime().toLocalDateTime()).toLocalTime();
                StartTimeComboBox.setValue(startTime);
                endTime = (appointment.getEndDateTime().toLocalDateTime()).toLocalTime();
                EndTimeComboBox.setValue(endTime);
                type = appointment.getType();
                if (appointment instanceof ServiceAppointment) {
                    service = ((ServiceAppointment) appointment).getService();
                    ServiceRB.setSelected(true);
                    ApptConcernsField.clear();
                    ApptServiceTypeComboBox.setValue(service);
                    ServiceRBAction(null);
                }
                else {
                    concerns = appointment.getConcerns();
                    ApptConcernsField.setText(concerns);
                    DiagnosticRB.setSelected(true);
                    DiagnosticRBAction(null);
                }
            }
        });
        CurrMonthTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                appointment = (Appointment)newSelection;
                apptId = appointment.getId();
                ApptIdField.setText(Integer.toString(apptId));
                custId = appointment.getCustId();
                Customer customer = CustomerDAO.getCustomerById(custId);
                CustomerComboBox.setValue(customer);
                techId = appointment.getTechId();
                Tech tech = TechDAO.getTechById(techId);
                TechComboBox.setValue(tech);
                advisorId = appointment.getAdvisorId();
                Advisor advisor = AdvisorDAO.getAdvisorById(advisorId);
                AdvisorComboBox.setValue(advisor);
                date = (appointment.getStartDateTime().toLocalDateTime()).toLocalDate();
                DatePicker.setValue(date);
                startTime = (appointment.getStartDateTime().toLocalDateTime()).toLocalTime();
                StartTimeComboBox.setValue(startTime);
                endTime = (appointment.getEndDateTime().toLocalDateTime()).toLocalTime();
                EndTimeComboBox.setValue(endTime);
                if (appointment instanceof ServiceAppointment) {
                    service = ((ServiceAppointment) appointment).getService();
                    ServiceRB.setSelected(true);
                    ApptConcernsField.clear();
                    ApptServiceTypeComboBox.setValue(service);
                    ServiceRBAction(null);
                }
                else {
                    concerns = appointment.getConcerns();
                    ApptConcernsField.setText(concerns);
                    DiagnosticRB.setSelected(true);
                    DiagnosticRBAction(null);
                }
            }
        });
        CurrWeekTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                appointment = (Appointment)newSelection;
                apptId = appointment.getId();
                ApptIdField.setText(Integer.toString(apptId));
                custId = appointment.getCustId();
                Customer customer = CustomerDAO.getCustomerById(custId);
                CustomerComboBox.setValue(customer);
                techId = appointment.getTechId();
                Tech tech = TechDAO.getTechById(techId);
                TechComboBox.setValue(tech);
                advisorId = appointment.getAdvisorId();
                Advisor advisor = AdvisorDAO.getAdvisorById(advisorId);
                AdvisorComboBox.setValue(advisor);
                date = (appointment.getStartDateTime().toLocalDateTime()).toLocalDate();
                DatePicker.setValue(date);
                startTime = (appointment.getStartDateTime().toLocalDateTime()).toLocalTime();
                StartTimeComboBox.setValue(startTime);
                endTime = (appointment.getEndDateTime().toLocalDateTime()).toLocalTime();
                EndTimeComboBox.setValue(endTime);
                if (appointment instanceof ServiceAppointment) {
                    service = ((ServiceAppointment) appointment).getService();
                    ServiceRB.setSelected(true);
                    ApptConcernsField.clear();
                    ApptServiceTypeComboBox.setValue(service);
                    ServiceRBAction(null);

                }
                else {
                    concerns = appointment.getConcerns();
                    ApptConcernsField.setText(concerns);
                    DiagnosticRB.setSelected(true);
                    DiagnosticRBAction(null);
                }
            }
        });

        // Exit button action lambda expression
        ExitButton.setOnAction( event ->
        {
            Alert alert;

            // Confirm Advisor wants to exit program
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit the program?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                // Close Program
                Platform.exit();
            }

        } );

        // Hide Service Combo Box
        ApptServiceTypeComboBox.setVisible(false);
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
        AllAdvisorIdCol.setCellValueFactory(new PropertyValueFactory<>("advisorId"));
        AllTechIdCol.setCellValueFactory(new PropertyValueFactory<>("techId"));
        AllStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        AllEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        AllConcernsCol.setCellValueFactory(new PropertyValueFactory<>("specialtyDisplay"));

        // Populate Current Month table on schedule form
        CurrMonthTable.setItems(AppointmentsDAO.getCurrMonthApptData());
        CurrMonthApptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        CurrMonthCustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        CurrMonthAdvisorIdCol.setCellValueFactory(new PropertyValueFactory<>("advisorId"));
        CurrMonthTechIdCol.setCellValueFactory(new PropertyValueFactory<>("techId"));
        CurrMonthStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        CurrMonthEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        CurrMonthConcernsCol.setCellValueFactory(new PropertyValueFactory<>("specialtyDisplay"));

        // Populate Current Week table on schedule form
        CurrWeekTable.setItems(AppointmentsDAO.getCurrWeekApptData());
        CurrWeekApptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        CurrWeekCustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        CurrWeekAdvisorIdCol.setCellValueFactory(new PropertyValueFactory<>("advisorId"));
        CurrWeekTechIdCol.setCellValueFactory(new PropertyValueFactory<>("techId"));
        CurrWeekStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        CurrWeekEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        CurrWeekConcernsCol.setCellValueFactory(new PropertyValueFactory<>("specialtyDisplay"));
    }

    /**
     * This method fills the customer, Tech, Advisor, and start/end time combo boxes.  Customer combo box uses customer
     * id and name, Tech combo box uses Tech id and name, Advisor combo box uses Advisor id and name, and appoint type
     * uses appointment type list. The start and end time combo boxes show the eastern time business hours converted
     * into local time.
     */
    private void fillComboBoxes() {
        // Fill customer combo box
        customerList = CustomerDAO.getCustomerData();
        CustomerComboBox.setVisibleRowCount(5);
        CustomerComboBox.setItems(customerList);

        // Fill Tech combo box
        techList = TechDAO.getTechByType("Line Tech");
        TechComboBox.setVisibleRowCount(5);
        TechComboBox.setItems(techList);

        // Fill Advisor combo box
        advisorList = AdvisorDAO.getAdvisorData();
        AdvisorComboBox.setVisibleRowCount(5);
        AdvisorComboBox.setItems(advisorList);

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

        // Fill service types combo box
        serviceTypes = ServiceAppointment.getServiceTypes();
        ApptServiceTypeComboBox.setVisibleRowCount(5);
        ApptServiceTypeComboBox.setItems(serviceTypes);
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
     * This method calls emptyFieldCheck, if none empty it gets info from Advisor input, adds the appointment, then updates
     * the appointment tables, otherwise informs Advisor of error.
     * @param actionEvent on Add button click
     */
    public void AddApptButtonAction(ActionEvent actionEvent) {
        Alert alert;

        if (ServiceRB.isSelected() && (((Tech)TechComboBox.getSelectionModel().getSelectedItem()).getType().equals("Line Tech"))) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tech Type Error");
            alert.setContentText("Please change tech to a Lube Tech for service appointments.");
            alert.show();
        }
        else if (DiagnosticRB.isSelected() && (((Tech)TechComboBox.getSelectionModel().getSelectedItem()).getType().equals("Lube Tech"))) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tech Type Error");
            alert.setContentText("Please change tech to a Line Tech for diagnostic appointments.");
            alert.show();
        }
        else {
            // Check that all fields/combo boxes have been filled out, add customer
            if (emptyFieldCheck()) {
                // Get new field values
                custId = ((Customer) CustomerComboBox.getSelectionModel().getSelectedItem()).getId();
                techId = ((Tech) TechComboBox.getSelectionModel().getSelectedItem()).getId();
                advisorId = ((Advisor) AdvisorComboBox.getSelectionModel().getSelectedItem()).getId();
                if (ServiceRB.isSelected()) {
                    type = "Service";
                    concerns = (String) ApptServiceTypeComboBox.getSelectionModel().getSelectedItem();
                } else {
                    type = "Diagnostic";
                    concerns = ApptConcernsField.getText();
                }
                date = DatePicker.getValue();
                startTime = (LocalTime) StartTimeComboBox.getSelectionModel().getSelectedItem();
                endTime = (LocalTime) EndTimeComboBox.getSelectionModel().getSelectedItem();
                startTimestamp = Timestamp.valueOf(LocalDateTime.of(date, startTime));
                endTimestamp = Timestamp.valueOf(LocalDateTime.of(date, endTime));

                // If apptId > 0 returned, repopulate table and inform Advisor of success
                int apptId = AppointmentsDAO.addAppt(custId, advisorId, techId, type, concerns, startTimestamp, endTimestamp);

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
                // Alert Advisor: customer has not been added
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Add Appointment Error");
                    alert.setContentText("Error: Appointment has NOT been added");
                    alert.showAndWait();
                }
            }
        }
    }

    /**
     * This method calls emptyFieldCheck, if none empty, it gets info from Advisor input, updates selected appointment, then
     * updates the appointment tables, otherwise informs Advisor of error.
     * @param actionEvent on Update button click
     */
    public void UpdateApptButtonAction(ActionEvent actionEvent) {
        Alert alert;

        if (ServiceRB.isSelected() && (((Tech)TechComboBox.getSelectionModel().getSelectedItem()).getType().equals("Line Tech"))) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tech Type Error");
            alert.setContentText("Please change tech to a Lube Tech for service appointments.");
            alert.show();
        }
        else if (DiagnosticRB.isSelected() && (((Tech)TechComboBox.getSelectionModel().getSelectedItem()).getType().equals("Lube Tech"))) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tech Type Error");
            alert.setContentText("Please change tech to a Line Tech for diagnostic appointments.");
            alert.show();
        }
        else {
            // Check that all fields/combo boxes have been filled out, update appointment
            if (emptyFieldCheck()) {
                custId = ((Customer) CustomerComboBox.getSelectionModel().getSelectedItem()).getId();
                techId = ((Tech) TechComboBox.getSelectionModel().getSelectedItem()).getId();
                advisorId = ((Advisor) AdvisorComboBox.getSelectionModel().getSelectedItem()).getId();
                if (ServiceRB.isSelected()) {
                    type = "Service";
                    concerns = (String) ApptServiceTypeComboBox.getSelectionModel().getSelectedItem();
                } else {
                    type = "Diagnostic";
                    concerns = ApptConcernsField.getText();
                }
                date = DatePicker.getValue();
                startTime = (LocalTime) StartTimeComboBox.getSelectionModel().getSelectedItem();
                endTime = (LocalTime) EndTimeComboBox.getSelectionModel().getSelectedItem();
                startTimestamp = Timestamp.valueOf(LocalDateTime.of(date, startTime));
                endTimestamp = Timestamp.valueOf(LocalDateTime.of(date, endTime));

                // If appointment updated in database, repopulate table and inform Advisor of success
                if (AppointmentsDAO.updateAppt(apptId, custId, advisorId, techId, type, concerns, startTimestamp, endTimestamp) > 0) {
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
                // Alert Advisor: customer has not been added
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Update Error");
                    alert.setContentText("Error: Appointment has NOT been updated");
                    alert.showAndWait();
                }
            }
        }
    }

    /**
     * This method checks that an appointment is selected, calls deleteAppt from AppointmentsDAO, then informs Advisor of
     * successful delete or otherwise.
     * @param actionEvent on Delete button click
     */
    public void DeleteApptButtonAction(ActionEvent actionEvent) {
        Alert alert;

        // If nothing selected, alert Advisor to select appointment
        if (appointment == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Error");
            alert.setContentText("Please select an appointment to delete.");
            alert.showAndWait();
            return;
        }
        else {
            // If delete is unsuccessful,notify Advisor
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
                alert.setContentText("Appointment #" + apptId +  " has been deleted.");
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
        CustomerComboBox.getSelectionModel().clearSelection();
        TechComboBox.getSelectionModel().clearSelection();
        AdvisorComboBox.getSelectionModel().clearSelection();
        ApptServiceTypeComboBox.getSelectionModel().clearSelection();
        ApptConcernsField.clear();
        DatePicker.getEditor().clear();
        StartTimeComboBox.getSelectionModel().clearSelection();
        EndTimeComboBox.getSelectionModel().clearSelection();
    }

    /**
     * This method checks all form fields to make sure they hold value, returns true if all full, false otherwise.
     * @return boolean hasText
     */
    public boolean emptyFieldCheck() {
        boolean hasText = true;
        if ((CustomerComboBox.getValue() == null)
                || (TechComboBox.getValue() == null)
                || (AdvisorComboBox.getValue() == null)
                || (ApptConcernsField.getText().isBlank() && ApptServiceTypeComboBox.getValue() == null)
                || (DatePicker.getValue() == null)
                || (StartTimeComboBox.getValue() == null)
                || (EndTimeComboBox.getValue() == null)
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

    public void ServiceRBAction(ActionEvent actionEvent) {
        ConcernLabel.setText("Service Type");
        ApptConcernsField.setVisible(false);
        ApptServiceTypeComboBox.setVisible(true);
        techList = TechDAO.getTechByType("Lube Tech");
        TechComboBox.setVisibleRowCount(5);
        TechComboBox.setItems(techList);
    }

    public void DiagnosticRBAction(ActionEvent actionEvent) {
        ConcernLabel.setText("Concerns");
        ApptServiceTypeComboBox.setVisible(false);
        ApptConcernsField.setVisible(true);
        techList = TechDAO.getTechByType("Line Tech");
        TechComboBox.setVisibleRowCount(5);
        TechComboBox.setItems(techList);
    }
}
