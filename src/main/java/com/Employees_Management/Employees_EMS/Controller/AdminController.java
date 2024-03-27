package com.Employees_Management.Employees_EMS.Controller;

import com.Employees_Management.Employees_EMS.Entity.Admin;
import com.Employees_Management.Employees_EMS.Repository.Admin_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private Admin_Repository adminRepository;

    @GetMapping("adminlist")
    public String adminlist( Model model){
       try {
           List<Admin> adminList = adminRepository.findAll();
           model.addAttribute("admin_office", adminList);
           return "authentication/AdminList";
       }
       catch (Exception e){
           model.addAttribute("error", "An error occurred while fetching employee data.");
           return "error";

       }
    }
    @GetMapping("/delete/{id}")
    public String admin_account(@PathVariable Long id){
        try {
            adminRepository.deleteById(id);

        }catch (Exception e){
            return "error";
        }
        return "redirect:/adminlist";

    }
}
