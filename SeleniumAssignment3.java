package com.anudip.org;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class SeleniumAssignment3 {

    WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        SeleniumAssignment3 assignment = new SeleniumAssignment3();

        assignment.setUp();
        assignment.labExercise1_GoogleSearch();
        assignment.labExercise2_LoginUsingLocators();
        assignment.labExercise3_LoginUsingCSS();
        assignment.tearDown();
    }

    
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

   
    public void labExercise1_GoogleSearch() throws InterruptedException {

        System.out.println("Lab Exercise 1: Google Search");

        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        List<WebElement> titles = driver.findElements(By.tagName("h3"));

        System.out.println("Search Results:");
        for (WebElement title : titles) {
            if (!title.getText().isEmpty()) {
                System.out.println(title.getText());
            }
        }
    }

    public void labExercise2_LoginUsingLocators() {

        System.out.println("\nLab Exercise 2: Login using ID, Name, ClassName");

        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username"))
                .sendKeys("student");

        driver.findElement(By.name("password"))
                .sendKeys("Password123");

        driver.findElement(By.className("btn"))
                .click();

        WebElement successMsg = driver.findElement(By.tagName("h1"));
        if (successMsg.getText().equals("Logged In Successfully")) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }
    }

    public void labExercise3_LoginUsingCSS() {

        System.out.println("\nLab Exercise 3: Login using CSS Selectors");

        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.cssSelector("#username"))
                .sendKeys("student");

        driver.findElement(By.cssSelector("input[name='password']"))
                .sendKeys("Password123");

        driver.findElement(By.cssSelector(".btn"))
                .click();

        System.out.println("CSS Selector Login Executed");
    }

    
    public void tearDown() {
        driver.quit();
    }
}

