package com.example.restservice.repository;

import com.example.restservice.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository {


    @Override
    List<Doctor> findAll();

    Doctor findById(String id);

    Doctor findByFirstName(String firstName);
}
