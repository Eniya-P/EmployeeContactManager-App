package com.example.employeecontactmanagerapp;

public class Employee {
    private String name;
    private String id;
    private String phone;
    private String email;
    private String department;

    public Employee(String name, String id, String phone, String email, String department) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.department = department;
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
}
