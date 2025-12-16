package com.anudip.org;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumAssignment2 {

	public static void main(String[] args) throws InterruptedException {

		// task1();
		task2();
	}

	private static void task1() {

		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");

		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.selenium.dev");

		System.out.println("Page Title is: " + driver.getTitle());
		driver.quit();
	}

	private static void task2() throws InterruptedException {

		System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");

		WebDriver driver = new EdgeDriver();
		driver.get("https://www.google.com");

		driver.findElement(By.name("q")).sendKeys("Selenium WebDriver", Keys.ENTER);

		Thread.sleep(2000);
		System.out.println("Current URL: " + driver.getCurrentUrl());

		driver.quit();
	}
}
