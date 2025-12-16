package com.anudip.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.Set;

public class SeleniumAssignment8 {

    WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        SeleniumAssignment8 assignment = new SeleniumAssignment8();

        assignment.labExercise1_WindowHandling();

        assignment.labExercise2_iFrame();

        assignment.labExercise3_Actions();
    }

    public void labExercise1_WindowHandling() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demo.guru99.com/popup.php");

        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window ID: " + parentWindow);

        driver.findElement(By.linkText("Click Here")).click();

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("Child Window Title: " + driver.getTitle());

        driver.switchTo().window(parentWindow);
        System.out.println("Back to Parent Window Title: " + driver.getTitle());

        driver.quit();
    }

    public void labExercise2_iFrame() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/frames");

        driver.switchTo().frame("frame2");

        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String extractedText = heading.getText();

        System.out.println("Extracted Text: " + extractedText);

        driver.switchTo().defaultContent();

        System.out.println("Successfully switched back to main page and extracted text.");

        driver.quit();
    }

    public void labExercise3_Actions() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.flipkart.com/");

        try {
            WebElement closeLoginPopup = driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z"));
            if (closeLoginPopup.isDisplayed()) {
                closeLoginPopup.click();
            }
        } catch (Exception e) {
            System.out.println("Login popup did not appear.");
        }

        Actions actions = new Actions(driver);
        WebElement electronicsMenu = driver.findElement(By.xpath("//div[text()='Electronics']"));
        actions.moveToElement(electronicsMenu).perform();
        System.out.println("Hovered over Electronics menu.");

        actions.contextClick(electronicsMenu).perform();
        System.out.println("Performed right-click on Electronics menu.");

        Thread.sleep(2000); 
        driver.quit();
    }
}
