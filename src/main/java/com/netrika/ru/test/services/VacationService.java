package com.netrika.ru.test.services;

import com.netrika.ru.test.dbo.Employee;
import com.netrika.ru.test.dbo.Vacation;
import com.netrika.ru.test.dto.VacationTO;
import com.netrika.ru.test.repos.VacationRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final EmployeeService employeeService;

    public VacationService(@Qualifier("vacationRepository") VacationRepository vacationRepository,
                           @Qualifier("employeeService") EmployeeService employeeService) {
        this.vacationRepository = vacationRepository;
        this.employeeService = employeeService;
    }


    public List<VacationTO> getAllVacations() {
        return vacationRepository.findAll().stream().map(VacationTO::new).collect(Collectors.toList());
    }

    public VacationTO addVacation(Vacation vacation) {
        vacationRepository.saveAndFlush(vacation);
        Employee employee = employeeService.findEmployeeById(vacation.getEmployee().getId());
        List<Vacation> vacations = employee.getVacations();
        vacations.add(vacation);
        employee.setVacations(vacations);
        employeeService.updateEmployee(employee);

        return new VacationTO(vacation);
    }

    public VacationTO updateVacation(Vacation vacation) {
        vacationRepository.saveAndFlush(vacation);
        Employee employee = employeeService.findEmployeeById(vacation.getEmployee().getId());
        List<Vacation> vacations = employee.getVacations();
        for(Vacation updVacation : vacations) {
            if(updVacation.getId().equals(vacation.getId())) {
                vacation.setStartVacation(vacation.getStartVacation());
                vacation.setFinishVacation(vacation.getFinishVacation());
                break;
            }
        }
        employee.setVacations(vacations);
        employeeService.updateEmployee(employee);

        return  new VacationTO(vacation);
    }

    public void deleteVacation(Long id) {
        if(vacationRepository.findById(id).isPresent())
            vacationRepository.deleteById(id);
    }
}
