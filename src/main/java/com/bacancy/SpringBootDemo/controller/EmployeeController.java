package com.bacancy.SpringBootDemo.controller;

import com.bacancy.SpringBootDemo.dtos.EmployeeDTO;
import com.bacancy.SpringBootDemo.entities.Employee;
import com.bacancy.SpringBootDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author Vikas
 * @date 11/15/2021
 * @time 1:50 PM
 */
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(
            employeeService.getEmployees(page, pageSize)
        );
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Employee> getEmployee(@PathVariable("id") @NonNull Long id) {
        try {
            Employee emp = employeeService.getEmployeeDetails(id);
            return ResponseEntity.ok(emp);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Employee> addEmployee(@RequestBody @Validated Employee employee) {
        try {
            Employee emp = employeeService.add(employee);
            return ResponseEntity.ok(emp);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Employee> updateEmployee(@RequestBody @Validated Employee employee) {
        try {
            Employee emp = employeeService.update(employee);
            return ResponseEntity.ok(emp);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
