package com.mayab.calidad.dao;

public interface DAO {
	public boolean addAlumno(Alumno a);
	public boolean deleteAlumno(Alumno a);
	public int getAllAlumnosCount();
	public Alumno getAlumno(int id);
	public boolean  updateAlumnoCalificacion(Alumno a, int calificacion);
	
}
