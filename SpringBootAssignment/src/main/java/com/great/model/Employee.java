package com.great.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String employeeName;
	private String Address;
	private String EmployeePhone;
	private float employeeSalary;

	public Employee() {

	}

	public Employee(String employeeName, String address, String employeePhone, float employeeSalary) {
		super();
		this.employeeName = employeeName;
		this.Address = address;
		this.EmployeePhone = employeePhone;
		this.employeeSalary = employeeSalary;
	}

	public Employee(int id, String employeeName, String address, String employeePhone, float employeeSalary) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.Address = address;
		this.EmployeePhone = employeePhone;
		this.employeeSalary = employeeSalary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmployeePhone() {
		return EmployeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		EmployeePhone = employeePhone;
	}

	public float getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(float employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

}
