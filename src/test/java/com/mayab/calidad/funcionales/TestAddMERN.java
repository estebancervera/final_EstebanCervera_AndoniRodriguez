package com.mayab.calidad.funcionales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAddMERN {
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

	//ADD TEST
	  @Test
	  public void testAddMern() throws Exception {
		  
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
	    driver.findElement(By.name("email")).sendKeys("test@gmail.com");
	    
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
	    
	    //VERIFY
	    String text = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
	    assertEquals(text, "Nice one!");
	    
	    
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
