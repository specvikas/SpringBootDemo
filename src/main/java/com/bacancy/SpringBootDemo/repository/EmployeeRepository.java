package com.bacancy.SpringBootDemo.repository;

import com.bacancy.SpringBootDemo.dtos.EmployeeDTO;
import com.bacancy.SpringBootDemo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA
 *
 * @author Vikas
 * @date 11/15/2021
 * @time 1:55 PM
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT id, name, age, job from Employee", nativeQuery=true)
    Page<EmployeeDTO> findAllEmployees(Pageable pageable);

    Optional<Employee> findById(Long id);
}
