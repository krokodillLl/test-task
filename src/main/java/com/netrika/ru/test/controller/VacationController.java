package com.netrika.ru.test.controller;

import com.netrika.ru.test.dbo.Vacation;
import com.netrika.ru.test.dto.VacationTO;
import com.netrika.ru.test.services.VacationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService vacationService;

    public VacationController(@Qualifier("vacationService") VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping()
    public List<VacationTO> getAllVacations() {
        return vacationService.getAllVacations();
    }

    @PostMapping(value = "/add")
    public VacationTO addVacation(@RequestBody Vacation vacation) {
        return vacationService.addVacation(vacation);
    }

    @PutMapping(value = "/update/{id}")
    public VacationTO updateVacation(@RequestBody Vacation vacation, @PathVariable Long id) {
        vacation.setId(id);
        return vacationService.updateVacation(vacation);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteVacation(@PathVariable Long id) {
        vacationService.deleteVacation(id);
    }
}