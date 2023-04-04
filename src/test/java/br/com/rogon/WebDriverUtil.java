package br.com.rogon;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WebDriverUtil {
    private WebDriver browser;
    private Wait<WebDriver> wait;

    public WebDriverUtil(Urls initialUrl){
        setWebDriver();   
        setWait();
        setManage();
        this.browser.navigate().to(initialUrl.getUrl());
    }

    public WebDriverUtil(WebDriver browser){
        this.browser = browser;
        setWait();
        setManage();
    }

    public WebDriverUtil(){
        setWebDriver();
        setWait();
        setManage();
    }

    private void setManage(){
        this.browser.manage()
                    .timeouts()
                    .implicitlyWait(5, TimeUnit.SECONDS);
    }

    private void setWebDriver(){
        System.setProperty("webdriver.gecko.driver", "assets/geckodriver.exe");
        this.browser = new FirefoxDriver();
    }

    private void setWait(){
        this.wait = new FluentWait<WebDriver>(browser)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofSeconds(2))
                        .ignoring(NoSuchElementException.class);
    }

    public WebDriver getBrowser() {
        return browser;
    }

    public Wait<WebDriver> getWait() {
        return wait;
    }

    public void close() {
        browser.close();
    }

    public void waitForLoading(String element) throws TimeoutException{                   
        wait.until(driver -> driver.findElement(By.id(element)));        
    }

    public boolean isPageIn(String page){
        System.out.println(this.browser.getCurrentUrl());
        return this.browser.getCurrentUrl().equals(page);
    }

    public boolean isInPageSource(String contains){
        return this.browser.getPageSource().contains(contains);
    }

    public void navigateTo(String url){       
        this.browser.navigate().to(url);
    }

}
