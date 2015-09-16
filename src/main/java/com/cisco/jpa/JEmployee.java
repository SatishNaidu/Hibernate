package com.cisco.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "findEmpById", query = "Select e from JEmployee e where e.eid = :id") })
@Table
public class JEmployee implements Serializable{
	@Id
	@GeneratedValue(generator = "empSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "empSeq", sequenceName = "emp_seq")
	private int eid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "D_ID", nullable = false)
	private JDepartment jDepartment;
	
	
	private String ename;
	private double salary;
	private String deg;

	public JEmployee(String ename, double salary, String deg) {
		super();
		// this.eid = eid;
		this.ename = ename;
		this.salary = salary;
		this.deg = deg;
	}

	public JEmployee() {
		super();
	}

	public JDepartment getjDepartment() {
		return jDepartment;
	}

	public void setjDepartment(JDepartment jDepartment) {
		this.jDepartment = jDepartment;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDeg() {
		return deg;
	}

	public void setDeg(String deg) {
		this.deg = deg;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", salary="
				+ salary + ", deg=" + deg + "]";
	}
	private static final long serialVersionUID = 1L;
}
