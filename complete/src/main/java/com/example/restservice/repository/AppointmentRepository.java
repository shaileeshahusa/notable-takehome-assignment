package com.example.restservice.repository;

import com.example.restservice.models.Appointment;
import com.example.restservice.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Override
    List<Appointment> findAll();

    void deleteById(String id);

    List<Appointment> findByDoctorAndAppointmentTime(@Param("doctor") Doctor doctor,
                                                       @Param("appointmentTime") Calendar appointmentTime);

    Appointment findById(String id);
}
