package com.yihaomen.test;

public class Users{
	private String username;
	private String age;
	private String useremail;
	public Users(String username,String age, String useremail) {
		this.username = username;
		this.age = age;
		this.useremail = useremail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}
