package com.bacancy.SpringBootDemo.dtos;

/**
 * Created with IntelliJ IDEA
 *
 * @author Vikas
 * @date 11/15/2021
 * @time 2:21 PM
 */
public class EmployeeDTO {

    private long id;

    private String name;

    private int age;

    private String designation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
