package com.bacancy.SpringBootDemo.service;

import com.bacancy.SpringBootDemo.dtos.EmployeeDTO;
import com.bacancy.SpringBootDemo.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author Vikas
 * @date 11/15/2021
 * @time 1:52 PM
 */
@Service
public interface EmployeeService {

    List<EmployeeDTO> getEmployees(int page, int pageSize);

    Employee getEmployeeDetails(long employeeId);

    Employee add(Employee employee);

    Employee update(Employee employee);}
