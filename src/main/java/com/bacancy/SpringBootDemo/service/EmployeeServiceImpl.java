package com.bacancy.SpringBootDemo.service;

import com.bacancy.SpringBootDemo.dtos.EmployeeDTO;
import com.bacancy.SpringBootDemo.entities.Employee;
import com.bacancy.SpringBootDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA
 *
 * @author Vikas
 * @date 11/15/2021
 * @time 1:54 PM
 */
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> getEmployees(int page, int pageSize) {
        Page<EmployeeDTO> employees = employeeRepository.findAllEmployees(PageRequest.of(page, pageSize));
        if(employees.getSize() == 0) {
            return Collections.emptyList();
        }
        return employees.toList();
    }

    @Override
    public Employee getEmployeeDetails(long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(!employee.isPresent()) {
            throw new EntityNotFoundException("Employee with id : " + employeeId + " not found.");
        }
        return employee.get();
    }

    @Override
    public Employee add(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if(!existingEmployee.isPresent()) {
            throw new EntityNotFoundException("Employee with id : " + employee.getId() + " not found.");
        }

        Employee employeeFromDb = existingEmployee.get();

        // Setting values for all the attributes of the employee as of now
        // Here we can check for each attribute and which ever is changed only update that and flush it to the database.

        employeeFromDb.setAge(employee.getAge());
        employeeFromDb.setDescription(employee.getDescription());
        employeeFromDb.setHeight(employee.getHeight());
        employeeFromDb.setName(employee.getName());
        employeeFromDb.setWeight(employee.getWeight());
        employeeFromDb.setDesignation(employee.getDesignation());

        employeeRepository.save(employeeFromDb);

        return employeeFromDb;
    }
}
