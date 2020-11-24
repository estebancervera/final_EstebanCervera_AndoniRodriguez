package com.mayab.calidad.funcionales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestUpdateMERN {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\cerve\\Documents\\Calidad\\chromedriver.exe");
	driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  private void pause(long mils) {
	  try {
		  Thread.sleep(mils);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
  

 
  //UPDATE
  @Test
  public void testUpdateMern() throws Exception {
	  
	String[] expectedValuesBefore = {"Esteban", "test@gmail.com", "35", "m"};
		
	String[] actualValues = new String[4];
	
	int rowNumber = 1;
	
	driver.get("https://mern-crud.herokuapp.com/");
		
	WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
	pause(2000);
	List<WebElement> rows = table.findElements(By.tagName("tr"));
		   
	for(int i = 1; i < rows.size(); i++) {
		    	
		List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
		   
		for(int j = 0; j < columns.size()-1; j++) {
			
			actualValues[j] = columns.get(j).getText();
		    	   	
		}
		 if(Arrays.equals(expectedValuesBefore, actualValues)) {
			   rowNumber = i;
			   break;
		    }
	}
		    
    // BUTTON
    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr["+ rowNumber + "]/td[5]/button[1]")).click();
   
    
    //NAME
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Andres");
    
    //EMAIL
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("test2@gmail.com");

    
    //GENDER
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/i")).click();
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[3]/span")).click();
    
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
    pause(2000);
    
    
    //String msg = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
    //assertEquals(msg, "Successfully updated!");
    
    // La ventana de chrome debe estar lo suficiente mente delgada para que salga la tacha en la tarjeta de modificacion.
    driver.findElement(By.xpath("/html/body/div[2]/div/i")).click();
	
    WebElement tableCheck = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
    pause(2000);
    List<WebElement> rowsCheck = tableCheck.findElements(By.tagName("tr"));
   
    String[] expectedValuesAfter = {"Andres", "test2@gmail.com", "", ""};
    
    for(int i = 1; i < rowsCheck.size(); i++) {
    	
    	List<WebElement> columnsCheck = rowsCheck.get(i).findElements(By.tagName("td"));
   
    	for(int j = 0; j < columnsCheck.size()-1; j++) {
    		actualValues[j] = columnsCheck.get(j).getText();
    		System.out.println("i: " + i +  ",j: " + j + "---" + actualValues[j] ) ;
    	}
    	
    	if(Arrays.equals(expectedValuesAfter, actualValues)) {
    		break;
    	}
    	
    }
    
    for(int i = 0; i < expectedValuesAfter.length; i++) {
    	assertEquals(expectedValuesAfter[i], actualValues[i]);
    	}

    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}

