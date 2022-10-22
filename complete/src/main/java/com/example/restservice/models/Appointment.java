package com.example.restservice.models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(schema = "APPOINTMENTS")
public class Appointment {
    @Id
    @GeneratedValue
    private String id;

    @Column
    private String patientFirstName;

    @Column
    private String patientLastName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar appointmentTime;

    @Column
    private AppointmentType appointmentType;

    @Column
    @ManyToOne
    private Doctor doctor;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public Calendar getAppointmentTime() {
        return this.appointmentTime;
    }

    public void setAppointmentTime(Calendar appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public AppointmentType getAppointmentType() {
        return this.appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
