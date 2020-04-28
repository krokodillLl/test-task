package com.netrika.ru.test.controller;

import com.netrika.ru.test.dbo.Employee;
import com.netrika.ru.test.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public Boolean addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        employee.setId(id);
        employeeService.updateEmployee(employee);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
