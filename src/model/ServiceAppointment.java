package model;

import javafx.collections.ObservableList;

import java.sql.Timestamp;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * This class ServiceAppointments is used to make Service Appointment objects, and includes getters/setters for id, custId, advisorId, techId, service, type,
 * startTimestamp, and endTimestamp.
 *
 * @author Ashley Jensen
 */
public class ServiceAppointment extends Appointment {
    private String service;
    private static ObservableList<String> serviceTypes = observableArrayList();

    /**
     * This is the ServiceAppointment constructor with id, custId, advisorId, techId, service, type,
     * startTimestamp, and endTimestamp.
     * @param id sets integer id
     * @param custId sets integer custId
     * @param advisorId sets integer advisorId
     * @param techId sets integer techId
     * @param type sets String type
     * @param service sets String concerns
     * @param startTimestamp sets Timestamp startTimestamp
     * @param endTimestamp sets Timestamp endTimestamp
     */
    public ServiceAppointment(int id, int custId, int advisorId, int techId, String type, String service,
                              Timestamp startTimestamp, Timestamp endTimestamp) {
        super(id, custId, advisorId, techId, type, startTimestamp, endTimestamp);
        
        this.service = service;
    }

    /**
     * This method returns service.
     * @return String service
     */
    public String getService() {
        return service;
    }

    /**
     * This method sets service variable with service parameter
     * @param service sets String service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * This method returns an observableList of strings of lubeTech service types
     * @return ObservableList of strings of lubeTech service types
     */
    public static ObservableList<String> getServiceTypes() {
        serviceTypes.clear();
        serviceTypes.add("10K Service");
        serviceTypes.add("20K Service");
        serviceTypes.add("30K Service");
        serviceTypes.add("40K Service");
        serviceTypes.add("50K Service");
        serviceTypes.add("60K Service");
        serviceTypes.add("70K Service");
        serviceTypes.add("80K Service");
        serviceTypes.add("90K Service");
        serviceTypes.add("100K Service");
        return serviceTypes;
    }

    /**
     * This method returns String of service to be input into tableView.
     * @return String service
     */
    @Override
    public String getSpecialtyDisplay(){
        return service;
    }
}
