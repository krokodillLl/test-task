package com.netrika.ru.test.repos;

import com.netrika.ru.test.dbo.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

    @Query("select v from Vacation v  order by  v.finishVacation")
    List<Vacation> findAll();

    void deleteById(Long id);
}
