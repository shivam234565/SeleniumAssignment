package com.anudip.org;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumAssignment6 {

    WebDriver driver;
    WebDriverWait wait;

    public static void main(String[] args) {

        SeleniumAssignment6 assignment = new SeleniumAssignment6();

        assignment.setUp();
        assignment.labExercise1_AddItemToCart();
        assignment.labExercise2_SearchAndExtractProducts();
        assignment.tearDown();
    }

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public void labExercise1_AddItemToCart() {

        System.out.println("Lab Exercise 1: Add Item to Cart");

        driver.get("https://demowebshop.tricentis.com/login");

        driver.findElement(By.id("Email"))
              .sendKeys("testuser@gmail.com");

        driver.findElement(By.id("Password"))
              .sendKeys("Test@123");

        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("small-searchterms")));
        searchBox.sendKeys("Laptop");

        driver.findElement(By.cssSelector("input[value='Search']")).click();

        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[value='Add to cart']")));
        addToCartBtn.click();

        WebElement cartQty = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("span.cart-qty")));

        System.out.println("Total items in cart: " + cartQty.getText());
    }

    public void labExercise2_SearchAndExtractProducts() {

        System.out.println("\nLab Exercise 2: Extract Product Names & Prices");

        driver.get("https://demowebshop.tricentis.com/");

        WebElement searchBox = driver.findElement(By.id("small-searchterms"));
        searchBox.sendKeys("Laptop");
        driver.findElement(By.cssSelector("input[value='Search']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".product-item")));

        List<WebElement> products =
                driver.findElements(By.cssSelector(".product-item"));

        for (WebElement product : products) {

            String productName =
                    product.findElement(By.cssSelector("h2.product-title")).getText();

            String price;
            try {
                price = product.findElement(By.cssSelector("span.price")).getText();
            } catch (Exception e) {
                price = "Price not available";
            }

            // 7. Print product name & price
            System.out.println("Product: " + productName + " | Price: " + price);
        }
    }

    public void tearDown() {
        driver.quit();
    }
}
