package com.netrika.ru.test.services;

import com.netrika.ru.test.dbo.Vacation;
import com.netrika.ru.test.repos.VacationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {

    private final VacationRepository vacationRepository;

    public VacationService(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }


    public List<Vacation> getAllVacations() {
        return vacationRepository.findAll();
    }

    public Boolean addVacation(Vacation vacation) {
        if(vacationRepository.findById(vacation.getId()).isPresent()) {
            return false;
        }
        vacationRepository.saveAndFlush(vacation);
        return true;
    }

    public void updateVacation(Vacation vacation) {
        vacationRepository.saveAndFlush(vacation);
    }

    public void deleteVacation(Long id) {
        if(vacationRepository.findById(id).isPresent())
            vacationRepository.deleteById(id);
    }
}
