package com.mayab.calidad.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;




public class AlumnoDaoOracleSQL implements DAO {
	
	

	public Connection getConnectionMySQL(){
		
			Connection con=null;  
	        try{  
	            Class.forName("oracle.jdbc.driver.OracleDriver"); 
	            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","dbunit","dbunit");  
	        }catch(Exception e){System.out.println(e);}  
	        return con;  
	        
	
  
	}
	
	public boolean addAlumno(Alumno a) {
		// TODO Auto-generated method stub
		
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement("insert INTO alumno(id, name, age, grade, email) values (?, ?, ?, ?, ?)");
			
			preparedStatement.setInt(1, a.getId());
			preparedStatement.setString(2, a.getName());
			preparedStatement.setInt(3, a.getAge());
			preparedStatement.setInt(4, a.getGrade());
			preparedStatement.setString(5, a.getEmail());
			
			preparedStatement.executeUpdate();
			
			
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
		return false;
	}

	public boolean deleteAlumno(Alumno a) {
		// TODO Auto-generated method stub
		
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement("Delete from alumno WHERE  id = ?");
			
			preparedStatement.setInt(1, a.getId());
			
			preparedStatement.executeUpdate();
			
			
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return false;
	}

	public int getAllAlumnosCount() {
		// TODO Auto-generated method stub
		
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement("SELECT * from alumno");
			
			
			 count = preparedStatement.executeUpdate();
		
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
		return count;
	}

	public Alumno getAlumno(int id) {
		// TODO Auto-generated method stub
		
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		Alumno retrieved = null;
		
		try {
			preparedStatement = connection.prepareStatement("SELECT * from alumno WHERE id = ?");
			
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			
			rs.next();
			
			int retrievedId = rs.getInt(1);
			String retrievedName = rs.getString(2);
			int retrievedAge = rs.getInt(3);
			int retrievedGrade = rs.getInt(4);
			String retrievedEmail = rs.getString(5);
			
			
			retrieved = new Alumno(retrievedId, retrievedName, retrievedAge, retrievedGrade, retrievedEmail);
			
			rs.close();
			preparedStatement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exceptions
			System.out.println(e);
		}
		
		return retrieved;
	}

	public boolean updateAlumnoCalificacion(Alumno a, int calificacion) {
		// TODO Auto-generated method stub
		
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement("UPDATE alumno set grade = ? WHERE  id = ?");
			
			preparedStatement.setInt(1, calificacion);
			preparedStatement.setInt(2, a.getId());
			
			 preparedStatement.executeUpdate();
			
			
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return false;
	}

}
