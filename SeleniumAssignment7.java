package com.anudip.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class SeleniumAssignment7 {

	WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		SeleniumAssignment7 assignment = new SeleniumAssignment7();

		assignment.labExercise1_Login();

		assignment.labExercise2_ExplicitWait();

		assignment.labExercise3_FluentWait();
	}

	public void labExercise1_Login() {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://practicetestautomation.com/practice-test-login/");

		driver.findElement(By.id("username")).sendKeys("student");

		driver.findElement(By.id("password")).sendKeys("Password123");

		driver.findElement(By.id("submit")).click();

		WebElement successMessage = driver.findElement(By.cssSelector(".post-title"));
		if (successMessage.isDisplayed()) {
			System.out.println("Lab 1: Login Successful! Message: " + successMessage.getText());
		} else {
			System.out.println("Lab 1: Login Failed!");
		}

		driver.quit();
	}

	public void labExercise2_ExplicitWait() {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.myntra.com/");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchBar = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.desktop-searchBar")));

		searchBar.sendKeys("Shoes");

		WebElement suggestionList = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.desktop-group")));

		System.out.println("Lab 2: Search suggestions are displayed.");

		driver.quit();
	}

	public void labExercise3_FluentWait() {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://aln.anudip.org/login/index.php");

		driver.findElement(By.id("username")).sendKeys("invalidUser");
		driver.findElement(By.id("password")).sendKeys("wrongPassword");

		driver.findElement(By.id("loginbtn")).click();

		FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

		WebElement errorMessage = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div.alert.alert-danger"));
			}
		});

		System.out.println("Lab 3: Error message displayed: " + errorMessage.getText());

		driver.quit();
	}
}
