package com.mayab.calidad.doubles;


import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;


import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mayab.calidad.dao.*;


public class TestDAOMySql {


	private DAOOracle database;
	private Alumno alumno;
	
	@Before 
	public void setupMocks() {
		database = mock(DAOOracle.class);
		//dependency = new Dependency(new SubDependency());
		alumno = new Alumno(1,"Esteban", 10, 9, "esteban@gmail.com");
		setupDatabase();
		addAlumnos();
	}
	
	public void setupDatabase() {
		when(database.getDatabase()).thenReturn(new HashMap<Integer, Alumno>());
	}
	
	public void addAlumnos() {
		
		when(database.addAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
			 Alumno arg = (Alumno) invocation.getArguments()[0];
			 
		
			 database.getDatabase().putIfAbsent(arg.getId(), arg);
			 return true;
			}
			
		});
		
	}
	
	@Test
	public void testAddAlumnos() {
		
		
		
		
		int size = database.getDatabase().size();
		
		database.addAlumno(alumno);
		
		
		assertThat(database.getDatabase().size(), is(size + 1)); //add + 1 to work
	}
	
	@Test
	public void testDeleteAlumno() {
		
		when(database.deleteAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
			 Alumno arg = (Alumno) invocation.getArguments()[0];
			 
			 if(database.getDatabase().containsKey(arg.getId())) {
				 database.getDatabase().remove(arg.getId());
				 return true;
			 }
			 
			 	return false;
			}
			
		});
		
	
		
		database.addAlumno(alumno);
		int size = database.getDatabase().size();
		database.deleteAlumno(alumno);
		
		assertThat(database.getDatabase().size(), is(size - 1));
	}
	
	@Test
	public void testUpdateAlumno() {
		
		when(database.updateAlumnoCalificacion(any(Alumno.class), anyInt())).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
			 Alumno arg = (Alumno) invocation.getArguments()[0];
			 int arg1 = (Integer) invocation.getArguments()[1];
			 
			 if(database.getDatabase().containsKey(arg.getId())) {
				 Alumno alumno = database.getDatabase().get(arg.getId());
				 alumno.setGrade(arg1);
				 database.getDatabase().put(alumno.getId(), alumno);	 
				 return true;
			 }
			 
			 
			 	return false;
			}
			
		});
		
	
		
		database.addAlumno(alumno);
		int cal = database.getDatabase().get(alumno.getId()).getGrade();
		database.updateAlumnoCalificacion(alumno, 8);
		cal = database.getDatabase().get(alumno.getId()).getGrade();
		
		assertThat(cal, is(alumno.getGrade()));
	}
	
	@Test
	public void testGetAlumno() {
		
		when(database.getAlumno(anyInt() )).thenAnswer(new Answer<Alumno>() {
			
			public Alumno answer(InvocationOnMock invocation) throws Throwable {
			 
			 int arg = (Integer) invocation.getArguments()[0];
			 
			 if(database.getDatabase().containsKey(arg)) {
				 Alumno alumno = database.getDatabase().get(arg); 
				 return alumno;
			 }
			 
			 
			 	return null;
			}
			
		});
		
	
		
		database.addAlumno(alumno);
		Alumno aExpected = database.getAlumno(alumno.getId());
		
		assertThat(aExpected, is(database.getDatabase().get(alumno.getId())));
	}

}
