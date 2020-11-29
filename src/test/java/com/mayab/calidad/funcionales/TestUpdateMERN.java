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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestUpdateMERN {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  public static final String URL = "https://esteban_cervera:0706ac1c-19d9-450a-a786-a5f40767e103@ondemand.us-west-1.saucelabs.com:443/wd/hub";
  
  @Before
  public void setUp() throws Exception {
	  DesiredCapabilities caps = DesiredCapabilities.firefox();
	    caps.setCapability("platform", "Windows 10");
	    caps.setCapability("platform", "Linux");
	    caps.setCapability("platform", "macOS 10.13");
	    caps.setCapability("version", "latest");
	    caps.setCapability("name", "Firefox");
	    caps.setCapability("extendedDebugging", "true");
	    caps.setCapability("buildNumber", "3.0");
	    driver = new RemoteWebDriver(new java.net.URL(URL), caps);
	    
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  private void pause(long mils) {
	  try {
		  Thread.sleep(mils);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
  public void addUser() {
	  driver.get("https://mern-crud.herokuapp.com/");
	    
	    // BUTTON
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	    
	    // NAME
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Esteban");
	    
	    // EMAIL
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("test4@gmail.com");
	    
	    // AGE
	    driver.findElement(By.name("age")).click();
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("35");
	    
	    //GENDER
	    driver.findElement(By.xpath("//div[3]/div[2]/div/i")).click();
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div")).click();
	    
	    // BUTTON
	    driver.findElement(By.xpath("//form/button")).click();
	    
	    // WAIT FOR ALERT
	    pause(3000);
  }
  
  public void deleteUser() {
	  driver.get("https://mern-crud.herokuapp.com/");
		// TABLE
	    WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
	    pause(2000);
	    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[2]")).click();
	    pause(1000);
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();
	    pause(2000);
  }

 
  //UPDATE
  @Test
  public void testUpdateMern() throws Exception {
	  
	  addUser();
	  
	String[] expectedValuesBefore = {"Esteban", "test4@gmail.com", "35", "m"};
		
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
    driver.findElement(By.name("email")).sendKeys("test5@gmail.com");

    
    //GENDER
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/i")).click();
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[3]/span")).click();
    
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
    pause(2000);
    
    
    String msg = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
    
    if (msg.equals("Successfully updated!")){
    	System.out.println("UPDATE PASO");
	      ((JavascriptExecutor)driver).executeScript("sauce:job-result=passed");
	     
	    }
	else {
	      ((JavascriptExecutor)driver).executeScript("sauce:job-result=failed");
	    }
    
    assertEquals(msg, "Successfully updated!");
    deleteUser();
    
	driver.quit();
    
//    // La ventana de chrome debe estar lo suficiente mente delgada para que salga la tacha en la tarjeta de modificacion.
//    driver.findElement(By.xpath("/html/body/div[2]/div/i")).click();
//	
//    WebElement tableCheck = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
//    pause(2000);
//    List<WebElement> rowsCheck = tableCheck.findElements(By.tagName("tr"));
//   
//    String[] expectedValuesAfter = {"Andres", "test2@gmail.com", "", ""};
//    
//    for(int i = 1; i < rowsCheck.size(); i++) {
//    	
//    	List<WebElement> columnsCheck = rowsCheck.get(i).findElements(By.tagName("td"));
//   
//    	for(int j = 0; j < columnsCheck.size()-1; j++) {
//    		actualValues[j] = columnsCheck.get(j).getText();
//    		System.out.println("i: " + i +  ",j: " + j + "---" + actualValues[j] ) ;
//    	}
//    	
//    	if(Arrays.equals(expectedValuesAfter, actualValues)) {
//    		break;
//    	}
//    	
//    }
//    
//    for(int i = 0; i < expectedValuesAfter.length; i++) {
//    	assertEquals(expectedValuesAfter[i], actualValues[i]);
//    	}

    
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

