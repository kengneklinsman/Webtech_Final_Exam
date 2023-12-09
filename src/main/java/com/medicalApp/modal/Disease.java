package com.medicalApp.modal;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String symptoms;


    @OneToMany(mappedBy = "diagnosedDisease")
    private Set<Patient> patients;

    public Disease() {
    }

    public Disease(Long id, String name, String description, Set<Patient> patients) {
        this.id = id;
        this.name = name;
        this.symptoms = description;
        this.patients = patients;
    }

    public Disease(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return symptoms;
    }

    public void setDescription(String description) {
        this.symptoms = description;
    }


    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
