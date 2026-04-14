package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        // =========================
        // FIX FOR JENKINS CHROME
        // =========================
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // IMPORTANT FIX FOR "Chrome instance exited"
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--disable-software-rasterizer");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        try {

            // =========================
            // 1. SAUCE DEMO
            // =========================
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000);

            // =========================
            // 2. PRACTICE TEST AUTOMATION
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

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Step 1: Products
            WebElement productsLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/products']"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productsLink);

            Thread.sleep(2000);

            // Step 2: Add to cart
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".add-to-cart"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

            Thread.sleep(2000);

            // Step 3: Close modal
            WebElement closeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".close-modal"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);

            Thread.sleep(2000);

            System.out.println("ALL TESTS COMPLETED SUCCESSFULLY");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
