package com.mayab.calidad.dbunit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mayab.calidad.dao.*;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import java.io.File;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;

public class TestAlumnoOracleSQL extends DBTestCase {

	
	public TestAlumnoOracleSQL(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://127.0.0.1:3306/alumno");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "travis");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");	
	}
	
	@Before
	public void setUp() throws Exception{
		super.setUp();
		IDatabaseConnection connection = getConnection();
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} finally {
			connection.close();
		}
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		//InputStream xmlFile = getClass().getResourceAsStream("src/resources/alumno_init.xml");
		return new FlatXmlDataSetBuilder().build(new File("src/resources/alumno_init.xml"));
	}
	
	
	//INSERT TEST --- BOTH COUNT AND WITH DATASET
	
	@Test
	public void testInsert() {
		Alumno alumno = new Alumno(4, "Esteban C", 23, 9, "esteban@gmail.com");
		AlumnoDaoMySQL daoMySQL = new AlumnoDaoMySQL();
		
		daoMySQL.addAlumno(alumno);
		
		//verify
		try {
			IDataSet databaseDataSet = getConnection().createDataSet();
			
			ITable actualTable = databaseDataSet.getTable("alumno");
			
			//InputStream xmlFileInputStream = getClass().getResourceAsStream("src/resources/insert_result.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumno");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}

	 
	
	@Test
	public void testInsertCount() {
		
		Alumno alumno = new Alumno(5, "Esteban 5", 25, 10, "esteban@gmail.com");
		AlumnoDaoMySQL daoOracle = new AlumnoDaoMySQL();
		
		IDatabaseConnection connection;
		
		try {
			
			connection = getConnection();
			
			int actualRows = connection.getRowCount("alumno");
			daoOracle.addAlumno(alumno);
			assertEquals(actualRows + 1, connection.getRowCount("alumno"));
			connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//DELETE TEST --- BOTH COUNT AND WITH DATASET
	
	@Test
	public void testDelete() {
		
		Alumno alumno = new Alumno(2, "Esteban 2", 22, 9, "esteban@gmail.com");
		AlumnoDaoMySQL daoMySQL = new AlumnoDaoMySQL();
		
		daoMySQL.deleteAlumno(alumno);
		
		//verify
		try {
			IDataSet databaseDataSet = getConnection().createDataSet();
			
			ITable actualTable = databaseDataSet.getTable("alumno");
			
			
			
			//InputStream xmlFileInputStream = getClass().getResourceAsStream("src/resources/insert_result.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/delete_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumno");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	
	
	@Test
	public void testDeleteCount() {
		
		Alumno alumno = new Alumno(3, "Esteban 3", 23, 10, "esteban@gmail.com");
		AlumnoDaoMySQL daoOracle = new AlumnoDaoMySQL();
		
		daoOracle.getAlumno(alumno.getId());
		IDatabaseConnection connection;
		
		try {
			
			connection = getConnection();
			
			int actualRows = connection.getRowCount("alumno");
			daoOracle.deleteAlumno(alumno);
			assertEquals(actualRows - 1, connection.getRowCount("alumno"));
			connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//RETRIEVE TEST
	
	
	@Test
	public void testGetAlumno() throws Exception {

		Alumno alumno = new Alumno(3, "Esteban 3", 23, 10, "esteban@gmail.com");
		
		//AlumnoDaoOracleSQL daoOracle = new AlumnoDaoOracleSQL();
		
		try {
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/retrieve_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumno");
			
			ITable actualData = getConnection().createQueryTable("result_alumno", "SELECT * FROM alumno WHERE id = " + alumno.getId());
			
			Assertion.assertEquals(expectedTable, actualData);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	
//	//GET ALL COUNT TEST
//	
//	@Test
//	public void testGetAllCount() {
//		
//		AlumnoDaoMySQL daoOracle = new AlumnoDaoMySQL();
//		
//		
//		IDatabaseConnection connection;
//		
//		try {
//			
//			connection = getConnection();
//			
//			int actualRows = connection.getRowCount("alumno");
//			int expectedResult = daoOracle.getAllAlumnosCount();
//			assertEquals(actualRows, expectedResult);
//			connection.close();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
	
	//UPDATE TEST
	
	@Test
	public void testUpdate() {
		Alumno alumno = new Alumno(3, "Esteban 3", 23, 10, "esteban@gmail.com");
		AlumnoDaoMySQL daoMySQL = new AlumnoDaoMySQL();
		
		daoMySQL.updateAlumnoCalificacion(alumno, 5);
		
		//verify
		try {
			IDataSet databaseDataSet = getConnection().createDataSet();
			
			ITable actualTable = databaseDataSet.getTable("alumno");
			

			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/update_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumno");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	


}
 