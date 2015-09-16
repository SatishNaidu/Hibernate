package com.cisco.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "JADDRESS")
public class JAddress implements Serializable {

	@Id
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = { @Parameter(name = "property", value = "jdepartment") })
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private JDepartment jdepartment;

	private int addressId;
	private String floorNo;
	private String wingName;


	public JAddress() {

	}

	public JAddress(String floorNo, String wingName) {
		super();
		this.floorNo = floorNo;
		this.wingName = wingName;

	}

	public JDepartment getJdepartment() {
		return jdepartment;
	}

	public void setJdepartment(JDepartment jdepartment) {
		this.jdepartment = jdepartment;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getWingName() {
		return wingName;
	}

	public void setWingName(String wingName) {
		this.wingName = wingName;
	}
	private static final long serialVersionUID = 1L;
}
