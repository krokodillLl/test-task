package com.netrika.ru.test.controller;

import com.netrika.ru.test.dbo.Vacation;
import com.netrika.ru.test.services.VacationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Vacation> getAllVacations() {
        return vacationService.getAllVacations();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Boolean addVacation(@RequestBody Vacation vacation) {
        return vacationService.addVacation(vacation);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateVacation(@RequestBody Vacation vacation, @PathVariable Long id) {
        vacation.setId(id);
        vacationService.updateVacation(vacation);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteVacation(@PathVariable Long id) {
        vacationService.deleteVacation(id);
    }
}