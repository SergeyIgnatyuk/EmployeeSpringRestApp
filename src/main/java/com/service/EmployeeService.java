package com.service;

import com.model.Employee;

import java.util.List;

/**
 * Service interface for {@link com.model.Employee}
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

public interface EmployeeService {
    List<Employee> findAllEmployees();
}
