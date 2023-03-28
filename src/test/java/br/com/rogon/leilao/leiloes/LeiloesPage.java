package br.com.rogon.leilao.leiloes;

import org.openqa.selenium.WebDriver;

import br.com.rogon.WebDriverUtil;

public class LeiloesPage extends WebDriverUtil{

    private WebDriver browser;    

    public LeiloesPage(WebDriver browser){
        super(browser);
        browser = super.getBrowser();
    }

}
