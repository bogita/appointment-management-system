package com.appointment.system.users;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String first_name;
	private String last_name;
	private String gender;
	private String email;
	private String phone_number;
	private Integer age;

	public User(){}

	public User(String first_name, String last_name, String gender, String email, String phone_number, Integer age){
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.email = email;
		this.phone_number = phone_number;
		this.age = age;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id=id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(first_name, user.first_name) &&
				Objects.equals(last_name, user.last_name) &&
				Objects.equals(gender, user.gender) &&
				Objects.equals(email, user.email) &&
				Objects.equals(phone_number, user.phone_number) &&
				Objects.equals(age, user.age);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, first_name, last_name, gender, email, phone_number, age);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", first_name='" + first_name + '\'' +
				", last_name='" + last_name + '\'' +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				", phone_number='" + phone_number + '\'' +
				", age=" + age +
				'}';
	}
}
