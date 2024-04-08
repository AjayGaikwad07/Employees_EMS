package com.Employees_Management.Employees_EMS.Controller;

import ch.qos.logback.core.model.Model;
import com.Employees_Management.Employees_EMS.Entity.Admin;
import com.Employees_Management.Employees_EMS.Repository.Admin_Repository;
import com.Employees_Management.Employees_EMS.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    @Autowired
    private Admin_Repository adminRepository;

    @Autowired
    private AdminService adminService;

    @GetMapping("/formsignup")
    public String showsignupform( Model model){

        return "authentication/signup_page";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("account") Admin admin, BindingResult result){
        try {

            adminRepository.save(admin);
            return "redirect:/formsignup";
        }
        catch (Exception e){
            return "redirect:/formsignup";
        }
    }

    @GetMapping("/loginpage")
    public String showLoginForm(){
        return "authentication/login";
    }
    @PostMapping("/login_account")
    public String login(@RequestParam String username, @RequestParam String password, Model model){
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)){
            return "redirect:/accounts/list";
        }
        else {
            return "redirect:/loginpage";
        }
    }
}
