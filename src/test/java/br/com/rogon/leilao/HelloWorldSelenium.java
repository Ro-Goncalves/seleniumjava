package br.com.rogon.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloWorldSelenium {

    @Test
    public void hello(){
        System.setProperty("webdriver.gecko.driver", "assets/geckodriver.exe");
        WebDriver browser = new FirefoxDriver();
        browser.navigate().to("http://localhost:8080/leiloes");
        browser.quit();
    }    
}
