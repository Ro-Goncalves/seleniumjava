package br.com.rogon.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import br.com.rogon.Urls;
import br.com.rogon.WebDriverUtil;

public class LoginPage extends WebDriverUtil{
    private WebDriver browser;    

    public LoginPage(){
        super(Urls.LOGIN);
        browser = super.getBrowser();
    }

    public void fillLoginForm(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void submitForm() {
        browser.findElement(By.id("login-form")).submit();
    }   

    public String getLoggedUserName() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();            
        } catch (NoSuchElementException e) {
            return null;
        }
    }     
}
