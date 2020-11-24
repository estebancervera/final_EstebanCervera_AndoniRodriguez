package com.mayab.calidad.dao;

public class Alumno {
	
	private int id;
	private String name;
	private int age;
	private int grade;
	private String email;
	
	
	public Alumno(int id, String name, int age, int grade, String email) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.email = email;
	}
	
	//Getters and Setters
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
}
