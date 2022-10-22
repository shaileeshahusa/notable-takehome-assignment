package com.example.restservice;

import com.example.restservice.models.Appointment;
import com.example.restservice.models.AppointmentType;
import com.example.restservice.models.Doctor;
import com.example.restservice.repository.AppointmentRepository;
import com.example.restservice.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SchedulingService {
    private DoctorRepository doctorRepository;

    private AppointmentRepository appointmentRepository;

    public SchedulingService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository){
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Appointment> getAppointments(String doctorId, String date) {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<Appointment> doctorAppointments = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (Appointment appointment : allAppointments) {
            Date appointmentDate = appointment.getAppointmentTime().getTime();
            String appointmentDateString = dateFormat.format(appointmentDate);
            if (appointment.getDoctor().getId().equals(doctorId) && appointmentDateString.equals(date)) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    public void deleteAppointment(String appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    public void addAppointment(String doctorId, String patientFirstName, String patientLastName,
                               String appointmentTypeString, String appointmentTimeString) throws Exception {

        Calendar appointmentTime = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        appointmentTime.setTime(dateFormat.parse(appointmentTimeString));

        Doctor doctor = doctorRepository.findById(doctorId);
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorAndAppointmentTime(doctor, appointmentTime);
        if (existingAppointments.size() > 2) {
            throw new Exception("Doctor is booked up for this time slot.");
        }
        if (appointmentTime.get(Calendar.MINUTE) % 15 != 0) {
            throw new Exception("Appointment must start at valid 15 minute interval.");
        }


        Appointment newAppointment = new Appointment();
        newAppointment.setDoctor(doctor);
        newAppointment.setPatientFirstName(patientFirstName);
        newAppointment.setPatientLastName(patientLastName);
        if (appointmentTypeString.equals("New Patient")) {
            newAppointment.setAppointmentType(AppointmentType.NEW_PATIENT);
        }
        else if (appointmentTypeString.equals("Follow-Up")) {
            newAppointment.setAppointmentType(AppointmentType.FOLLOW_UP);
        }
        else {
            throw new Exception("Please enter a valid appointment type.");
        }
        newAppointment.setAppointmentTime(appointmentTime);

        appointmentRepository.save(newAppointment);

    }

    public List<Doctor> initializeDoctorList() {
        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor1 = new Doctor();
        doctor1.setFirstName("Julius");
        doctor1.setLastName("Hibbert");

        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("Algernop");
        doctor2.setLastName("Krieger");

        Doctor doctor3 = new Doctor();
        doctor3.setFirstName("Nick");
        doctor3.setLastName("Riviera");

        doctorList.add(doctor1);
        doctorList.add(doctor2);
        doctorList.add(doctor3);

        doctorRepository.saveAll(doctorList);
        return doctorRepository.findAll();
    }

    public List<Appointment> initializeAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        Doctor doctor = doctorRepository.findByFirstName("Algernop");
        Appointment appointment1 = new Appointment();
        appointment1.setDoctor(doctor);
        appointment1.setPatientFirstName("Sterling");
        appointment1.setPatientLastName("Archer");
        appointment1.setAppointmentType(AppointmentType.NEW_PATIENT);

        Calendar appointmentTime = Calendar.getInstance();
        appointmentTime.set(Calendar.YEAR, 2018);
        appointmentTime.set(Calendar.MONTH, 04);
        appointmentTime.set(Calendar.DATE, 9);
        appointmentTime.set(Calendar.HOUR, 8);
        appointmentTime.set(Calendar.MINUTE, 00);

        appointment1.setAppointmentTime(appointmentTime);

        Appointment appointment2 = new Appointment();
        appointment2.setDoctor(doctor);
        appointment2.setPatientFirstName("Cyril");
        appointment2.setPatientLastName("Figus");
        appointment2.setAppointmentType(AppointmentType.FOLLOW_UP);

        appointmentTime.set(Calendar.HOUR, 8);
        appointmentTime.set(Calendar.MINUTE, 30);

        appointment2.setAppointmentTime(appointmentTime);

        Appointment appointment3 = new Appointment();
        appointment3.setDoctor(doctor);
        appointment3.setPatientFirstName("Ray");
        appointment3.setPatientLastName("Gillete");
        appointment3.setAppointmentType(AppointmentType.FOLLOW_UP);

        appointmentTime.set(Calendar.HOUR, 9);
        appointmentTime.set(Calendar.MINUTE, 00);

        appointment3.setAppointmentTime(appointmentTime);

        Appointment appointment4 = new Appointment();
        appointment4.setDoctor(doctor);
        appointment4.setPatientFirstName("Lana");
        appointment4.setPatientLastName("Kane");
        appointment4.setAppointmentType(AppointmentType.NEW_PATIENT);

        appointmentTime.set(Calendar.HOUR, 9);
        appointmentTime.set(Calendar.MINUTE, 00);

        appointment4.setAppointmentTime(appointmentTime);

        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        appointmentList.add(appointment3);
        appointmentList.add(appointment4);

        appointmentRepository.saveAll(appointmentList);

        return appointmentRepository.findAll();

    }
}
