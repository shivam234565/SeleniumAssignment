package com.anudip.org;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;

public class SeleniumAssignment4 {

	WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		SeleniumAssignment4 assignment = new SeleniumAssignment4();

		assignment.setUp();
		assignment.labExercise1_WordToPdfUpload();
		assignment.labExercise2_NavigationCommands();
		assignment.labExercise3_WikipediaLoginCSS();
		assignment.tearDown();
	}

	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void labExercise1_WordToPdfUpload() throws InterruptedException {

		System.out.println("Lab Exercise 1: Word to PDF Conversion");

		driver.get("https://www.ilovepdf.com/word_to_pdf");

		 File file = new File("C:\\Users\\Public\\sample.docx"); 
		 WebElement uploadBtn = driver.findElement(By.cssSelector("input[type='file']"));
		 uploadBtn.sendKeys(file.getAbsolutePath());

		Thread.sleep(3000);

		driver.findElement(By.id("processTask")).click();

		System.out.println("Word file uploaded and conversion started");
		Thread.sleep(5000);
	}

	public void labExercise2_NavigationCommands() throws InterruptedException {

		System.out.println("Lab Exercise 2: Navigation Commands");

		driver.get("https://www.bing.com");
		Thread.sleep(2000);

		driver.navigate().to("https://www.wikipedia.org");
		Thread.sleep(2000);

		driver.navigate().back();
		Thread.sleep(2000);

		driver.navigate().forward();
		Thread.sleep(2000);

		driver.navigate().refresh();
		Thread.sleep(2000);

		driver.manage().window().setSize(new Dimension(800, 600));
		Thread.sleep(2000);

		System.out.println("Navigation commands executed");
	}

	public void labExercise3_WikipediaLoginCSS() throws InterruptedException {

		System.out.println("Lab Exercise 3: Wikipedia Login using CSS Selectors");

		driver.get("https://en.wikipedia.org/w/index.php?title=Special:UserLogin");

		driver.findElement(By.cssSelector("input[name^='wpName']")).sendKeys("testusername");

		driver.findElement(By.cssSelector("input[name*='Password']")).sendKeys("testpassword");

		driver.findElement(By.cssSelector("button[type$='submit']")).click();

		Thread.sleep(2000);

		// Verify login by URL
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("wiki")) {
			System.out.println("Login attempted, current URL: " + currentUrl);
		}
	}

	public void tearDown() {
		driver.quit();
	}
}
