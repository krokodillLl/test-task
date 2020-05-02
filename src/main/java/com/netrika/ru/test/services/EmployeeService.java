package com.netrika.ru.test.services;

import com.netrika.ru.test.dbo.Employee;
import com.netrika.ru.test.repos.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        if(employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber()) != null) {
            return null;
        }
        employeeRepository.saveAndFlush(employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        employeeRepository.saveAndFlush(employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        if(employeeRepository.findById(id).isPresent())
        employeeRepository.deleteById(id);
    }
}
