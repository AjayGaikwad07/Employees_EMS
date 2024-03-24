package com.Employees_Management.Employees_EMS.Controller;

import com.Employees_Management.Employees_EMS.Entity.Employee;
import com.Employees_Management.Employees_EMS.Repository.Employee_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class Employee_Controller {

    @Autowired
    private Employee_Repository employee_repository;

    @GetMapping("/list")
    public String getAllAccounts(Model model) {
        try {
            List<Employee> employeeList = employee_repository.findAll();
            model.addAttribute("employees", employeeList);
            return "Employee_List";
        } catch (Exception e) {
            // Handle the exception gracefully, for example:
            model.addAttribute("error", "An error occurred while fetching employee data.");
            return "error"; // Provide a custom error page or redirect as needed
        }
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("account", new Employee());
        return "New_Employee";
    }

    @PostMapping("/save")
    public String submitform(@ModelAttribute("Employee") Employee employee, Model model, @RequestParam("salary") double salary, RedirectAttributes redirectAttributes) {
        try {
            if (salary<0){
                model.addAttribute("errorMessage", "Salary cannot be negative");
                return "New_Employee";
            }
            employee_repository.save(employee);
            model.addAttribute("message","New Employee Created");

        } catch (Exception e){
            return "error";
        }
        return "redirect:/accounts/new";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id){
        try {
            employee_repository.deleteById(id);
        }
        catch (Exception e){
            return "error";
        }
     return "redirect:/accounts/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditAccountForm(@PathVariable Long id, Model model){
        try {
            Employee employee =employee_repository.findById(id).orElse(null);
            model.addAttribute("account", employee);
        } catch (Exception e){
            return "error";
        }
        return "Update_Employee";
    }

}
