package com.cisco.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity()
@Table(name = "JDEPARTMENT")
public class JDepartment implements Serializable {

	@Id
	@GeneratedValue(generator = "empSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "empSeq", sequenceName = "emp_seq")
	@Column(name = "D_ID", unique = true, nullable = false)
	private int deptId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jDepartment", cascade =CascadeType.ALL)
	private Set<JEmployee> jemployees = new HashSet<JEmployee>();

	@Column(name = "D_NAME")
	private String deptName;

	@Column(name = "D_TYPE")
	private String deptType;

	@OneToOne(mappedBy = "jdepartment", cascade = { CascadeType.ALL })
	private JAddress jaddress = new JAddress();

	public JDepartment() {

	}

	public JAddress getJaddress() {
		return jaddress;
	}

	public void setJaddress(JAddress jaddress) {
		this.jaddress = jaddress;
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

	public Set<JEmployee> getJemployees() {
		return jemployees;
	}

	public void setJemployees(Set<JEmployee> jemployees) {
		this.jemployees = jemployees;
	}

	private static final long serialVersionUID = 1L;
}
