package com.Employees_Management.Employees_EMS.Repository;

import com.Employees_Management.Employees_EMS.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Admin_Repository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);

    Admin deleteByUsername(String username);

}
