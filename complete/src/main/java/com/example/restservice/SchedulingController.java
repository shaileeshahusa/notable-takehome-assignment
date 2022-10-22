package com.example.restservice;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.restservice.models.Appointment;
import com.example.restservice.models.AppointmentType;
import com.example.restservice.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchedulingController {

	@Autowired
	private SchedulingService schedulingService;

	@GetMapping("/initalizeDoctors")
	public List<Doctor> initalizeDoctorList() {
		return schedulingService.initializeDoctorList();
	}

	@GetMapping("/initalizeAppointments")
	public List<Appointment> initalizeAppointmentList() {
		return schedulingService.initializeAppointments();
	}


	@GetMapping("/doctors")
	public List<Doctor> getAllDoctors() {
		return schedulingService.getAllDoctors();
	}

	// Date must be input as a string like "mm/dd/yyyy"
	@GetMapping("/appointments")
	public List<Appointment> getAppointmentByDoctorAndDate(@RequestParam(name = "doctorId") String doctorId,
														   @RequestParam(name = "date") String date) {
		return schedulingService.getAppointments(doctorId, date);
	}

	@DeleteMapping("/appointments")
	public void deleteAppointment(@RequestParam(name = "appointmentId") String appointmentId) {
		schedulingService.deleteAppointment(appointmentId);
	}

	@PostMapping("/appointments")
	public void addAppointment(@RequestParam(name = "doctorId") String doctorId,
							   @RequestParam(name = "patientFirstName") String patientFirstName,
							   @RequestParam(name = "patientLastName") String patientLastName,
							   @RequestParam(name = "appointmentType") String appointmentType,
							   @RequestParam(name = "appointmentTime") String appointmentTime) throws Exception {
		schedulingService.addAppointment(doctorId, patientFirstName, patientLastName, appointmentType, appointmentTime);
	}
}
