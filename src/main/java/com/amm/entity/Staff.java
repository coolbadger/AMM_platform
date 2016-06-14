package com.amm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@SuppressWarnings("serial")
@Entity
@Table(name = "staff")
public class Staff implements Serializable{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String code;	
	private String staffName;
	private String gender;
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(length = 10)
	private Date birthday;
	private String mobile;
	@Column(nullable = true)
	private boolean enabled;
	private String remark;

	@Column(name = "is_delete")
	private boolean isDelete;
	
	public Staff() {
		
	}

	public Staff(String staffName) {
		this.staffName = staffName;
	}

	public Staff(String code, String staffName, String gender, String email,
				 Date birthday, String mobile, boolean enabled, String remark) {
		super();
		this.code = code;
		this.staffName = staffName;
		this.gender = gender;
		this.email = email;
		this.birthday = birthday;
		this.mobile = mobile;
		this.enabled = enabled;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}
