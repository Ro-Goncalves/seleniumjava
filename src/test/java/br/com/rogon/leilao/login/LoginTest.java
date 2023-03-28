package br.com.rogon.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;

import br.com.rogon.Urls;

public class LoginTest {

    private LoginPage loginPage;
   
    @BeforeEach
    void setUp(){        
        loginPage = new LoginPage();
    }

    @AfterEach
    void tearDown(){
        this.loginPage.close();
    }
    
    @Test
    void deveriaEfetuarLoginComDadosValidos() throws InterruptedException{
        loginPage.fillLoginForm("fulano", "pass");
        loginPage.submitForm();
        loginPage.waitForLoading("usuario-logado");
        
        Assert.assertFalse(loginPage.isPageIn(Urls.LOGIN.getUrl()));
        Assert.assertEquals("fulano", loginPage.getLoggedUserName());       
    }

    @Test
    void naodeveriaLogarComDadosInvalidos() throws InterruptedException{  
        loginPage.fillLoginForm("invalido", "123123");
        loginPage.submitForm();

        Assertions.assertDoesNotThrow(() ->  loginPage.waitForLoading("alert-danger"));   
        Assert.assertTrue(loginPage.isPageIn(String.join("",Urls.LOGIN.getUrl(), "?error")));
        Assert.assertTrue(loginPage.isInPageSource("Usuário e senha inválidos."));
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado() throws InterruptedException{ 
        loginPage.navigateTo(String.join("", Urls.LEILAO.getUrl(),"/2"));
       
        Assertions.assertThrows(TimeoutException.class, () -> loginPage.waitForLoading("leiloes-cadastrados"));      
        Assert.assertTrue(loginPage.isPageIn(Urls.LOGIN.getUrl()));  
    }
}
