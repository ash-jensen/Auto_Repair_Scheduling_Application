package model;

import javafx.collections.ObservableList;

import java.sql.Timestamp;

import static javafx.collections.FXCollections.observableArrayList;

public class ServiceAppointment extends Appointment {
    private String service;

    private static ObservableList<String> serviceTypes = observableArrayList();

    public ServiceAppointment(int id, int custId, int advisorId, int techId, String type, String service,
                              Timestamp startTimestamp, Timestamp endTimestamp) {
        super(id, custId, advisorId, techId, type, startTimestamp, endTimestamp);
        
        this.service = service;
    }

    public String getService() {
        return service;
    }

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
}
