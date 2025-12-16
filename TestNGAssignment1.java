package com.anudip.org;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGAssignment1 {

    WebDriver driver;

    @BeforeClass
    public void setupAmazon() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
    }

    @Test(priority = 1)
    public void amazonTitleValidation() {
        String title = driver.getTitle();
        System.out.println("Amazon Title: " + title);
        Assert.assertTrue(title.contains("Amazon"), "Title validation failed!");
    }

    @Test(priority = 2)
    public void amazonUrlValidation() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Amazon URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("amazon.in"), "URL validation failed!");
    }

    @AfterClass
    public void teardownAmazon() {
        driver.quit();
    }

    @BeforeMethod
    public void setupSnapdeal() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 3)
    public void snapdealTitleAndSearchBoxValidation() {
        String title = driver.getTitle();
        System.out.println("Snapdeal Title: " + title);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Snapdeal URL: " + currentUrl);

        WebElement searchBox = driver.findElement(By.id("inputValEnter")); // Snapdeal search box ID
        System.out.println("Search box displayed: " + searchBox.isDisplayed());
        System.out.println("Search box enabled: " + searchBox.isEnabled());
    }

    @AfterMethod
    public void teardownSnapdeal() {
        driver.quit();
    }

    @BeforeClass
    public void setupFlipkart() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com");

        try {
            WebElement closeLoginPopup = driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z"));
            if (closeLoginPopup.isDisplayed()) {
                closeLoginPopup.click();
                System.out.println("Flipkart login popup closed.");
            }
        } catch (Exception e) {
            System.out.println("Flipkart login popup did not appear.");
        }
    }

    @Test(priority = 4)
    public void flipkartTitleValidation() {
        String title = driver.getTitle();
        System.out.println("Flipkart Title: " + title);
        Assert.assertTrue(title.contains("Flipkart"), "Flipkart title validation failed!");
    }

    @Test(priority = 5)
    public void flipkartUrlValidation() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Flipkart URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("flipkart.com"), "Flipkart URL validation failed!");
    }

    @Test(priority = 6)
    public void flipkartSearchBoxValidation() {
        WebElement searchBox = driver.findElement(By.name("q")); 
        System.out.println("Search box displayed: " + searchBox.isDisplayed());
        System.out.println("Search box enabled: " + searchBox.isEnabled());
    }

    @Test(priority = 7)
    public void flipkartSearchLaptops() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Laptops");
        searchBox.submit();
        System.out.println("Searched for Laptops on Flipkart.");
    }

    @AfterClass
    public void teardownFlipkart() {
        driver.quit();
    }
}

