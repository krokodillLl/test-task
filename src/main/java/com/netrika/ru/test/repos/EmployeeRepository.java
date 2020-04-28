package com.netrika.ru.test.repos;

import com.netrika.ru.test.dbo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    void deleteById(Long id);
}
