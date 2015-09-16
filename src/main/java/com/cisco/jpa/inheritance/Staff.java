package com.cisco.jpa.inheritance;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn( name="type" )
// @Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Staff implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "staffSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "staffSeq", sequenceName = "staff_seq")
	private int sid;
	private String sname;

	public Staff(int sid, String sname) {
		super();
		// this.sid = sid;
		this.sname = sname;
	}

	public Staff() {
		super();
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}
