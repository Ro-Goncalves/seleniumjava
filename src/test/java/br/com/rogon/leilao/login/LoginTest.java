package br.com.rogon.leilao.login;

import java.time.Duration;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class LoginTest {

    /**
     *
     */
    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LEILAO = "http://localhost:8080/leiloes";
    private WebDriver browser;
    private Wait<WebDriver> wait;

    @BeforeAll
    static void beforeAll(){
        System.setProperty("webdriver.gecko.driver", "assets/geckodriver.exe");
    }

    @BeforeEach
    void setUp(){        
        this.browser = new FirefoxDriver();
        this.browser.navigate().to(URL_LOGIN);

        this.wait = new FluentWait<WebDriver>(browser)
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofSeconds(2))
        .ignoring(NoSuchElementException.class);
    }

    @AfterEach
    void tearDown(){
        this.browser.quit();
    }
    
    @Test
    void deveriaEfetuarLoginComDadosValidos() throws InterruptedException{
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();       

        wait.until(driver -> driver.findElement(By.id("usuario-logado")));
        
        Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

       
    }

    @Test
    void naodeveriaLogarComDadosInvalidos() throws InterruptedException{  
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123123");
        browser.findElement(By.id("login-form")).submit();
       
        Assertions.assertDoesNotThrow(() ->  wait.until(driver -> driver.findElement(By.id("alert-danger"))));        
        Assert.assertTrue(browser.getCurrentUrl().equals(String.join("",URL_LOGIN, "?error")));
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        

        browser.quit();
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado() throws InterruptedException{  
        browser.navigate().to(String.join("", URL_LEILAO,"/2"));
       
        Assertions.assertThrows(TimeoutException.class, () ->  wait.until(driver -> driver.findElement(By.id("leiloes-cadastrados"))));        
        Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));   

        browser.quit();
    }
}
