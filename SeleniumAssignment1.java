package com.anudip.org;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SeleniumAssignment1 {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
	//	driver = new ChromeDriver();
	//	driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//	driver.get("https://practicetestautomation.com/practice-test-login/");
		
	   driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.get("https://www.google.com");
	}

	@Test
	public void loginWithValidCredentials() {

		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("student");

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Password123");

		WebElement loginBtn = driver.findElement(By.className("btn"));
		loginBtn.click();

		WebElement successMsg = driver.findElement(By.tagName("h1"));
		Assert.assertEquals(successMsg.getText(), "Logged In Successfully", "Login was not successful");
	}
	
	   @Test
	    public void googleSearchAndPrintResults() throws InterruptedException {

	        WebElement searchBox = driver.findElement(By.name("q"));
	        searchBox.sendKeys("Selenium WebDriver");

	        searchBox.sendKeys(Keys.ENTER);

	        Thread.sleep(2000); 

	        List<WebElement> resultTitles = driver.findElements(By.cssSelector("h3"));

	        System.out.println("Search Results:");
	        for (WebElement title : resultTitles) {
	            if (!title.getText().isEmpty()) {
	                System.out.println(title.getText());
	            }
	        }
	   }
	   
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
