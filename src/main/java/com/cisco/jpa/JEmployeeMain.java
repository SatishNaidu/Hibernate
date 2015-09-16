package com.cisco.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cisco.jpa.inheritance.NonTeachingStaff;
import com.cisco.jpa.inheritance.TeachingStaff;

public class JEmployeeMain {

	public static void main(String[] args) {
		// createEmployee();
		// updateEmployee();
		// updateFirstEmployee();
		// deleteEmployee();
		// scalarandAggregateFunctions();
		// updateNamedQuery();
		// showSingleTableStrategy();
		//oneToMany();
		showCriteria();

	}

	public static void createEmployee() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		JEmployee employee = new JEmployee();
		employee.setDeg("Java Developer");
		employee.setEname("Cisco");
		employee.setSalary(50000);
		entityManager.persist(employee);
		tx.commit();
		entityManager.close();
		emFactory.close();
	}

	public static void updateEmployee() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		JEmployee employee = entityManager.find(JEmployee.class, 1100);
		System.out.println("before update: " + employee);
		employee.setSalary(20000);
		tx.commit();
		System.out.println("after update: " + employee);
		entityManager.close();
		emFactory.close();

	}

	public static void updateFirstEmployee() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		JEmployee employee = entityManager.find(JEmployee.class, 4050);
		System.out.println("before update: " + employee);
		// if (employee == null) {
		employee = new JEmployee();
		employee.setDeg("Node Js Developer");
		employee.setEname("Node");
		employee.setSalary(30000);
		entityManager.persist(employee);
		// } else {
		// employee.setSalary(40000);
		// }
		tx.commit();
		System.out.println("after update: " + employee);
		entityManager.close();
		emFactory.close();

	}

	public static void deleteEmployee() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		JEmployee employee = entityManager.find(JEmployee.class, 1100);
		System.out.println("before delete: " + employee);
		entityManager.remove(employee);
		tx.commit();
		System.out.println("after delete: " + employee);
		entityManager.close();
		emFactory.close();

	}

	public static void scalarandAggregateFunctions() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		// Scalar function
		Query query = entityManager
				.createQuery("Select UPPER(e.ename) from JEmployee e");

		List<String> list = (List<String>) query.getResultList();
		for (String empName : list) {
			System.out.println("list of employees: " + empName);
		}

		Query query2 = entityManager
				.createQuery("Select MAX(e.salary) from JEmployee e");
		Double result = (Double) query2.getSingleResult();
		System.out.println("Max salary of account: " + result);
		
		tx.commit();
		entityManager.close();
		emFactory.close();

	}

	public static void updateNamedQuery() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Query query = entityManager.createNamedQuery("findEmpById");
		query.setParameter("id", 3050);
		List<JEmployee> list = query.getResultList();
		for (JEmployee e : list) {
			System.out.print("Employee ID :" + e.getEid());
			System.out.println("\t Employee Name :" + e.getEname());
		}
		

	}

	public static void showSingleTableStrategy() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		// Teaching staff entity
		TeachingStaff ts1 = new TeachingStaff(1, "Gopal", "MSc MEd", "Maths");
		TeachingStaff ts2 = new TeachingStaff(2, "Manisha", "BSc BEd",
				"English");
		// Non-Teaching Staff entity
		NonTeachingStaff nts1 = new NonTeachingStaff(3, "Satish", "Accounts");
		NonTeachingStaff nts2 = new NonTeachingStaff(4, "Krishna",
				"Office Admin");

		// storing all entities
		entityManager.persist(ts1);
		entityManager.persist(ts2);
		entityManager.persist(nts1);
		entityManager.persist(nts2);
		tx.commit();
		entityManager.close();
		emFactory.close();

	}

	public static void oneToMany() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		JDepartment department = new JDepartment();
		department.setDeptName("Department");
		department.setDeptType("DeptType");

		JEmployee employee1 = new JEmployee("Rajesh", 70000, "SSE");
		employee1.setjDepartment(department);
		JEmployee employee2 = new JEmployee("Suresh", 30000, "SD");
		employee2.setjDepartment(department);

		Set<JEmployee> empSet = new HashSet<JEmployee>();
		empSet.add(employee1);
		empSet.add(employee2);
		department.setJemployees(empSet);

		JAddress jAddress = new JAddress("First", "B-Wing");
		jAddress.setJdepartment(department);
		// if we dont specify this line, we will get "attempted to assign id from null one-to-one property:jdepartment"
		department.setJaddress(jAddress);

		entityManager.persist(department);
		tx.commit();

		entityManager.close();
		emFactory.close();

	}

	public static void showCriteria() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<JEmployee> criteriaQuery = criteriaBuilder
				.createQuery(JEmployee.class);
		
		//CriteriaQuery.form method is called to set the query root.
		Root<JEmployee> from = criteriaQuery.from(JEmployee.class);
		
		//CriteriaQuery.select is called to set the result list type.
		CriteriaQuery<JEmployee> select = criteriaQuery.select(from);
		select.orderBy(criteriaBuilder.asc(from.get("ename")));
		
		// Preparing the Query for execution and specifying the type of result
		TypedQuery<JEmployee> typedQuery = entityManager.createQuery(select);

		//typedQuery.getResultList() to execute the query
		List<JEmployee> resultSet = typedQuery.getResultList();
		for (JEmployee e : resultSet) {
			System.out.println("employee name: " + e.getEname());
		}
		entityManager.close();
		emFactory.close();

	}

}
