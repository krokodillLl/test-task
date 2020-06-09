package com.netrika.ru.test.repos;

import com.netrika.ru.test.dbo.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

    List<Vacation> findAll();

    void deleteById(Long id);
}
