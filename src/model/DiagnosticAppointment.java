package model;

import java.sql.Timestamp;

public class DiagnosticAppointment extends Appointment {
    private String concerns;

    public DiagnosticAppointment(int id, int custId, int advisorId, int techId, String type, String concerns,
                                 Timestamp startTimestamp, Timestamp endTimestamp) {
        super(id, custId, advisorId, techId, type, concerns, startTimestamp, endTimestamp);

        this.concerns = concerns;
    }

    @Override
    public String getConcerns() {
        return concerns;
    }

    @Override
    public void setConcerns(String concerns) {
        this.concerns = concerns;
    }
}
