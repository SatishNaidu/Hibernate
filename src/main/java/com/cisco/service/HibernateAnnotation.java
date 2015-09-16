package com.cisco.service;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cisco.demo.HibernateUtil;
import com.cisco.pojo.Address;
import com.cisco.pojo.Department;
import com.cisco.pojo.Employee;

public class HibernateAnnotation {

	public static void main(String[] args) {
		saveSampleData();
		// showNamedQueryData();
	}

	public static void saveSampleData() {

		// Get Session sessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		// If we set hibernate.current_session_context_class to thread it will create the session
		Session session = sessionFactory.getCurrentSession();
		
		
		// start transaction
		Transaction tx = session.beginTransaction();

		Department department = new Department();
		department.setDeptName("Department");
		department.setDeptType("DeptType");

		// Employee employee1 = new Employee("Rajesh", 70000, "SSE");
		// employee1.setjDepartment(department);
		// Employee employee2 = new Employee("Suresh", 30000, "SD");
		// employee2.setjDepartment(department);
		//
		// Set<Employee> empSet = new HashSet<Employee>();
		// empSet.add(employee1);
		// empSet.add(employee2);
		// department.d csetJemployees(empSet);
		//
		// Address jAddress = new Address("First", "B-Wing");
		// jAddress.setJdepartment(department);
		// // if we dont specify this line, we will get
		// // "attempted to assign id from null one-to-one property:jdepartment"
		// department.setJaddress(jAddress);

		// Save the Model object
		session.save(department);
		tx.commit();
		System.out.println("Employee ID=" + department.getDeptId());

		// terminate session factory, otherwise program won't end
		sessionFactory.close();

	}

//	public static void showNamedQueryData() {
//		Session session = null;
//		Transaction tx = null;
//		try {
//			SessionFactory sessionFactory = HibernateUtil
//					.getSessionAnnotationFactory();
//			session = sessionFactory.getCurrentSession();
//			tx = session.beginTransaction();
//			Query query = session.getNamedQuery("getDepartmentDetails");
//			query.setParameter("name", "Frans");
//			Department2 department = (Department2) query.uniqueResult();
//			tx.commit();
//			System.out.println("Company Location: "
//					+ department.getEmployees().size());
//			sessionFactory.close();
//		} catch (Exception ex) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			ex.printStackTrace();
//		}
//	}

}