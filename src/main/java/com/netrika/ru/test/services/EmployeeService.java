package com.netrika.ru.test.services;

import com.netrika.ru.test.dbo.Employee;
import com.netrika.ru.test.dto.EmployeeTO;
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

    public EmployeeTO addEmployee(Employee employee) {
        if(employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber()) != null) {
            return null;
        }
        employeeRepository.saveAndFlush(employee);
        return new EmployeeTO(employee);
    }

    public EmployeeTO updateEmployee(Employee employee) {
        if(employee.getLogin() == null || employee.getPassword() == null) {
            Employee employeeFromDB = employeeRepository.getOne(employee.getId());

            if (employee.getLogin() == null) {
                employee.setLogin(employeeFromDB.getLogin());
            }
            if(employee.getPassword() == null) {
                employee.setPassword(employeeFromDB.getPassword());
            }
        }
        employeeRepository.saveAndFlush(employee);
        return new EmployeeTO(employee);
    }

    public void deleteEmployee(Long id) {
        if(employeeRepository.findById(id).isPresent())
        employeeRepository.deleteById(id);
    }
}
