package com.Employees_Management.Employees_EMS.Service;

import com.Employees_Management.Employees_EMS.Repository.Admin_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private Admin_Repository adminRepository;

}
