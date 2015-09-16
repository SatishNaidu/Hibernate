package com.cisco.pojo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.cisco.demo.HibernateUtil;

public class DepartmentMain {

	public static void main(String[] args) {
		// saveDepartMent();
		// findById();
		// updateDepartMent();
		// listAllDepartMents();
		// deleteDepartMent();
		// oneToMany();
		// oneToOne();
		// showNamedQuery();
		showCriteriaQuery();
	}

	public static void saveDepartMent() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		// start transaction
		Transaction tx = session.beginTransaction();
		Department department = new Department();
		department.setDeptName("Pega");
		department.setDeptType("UI");
		session.save(department);
		tx.commit();
		System.out.println("Employee ID=" + department.getDeptId());
		// terminate session factory, otherwise program won't end
		sessionFactory.close();

	}

	public static void findById() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Department department = (Department) session.load(Department.class,
				13050);
		System.out.println("Department: "+department);//  Set<Employee> not loaded here
		System.out.println("Employee ID=" + department.getEmployees());// Set<Employee> loaded here
		tx.commit();

		// If we try to access object after closing the session,
		// org.hibernate.LazyInitializationException: could not initialize proxy
		// - no Session
		// System.out.println("Employee ID=" + department.getDeptId());
		// terminate session factory, otherwise program won't end
		sessionFactory.close();

	}

	public static void updateDepartMent() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Department department = (Department) session.load(Department.class,
				13050);
		System.out.println("Department before update: " + department);

		department.setDeptName("NodeJS");
		tx.commit();
		System.out.println("Employee ID=" + department.getDeptId());
		// terminate session factory, otherwise program won't end
		sessionFactory.close();

	}

	public static void listAllDepartMents() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		List<Department> departments = (List<Department>) session.createQuery(
				"from Department").list();

		System.out.println("All departments");
		List<DepartmentDto> departmentDtos = new ArrayList<DepartmentDto>();
		for (Department department : departments) {
			DepartmentDto departmentDto = new DepartmentDto();
			try {
				BeanUtils.copyProperties(departmentDto, department);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(departmentDto);
		}

		tx.commit();
		// terminate session factory, otherwise program won't end
		sessionFactory.close();
	}

	public static void deleteDepartMent() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Department department = (Department) session.load(Department.class,
				15050);
		System.out.println("Department before delete: " + department);
		session.delete(department);
		tx.commit();
		// terminate session factory, otherwise program won't end
		sessionFactory.close();

	}

	public static void oneToMany() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Department department = new Department();
		department.setDeptName("DeptName");
		department.setDeptType("DeptType");

		Employee employee1 = new Employee();
		employee1.setDeg("SSE");
		employee1.setEname("Mahesh");
		employee1.setSalary(80000);

		Employee employee2 = new Employee();
		employee2.setDeg("SE");
		employee2.setEname("Cisco");
		employee2.setSalary(100000);

		employee1.setDepartment(department);
		employee2.setDepartment(department);

		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee1);
		employees.add(employee2);

		department.setEmployees(employees);
		session.save(department);

		tx.commit();
		sessionFactory.close();
	}

	public static void oneToOne() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Department department = new Department();
		department.setDeptName("DeptName");
		department.setDeptType("DeptType");

		Employee employee1 = new Employee();
		employee1.setDeg("SSE");
		employee1.setEname("Mahesh");
		employee1.setSalary(80000);

		Employee employee2 = new Employee();
		employee2.setDeg("SE");
		employee2.setEname("Cisco");
		employee2.setSalary(100000);

		employee1.setDepartment(department);
		employee2.setDepartment(department);

		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee1);
		employees.add(employee2);

		department.setEmployees(employees);

		Address address = new Address();
		address.setFloorNo("5");
		address.setWingName("B");
		// Caution if we dont specify this line, hibernate will throw attempt to assign id from null @onetoone error
		address.setDepartment(department);
		department.setAddress(address);
		session.save(department);

		tx.commit();
		sessionFactory.close();
	}

	public static void showNamedQuery() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("findDepartmentById");
		query.setParameter("deptId", 16050);
		Department department = (Department) query.uniqueResult();
		System.out.println("Deparment details: " + department);
		tx.commit();
		sessionFactory.close();
	}

	public static void showCriteriaQuery() {
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Department.class);
		cr.add(Restrictions.eq("deptId", 16050));

		Criterion deptName = Restrictions.gt("deptName", "DeptName");
		Criterion deptType = Restrictions.ilike("deptType", "DeptType");
		LogicalExpression orExp = Restrictions.or(deptName, deptType);
		cr.add(orExp);
		List<Department> departments = cr.list();
		System.out.println("Filtered Departments::");
		for (Department dp : departments) {
			System.out.println(dp);
		}
		tx.commit();
		sessionFactory.close();
	}

}
