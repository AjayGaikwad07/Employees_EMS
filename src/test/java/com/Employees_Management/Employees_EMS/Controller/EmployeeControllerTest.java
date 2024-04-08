package com.Employees_Management.Employees_EMS.Controller;

import com.Employees_Management.Employees_EMS.Entity.Employee;
import com.Employees_Management.Employees_EMS.Repository.Employee_Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(Employee_Controller.class)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Employee_Repository employeeRepository;

    @Test
    void getAllAccounts() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("employees"))
                .andExpect(MockMvcResultMatchers.view().name("Employee_List"));
    }

    @Test
    void showForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("account"))
                .andExpect(MockMvcResultMatchers.view().name("New_Employee"));
    }

    @Test
    void submitform_validData() throws Exception {
        Employee employee = new Employee();

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/save")
                        .param("name", "John Doe")
                        .param("salary", "1000.0"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/accounts/new"));

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void deleteAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/delete/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/accounts/list"));

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void showEditAccountForm() throws Exception {
        Employee employee = new Employee();
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/edit/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("account"))
                .andExpect(MockMvcResultMatchers.view().name("Update_Employee"));
    }
}
