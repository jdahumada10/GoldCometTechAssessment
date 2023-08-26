package com.juan.ahumada.techassessment.repositories;

import com.juan.ahumada.techassessment.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
