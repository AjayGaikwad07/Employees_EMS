package com.Employees_Management.Employees_EMS.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Random;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "prn_number",unique = true,updatable = false)
    private String prn_number;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    @Column(name = "account_number",unique = true)
    private String accountNumber;
    private double salary;
    private LocalDate dob;
    private String gender;
    private String employee_title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrn_number() {
        return prn_number;
    }

    public void setPrn_number(String prn_number) {
        this.prn_number = prn_number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmployee_title() {
        return employee_title;
    }

    public void setEmployee_title(String employee_title) {
        this.employee_title = employee_title;
    }

    @PrePersist
    protected void onCreate(){
        this.prn_number = generatePRN_AccountNumber();
    }

    private String generatePRN_AccountNumber(){
        StringBuilder prn_numberBuilder = new StringBuilder();
        prn_numberBuilder.append("611");

        Random random = new Random();
        for (int i=0; i< 4; i++){
            prn_numberBuilder.append(random.nextInt(3));
        }
        return prn_numberBuilder.toString();
    }
}
