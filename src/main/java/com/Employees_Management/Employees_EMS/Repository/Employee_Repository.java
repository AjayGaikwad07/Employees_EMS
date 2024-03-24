package com.Employees_Management.Employees_EMS.Repository;

import com.Employees_Management.Employees_EMS.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Employee_Repository extends JpaRepository<Employee, Long> {
    List<Employee> findAllById(Long id);
//    Optional<Employee> findByPRN_Number(String prn_number);
}
