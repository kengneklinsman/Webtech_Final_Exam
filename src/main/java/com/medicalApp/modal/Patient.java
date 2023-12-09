package com.medicalApp.modal;


import jakarta.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    private String diseaseName;
    private String symptoms;
    @ManyToOne
    @JoinColumn(name = "diagnosed_disease_id")
    private Disease diagnosedDisease;

    public Patient() {
    }

    public Patient(Long id, String name, int age, String gender,String diseaseName, String email, Disease disease, String symptoms, Disease diagnosedDisease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.gender = diseaseName;
        this.email = email;
        this.disease = disease;
        this.symptoms = symptoms;
        this.diagnosedDisease = diagnosedDisease;
    }

    public Patient(Long id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Disease getDiagnosedDisease() {
        return diagnosedDisease;
    }

    public void setDiagnosedDisease(Disease diagnosedDisease) {
        this.diagnosedDisease = diagnosedDisease;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
}
