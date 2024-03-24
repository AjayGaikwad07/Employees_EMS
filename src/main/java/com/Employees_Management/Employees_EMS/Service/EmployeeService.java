package com.Employees_Management.Employees_EMS.Service;

import com.Employees_Management.Employees_EMS.Repository.Employee_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private Employee_Repository employee_repository;
}
