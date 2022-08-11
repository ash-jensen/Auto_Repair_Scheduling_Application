package controller;

import DAO.AppointmentsDAO;
import DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class creates LoginForm. You use this to log in to the Appointment Scheduling application.
 *
 * @author Ashley Jensen
 */
public class LoginForm implements Initializable {
    public TextField UserNameField;
    public TextField PasswordField;
    public Label Location;
    public Button SignInButton;
    public Label SignInLabel;
    public Label UserNameLabel;
    public Label PasswordLabel;
    private String userName;
    private String password;
    private String zone = ZoneId.systemDefault().toString();
    ResourceBundle langBundle = ResourceBundle.getBundle("bundle/lang", Locale.getDefault());
    String alertTitle = langBundle.getString("LoginError");
    String alertToDisplay = langBundle.getString("InvalidErrorMessage");

    /**
     * This method initializes LoginForm and checks language for translation purposes.
     * @param url is the location
     * @param resourceBundle is resources used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Show default location
        Location.setText(zone);

        // If locale language is French, translate page
        if (Locale.getDefault().getLanguage().equals("fr") ) {
            SignInLabel.setText(langBundle.getString("Login"));
            UserNameLabel.setText(langBundle.getString("UserName"));
            UserNameField.setText(langBundle.getString("UserName"));
            PasswordLabel.setText(langBundle.getString("Password"));
            PasswordField.setText(langBundle.getString("Password"));
            SignInButton.setText(langBundle.getString("Login"));
            alertTitle = langBundle.getString("LoginError");
            alertToDisplay = langBundle.getString("InvalidErrorMessage");
        }
    }

    /**
     * This method checks to make sure the username and password fields are not empty, then checks for valid login
     * information and logs info to login_activity.txt. If the login is valid, it then makes a call to apptLoginCheck to check for appointments for the logged
     * on user, and then loads AppointmentForm. If not valid login, it informs user to try again.
     * @param actionEvent SignIn button click
     * @throws IOException if the stage is unable to change scene
     */
    public void SIButtonAction(ActionEvent actionEvent) throws IOException {
        // Check for empty fields and check login info
        if ((!UserNameField.getText().isEmpty()) && (!PasswordField.getText().isEmpty())) {
            userName = UserNameField.getText();
            password = PasswordField.getText();
            if (UserDAO.checkLoginInfo(userName, password)) {
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                stage.hide();

                // Check for appointment within 15 minutes of login
                AppointmentsDAO.apptLoginCheck();

                // Record valid login info
                try {
                    PrintWriter pw = new PrintWriter(new FileOutputStream(
                            new File("login_activity.txt"),
                            true /* append = true */));
                    pw.append("Valid Login: " + userName + " at time: " + LocalDateTime.now() + "\n");
                    pw.flush();
                    pw.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                // Load Schedule Page
                Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));

                Scene scene = new Scene(root, 900, 653);
                stage.setTitle("Appointment");
                stage.setScene(scene);
                stage.show();
            }
            else {
                // Record invalid login info
                try {
                    PrintWriter pw = new PrintWriter(new FileOutputStream(
                            new File("login_activity.txt"),
                            true /* append = true */));
                    pw.append("Invalid login by user: " + userName + " at time: " + LocalDateTime.now() + "\n");
                    pw.flush();
                    pw.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                // Alert user that login information is invalid
                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(alertTitle);
                alert.setContentText(alertToDisplay);
                alert.showAndWait();
            }
        }
        else {
            // Record invalid login info
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(
                        new File("login_activity.txt"),
                        true /* append = true */));
                pw.append("Invalid login by user: " + userName + " at time: " + LocalDateTime.now() + "\n");
                pw.flush();
                pw.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            // Alert user that login information is invalid
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertTitle);
            alert.setContentText(alertToDisplay);
            alert.showAndWait();
        }
    }
}
