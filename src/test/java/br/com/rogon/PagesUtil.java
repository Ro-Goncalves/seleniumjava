package br.com.rogon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PagesUtil {
    private WebDriverUtil webDriverUtil;
    private WebDriver browser;

    public WebDriver login(){
        webDriverUtil = new WebDriverUtil(Urls.LOGIN);
        browser = webDriverUtil.getBrowser();
        
        webDriverUtil.waitForLoading("username");

        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");

        browser.findElement(By.id("login-form")).submit();

        webDriverUtil.waitForLoading("tabela-leiloes");

        return browser;
    }
}
