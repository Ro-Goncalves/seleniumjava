package br.com.rogon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PagesUtil {
    public static void login(WebDriver browser){
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");

        browser.findElement(By.id("login-form")).submit();
    }
}
