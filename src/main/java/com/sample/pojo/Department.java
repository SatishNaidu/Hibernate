package com.cisco.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity()
@NamedQueries({ @NamedQuery(name = "findDepartmentById", query = "Select d from Department d where d.deptId = :deptId") })
@Table(name = "DEPARTMENT")
@Access(AccessType.FIELD)
public class Department implements Serializable {

	@Id
	@GeneratedValue(generator = "empSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "empSeq", sequenceName = "emp_seq")
	@Column(name = "DEPT_ID", unique = true, nullable = false)
	private int deptId;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="department")
	private Address address;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="department",cascade=CascadeType.ALL)
	private Set<Employee> employees = new HashSet<Employee>();

	@Column(name = "DEPT_NAME")
	private String deptName;

	@Column(name = "DEPT_TYPE")
	private String deptType;


	


	public Department() {

	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName
				+ ", deptType=" + deptType + "]";
	}

	private static final long serialVersionUID = 1L;
}
