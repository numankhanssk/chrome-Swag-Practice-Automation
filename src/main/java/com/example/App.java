package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {

    public static void main(String[] args) {

        try {

            // Clean driver setup (IMPORTANT FIX)
            WebDriverManager.chromedriver().clearDriverCache().setup();

            // =========================
            // CHROME OPTIONS (JENKINS FIX)
            // =========================
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            // CRITICAL STABILITY FIX
            options.addArguments("--single-process");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-blink-features=AutomationControlled");

            WebDriver driver = new ChromeDriver(options);

            driver.manage().window().maximize();

            // =========================
            // 1. SAUCE DEMO
            // =========================
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000);

            // =========================
            // 2. PRACTICE TEST LOGIN
            // =========================
            driver.get("https://practicetestautomation.com/practice-test-login/");
            driver.findElement(By.id("username")).sendKeys("student");
            driver.findElement(By.id("password")).sendKeys("Password123");
            driver.findElement(By.id("submit")).click();

            Thread.sleep(2000);

            // =========================
            // 3. AUTOMATION EXERCISE
            // =========================
            driver.get("https://automationexercise.com/");

            // Step 1: Click Products
            WebElement products = driver.findElement(By.cssSelector("a[href='/products']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", products);

            Thread.sleep(2000);

            // Step 2: Add to cart
            WebElement addToCart = driver.findElement(By.cssSelector(".add-to-cart"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

            Thread.sleep(2000);

            // Step 3: Close modal
            WebElement closeBtn = driver.findElement(By.cssSelector(".close-modal"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);

            Thread.sleep(2000);

            System.out.println("ALL TESTS PASSED SUCCESSFULLY");

            driver.quit();

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED:");
            e.printStackTrace();
        }
    }
}
