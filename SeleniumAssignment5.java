package com.anudip.org;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class SeleniumAssignment5 {

    WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        SeleniumAssignment5 assignment = new SeleniumAssignment5();

        assignment.setUp();
        assignment.labExercise1_GoogleAutoSuggestion();
        assignment.labExercise2_Checkboxes();
        assignment.labExercise3_AlertBoxes();
        assignment.labExercise4_Dropdown();
        assignment.tearDown();
    }

    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void labExercise1_GoogleAutoSuggestion() throws InterruptedException {

        System.out.println("Lab Exercise 1: Google Auto Suggestion");

        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("java thread");

        Thread.sleep(2000);

        List<WebElement> suggestions =
                driver.findElements(By.xpath("//ul[@role='listbox']//li"));

        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().equalsIgnoreCase("java thread life cycle")) {
                suggestion.click();
                break;
            }
        }

        System.out.println("Selected suggestion: java thread life cycle");
    }

    public void labExercise2_Checkboxes() {

        System.out.println("\nLab Exercise 2: Checkboxes");

        driver.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> checkboxes =
                driver.findElements(By.cssSelector("input[type='checkbox']"));

        int alreadySelected = 0;
        int newlySelected = 0;

        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                alreadySelected++;
            } else {
                checkbox.click();
                newlySelected++;
            }
        }

        System.out.println("Total checkboxes: " + checkboxes.size());
        System.out.println("Already selected: " + alreadySelected);
        System.out.println("Newly selected: " + newlySelected);
    }

    public void labExercise3_AlertBoxes() throws InterruptedException {

        System.out.println("\nLab Exercise 3: Alert Boxes");

        driver.get("https://testpages.eviltester.com/styled/alerts/alert-test.html");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement alertBtn = driver.findElement(By.id("alertexamples"));
        js.executeScript("arguments[0].scrollIntoView(true);", alertBtn);
        js.executeScript("arguments[0].click();", alertBtn);

        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();

        Thread.sleep(1000);

        WebElement confirmBtn = driver.findElement(By.id("confirmexample"));
        js.executeScript("arguments[0].scrollIntoView(true);", confirmBtn);
        js.executeScript("arguments[0].click();", confirmBtn);

        alert = driver.switchTo().alert();
        System.out.println("Confirm Text: " + alert.getText());
        alert.dismiss();

        Thread.sleep(1000);

        WebElement promptBtn = driver.findElement(By.id("promptexample"));
        js.executeScript("arguments[0].scrollIntoView(true);", promptBtn);
        js.executeScript("arguments[0].click();", promptBtn);

        alert = driver.switchTo().alert();
        System.out.println("Prompt Text: " + alert.getText());
        alert.sendKeys("Selenium Test");
        alert.accept();
    }


    public void labExercise4_Dropdown() {

        System.out.println("\nLab Exercise 4: Dropdown");

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);

        select.selectByIndex(2);
        System.out.println("Selected by index: " +
                select.getFirstSelectedOption().getText());

        select.selectByValue("2");
        System.out.println("Selected by value: " +
                select.getFirstSelectedOption().getText());

        select.selectByVisibleText("Two");
        System.out.println("Selected by visible text: " +
                select.getFirstSelectedOption().getText());
    }

    public void tearDown() {
        driver.quit();
    }
}

