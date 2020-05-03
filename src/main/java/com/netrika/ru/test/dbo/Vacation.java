package com.netrika.ru.test.dbo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vacation")
public class Vacation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vacation_id")
    private Employee employee;
    private Date startVacation;
    private Date finishVacation;



    public Vacation() {
    }

    public Vacation(Employee employee, Date startVacation, Date finishVacation) {
        this.employee = employee;
        this.startVacation = startVacation;
        this.finishVacation = finishVacation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
}
