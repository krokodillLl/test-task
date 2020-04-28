package com.netrika.ru.test.repos;

import com.netrika.ru.test.dbo.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

    void deleteById(Long id);
}
