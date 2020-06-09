package com.netrika.ru.test.controller;

import com.netrika.ru.test.dbo.Employee;
import com.netrika.ru.test.dto.EmployeeTO;
import com.netrika.ru.test.services.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(@Qualifier("employeeService") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping()
    public List<EmployeeTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{id}")
    public EmployeeTO getAllEmployees(@PathVariable Long id) {
        return employeeService.getOneEmployee(id);
    }

    @PostMapping(value = "/add")
    public EmployeeTO addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping(value = "/update/{id}")
    public EmployeeTO updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        employee.setId(id);
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
