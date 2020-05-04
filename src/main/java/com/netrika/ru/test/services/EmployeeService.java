package com.netrika.ru.test.services;

import com.netrika.ru.test.dbo.Employee;
import com.netrika.ru.test.dto.EmployeeTO;
import com.netrika.ru.test.repos.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeTO::new).collect(Collectors.toList());
    }

    public EmployeeTO getOneEmployee(Long id) {
        return new EmployeeTO(employeeRepository.getOne(id));
    }
    public Employee findEmployeeById(Long id) {
        return employeeRepository.getOne(id);
    }

    public EmployeeTO addEmployee(Employee employee) {
        if(employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber()) != null) {
            return null;
        }
        employeeRepository.saveAndFlush(employee);
        return new EmployeeTO(employee);
    }

    public EmployeeTO updateEmployee(Employee employee) {
        if(employee.getLogin() == null || employee.getLogin().equals("") || employee.getPassword() == null || employee.getPassword().equals("")) {
            Employee employeeFromDB = employeeRepository.getOne(employee.getId());

            if (employee.getLogin() == null || employee.getLogin().equals("")) {
                employee.setLogin(employeeFromDB.getLogin());
            }
            if(employee.getPassword() == null || employee.getPassword().equals("")) {
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
