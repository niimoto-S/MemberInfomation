package jp.co.aforce.bean;

import java.io.Serializable;

public class MemberBean implements Serializable{

	private String lastName;
	private String firstName;
	private String sex;
	private int year;
	private int month;
	private int day;
	private String job;
	private String phoneNumber;
	private String mailAddress;

	public MemberBean() {}

	public MemberBean(
			String lastName,
			String firstName,
			String sex,
			int year,
			int month,
			int day,
			String job,
			String phoneNumber,
			String mailAddress) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.sex = sex;
		this.year = year;
		this.month = month;
		this.day = day;
		this.job = job;
		this.phoneNumber = phoneNumber;
		this.mailAddress = mailAddress;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

}
