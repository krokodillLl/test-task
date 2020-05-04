package com.netrika.ru.test.dto;

import com.netrika.ru.test.dbo.Vacation;

import java.util.Date;

public class VacationTO {

    private Long id;
    private Date startVacation;
    private Date finishVacation;
    private EmployeeForVacationTO employee;

    public VacationTO() {
    }
    public VacationTO(Vacation vacation) {
        this.id = vacation.getId();
        this.startVacation = vacation.getStartVacation();
        this.finishVacation = vacation.getFinishVacation();
        this.employee = new EmployeeForVacationTO(vacation.getEmployee());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartVacation() {
        return startVacation;
    }

    public void setStartVacation(Date startVacation) {
        this.startVacation = startVacation;
    }

    public Date getFinishVacation() {
        return finishVacation;
    }

    public void setFinishVacation(Date finishVacation) {
        this.finishVacation = finishVacation;
    }

    public EmployeeForVacationTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeForVacationTO employee) {
        this.employee = employee;
    }
}
