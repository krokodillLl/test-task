package com.netrika.ru.test.controller;

import com.netrika.ru.test.dbo.Employee;
import com.netrika.ru.test.dto.EmployeeTO;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeTO getAllEmployees(@PathVariable Long id) {
        return employeeService.getOneEmployee(id);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<EmployeeTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public EmployeeTO addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public EmployeeTO updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        employee.setId(id);
        return employeeService.updateEmployee(employee);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
