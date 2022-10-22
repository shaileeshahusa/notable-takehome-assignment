package com.example.restservice.models;

import javax.annotation.Generated;
import javax.persistence.*;


@Entity
@Table(schema = "DOCTORS")
public class Doctor {
    @Id
    @GeneratedValue
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
