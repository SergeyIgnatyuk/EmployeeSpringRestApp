package com.repository;

import com.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link com.model.Employee}
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
