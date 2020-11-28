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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestRetrieveMERN {
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
