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

public class TestRetrieveMERN {
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
	  
	  
	  //READ
	  @Test
	  public void testRetrieveMern() throws Exception {
		
		String[] expectedValues = {"Esteban", "test@gmail.com", "35", "m"};
		
		String[] actualValues = new String[4];
		
		driver.get("https://mern-crud.herokuapp.com/");
		
	    WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
	    pause(2000);
	    List<WebElement> rows = table.findElements(By.tagName("tr"));
	   
	    for(int i = 1; i < rows.size(); i++) {
	    	
	    	List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
	   
	    	for(int j = 0; j < columns.size()-1; j++) {
	    		actualValues[j] = columns.get(j).getText();
	    		System.out.println("i: " + i +  ",j: " + j + "---" + actualValues[j] ) ;
	    	}
	    	
	    	if(Arrays.equals(expectedValues, actualValues)) {
	  
	    		break;
	    	}
	    	
	    }
	    
	    for(int i = 0; i < expectedValues.length; i++) {
	    	assertEquals(expectedValues[i], actualValues[i]);
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
