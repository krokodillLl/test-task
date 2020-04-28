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

    public boolean addEmployee(Employee employee) {
        if(employeeRepository.findById(employee.getId()).isPresent()) {
            return false;
        }
        employeeRepository.saveAndFlush(employee);
        return true;
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.saveAndFlush(employee);
    }

    public void deleteEmployee(Long id) {
        if(employeeRepository.findById(id).isPresent())
        employeeRepository.deleteById(id);
    }
}
