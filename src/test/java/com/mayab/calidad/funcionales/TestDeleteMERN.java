package com.mayab.calidad.funcionales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestDeleteMERN {
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


	  
	  // DELETE TEST
	  
	  @Test
	  public void testDeleteMern() throws Exception {

		driver.get("https://mern-crud.herokuapp.com/");
		// TABLE
	    WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
	    pause(2000);
	    List<WebElement> rows_before_delete = table.findElements(By.tagName("tr"));
	    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[2]")).click();
	    pause(1000);
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();
	    pause(2000);
	    List<WebElement> rows_after_delete = table.findElements(By.tagName("tr"));
	    // VERIFY
	    assertEquals(rows_after_delete.size(), rows_before_delete.size()-1);
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
