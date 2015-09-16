package com.cisco.pojo;

import java.io.Serializable;

public class DepartmentDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int deptId;
	private String deptName;
	private String deptType;

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

	@Override
	public String toString() {
		return "DepartmentDto [deptId=" + deptId + ", deptName=" + deptName
				+ ", deptType=" + deptType + "]";
	}

}
