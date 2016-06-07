package com.amm.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;


@Entity
@Table(name = "student", catalog = "csw_frame")
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("user_name")
	@Column(name = "userName", length = 128)
	private String userName;

	@JsonProperty("password")
	@Column(name = "password", length = 20)
	private String password;

	@JsonProperty("email")
	@Column(name = "email", length = 128)
	private String email;

	@Column(name = "is_delete", nullable = false)
	@JsonProperty("is_delete")
	private boolean isDelete = false;

	public Student() {
	}

	public Student(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public Student(String userName, String password, String email, boolean isDelete) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.isDelete = isDelete;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Student changeInfo(Student student) {

		if(student != null) {
			student.setUserName(this.userName);
			student.setPassword(this.password);
			student.setEmail(this.email);
			student.setIsDelete(this.isDelete);
		}

		return student;
	}
}