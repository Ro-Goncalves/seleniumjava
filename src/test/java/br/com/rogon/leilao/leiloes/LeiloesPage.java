package br.com.rogon.leilao.leiloes;

import org.openqa.selenium.WebDriver;

import br.com.rogon.Urls;
import br.com.rogon.WebDriverUtil;

public class LeiloesPage extends WebDriverUtil{
    private WebDriver browser;    

    public LeiloesPage(WebDriver browser){
        super(browser);
        this.browser = browser;       
    }

    public CadastroLeilaoPage loadForm(){
        navigateTo(Urls.LEILAO_CADASTRO.getUrl());
        waitForLoading("button-submit");
        
        return new CadastroLeilaoPage(browser);
    }

}
