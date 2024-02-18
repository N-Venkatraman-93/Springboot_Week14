package com.gl.MyController;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.great.model.Employee;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

@Controller
public class EmployeeController {

	/* WELCOME PAGE */
	@RequestMapping("/")
	public String WelcomePage() {
		return "welcome";
	}

	/* ADD RECORD */
	@RequestMapping("/add-form")
	public String addform() {
		return "AddRecordForm";
	}

	/* ADDING FORM */
	@PostMapping("/add-record")
	public String submitRecord(@RequestParam String employeeName, @RequestParam String Address,
			@RequestParam String employeePhone, @RequestParam float employeeSalary, Model data) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		try (Session session = factory.openSession()) {
			Transaction tx = session.beginTransaction();
			// All the DB work
			Employee s1 = new Employee(employeeName, Address, employeePhone, employeeSalary);
			session.save(s1); // Object converted to insert query
			Query q1 = session.createQuery("from Employee");
			List<Employee> employees = q1.getResultList();
			data.addAttribute("employees", employees);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("Hibernate error: " + ex.getMessage());
		}
		return "show-all";

	}

	/* SHOW RECORD */
	@RequestMapping("/show-all")
	public String showAll(Model data) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		try (Session session = factory.openSession()) {
			// Select all
			Query q1 = session.createQuery("from Employee");
			List<Employee> employees = q1.getResultList();
			data.addAttribute("employees", employees);
		} catch (Exception ex) {
			System.out.println("Hibernate error: " + ex.getMessage());
		}
		return "show-all";
	}

	/* UPDATE RECORD */
	@GetMapping("/update-employee-form")
	public String updateEmployeeForm(@RequestParam int id, Model data) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		try (Session session = factory.openSession()) {
			// Select * from Employee where id = given id
			Employee updateEmp = session.get(Employee.class, id);
			data.addAttribute("employee", updateEmp);
		} catch (Exception ex) {
			System.out.println("Hibernate error: " + ex.getMessage());
		}
		return "update-employee-form";
	}

	/* UPDATE SAVE RECORDS */
	@PostMapping("/update-save-employee")
	public String updateSaveEmployee(@RequestParam int id, @RequestParam String employeeName,
			@RequestParam String Address, @RequestParam String employeePhone, @RequestParam float employeeSalary,
			Model data) {
		// Session factory
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		try (Session session = factory.openSession()) {
			Transaction tx = session.beginTransaction();
			// All the DB work
			Employee e1 = new Employee(id, employeeName, Address, employeePhone, employeeSalary);
			session.update(e1); // Object converted to update query
			Query q1 = session.createQuery("from Employee");
			List<Employee> employees = q1.getResultList();
			data.addAttribute("employees", employees);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("Hibernate error: " + ex.getMessage());
		}

		return "show-all";
	}

	/* DELETE RECORD */
	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam int id, Model data) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		try (Session session = factory.openSession()) {
			Transaction tx = session.beginTransaction();
			Employee deleteEmployee = new Employee();
			deleteEmployee.setId(id);
			session.delete(deleteEmployee);
			Query q1 = session.createQuery("from Employee");
			List<Employee> employees = q1.getResultList();
			data.addAttribute("employees", employees);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("Hibernate error: " + ex.getMessage());
		}
		return "show-all";
	}
}
