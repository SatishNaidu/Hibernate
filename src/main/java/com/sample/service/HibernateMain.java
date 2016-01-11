package com.cisco.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cisco.demo.HibernateUtil;
import com.cisco.pojo.User;

public class HibernateMain {
	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		try {
			User emp = new User();
			emp.setName("NodeJS");
			emp.setRole("Developer");
			emp.setInsertTime(new Date());

			// Get Session
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			// start transaction
			tx = session.beginTransaction();
			// Save the Model object
			session.save(emp);
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Employee ID=" + emp.getId());

			// terminate session factory, otherwise program won't end
			HibernateUtil.getSessionFactory().close();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}
