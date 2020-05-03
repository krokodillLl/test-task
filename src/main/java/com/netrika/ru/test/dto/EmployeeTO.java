package com.netrika.ru.test.dto;

import com.netrika.ru.test.dbo.Employee;
import com.netrika.ru.test.dbo.Vacation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeTO {
    private Long id;
    private Long employeeNumber;
    private String name;
    private String surname;
    private String patronymic;
    private String position;
    private Date birthday;
    private Date startWork;
    private List<VacationEmployeeTO> vacations;

    public EmployeeTO(Employee employee) {
        this.id = employee.getId();
        this.employeeNumber = employee.getEmployeeNumber();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.patronymic = employee.getPatronymic();
        this.position = employee.getPosition();
        this.birthday = employee.getBirthday();
        this.startWork = employee.getStartWork();
        this.vacations = employee.getVacations().stream().map(VacationEmployeeTO::new).collect(Collectors.toList());
    }

    public EmployeeTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getStartWork() {
        return startWork;
    }

    public void setStartWork(Date startWork) {
        this.startWork = startWork;
    }

    public List<VacationEmployeeTO> getVacations() {
        return vacations;
    }

    public void setVacations(List<VacationEmployeeTO> vacations) {
        this.vacations = vacations;
    }
}
