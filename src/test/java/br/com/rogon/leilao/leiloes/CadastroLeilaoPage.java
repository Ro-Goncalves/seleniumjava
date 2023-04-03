package br.com.rogon.leilao.leiloes;

import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.rogon.WebDriverUtil;
import br.com.rogon.leilao.dto.NovoLeilaoDto;

public class CadastroLeilaoPage extends WebDriverUtil{
    private WebDriver browser;   

    public CadastroLeilaoPage(WebDriver browser) {
        super(browser);
        this.browser = browser;
    }

    public LeiloesPage cadastrarLeilao(NovoLeilaoDto novoLeilao) {
        this.browser.findElement(By.id("nome")).sendKeys(novoLeilao.getNome());
        this.browser.findElement(By.id("valorInicial")).sendKeys(novoLeilao.getValorInicial().toString());
        this.browser.findElement(By.id("dataAbertura")).sendKeys(novoLeilao.getDataAbertura());

        this.browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPage(this.browser);
    }

    public LeiloesPage cadastrarLeilaoVazio() {
        this.browser.findElement(By.id("nome")).sendKeys("");
        this.browser.findElement(By.id("valorInicial")).sendKeys("");
        this.browser.findElement(By.id("dataAbertura")).sendKeys("");

        this.browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPage(this.browser);
    }

    public boolean isLeilaoCadastrado(NovoLeilaoDto novoLeilao) {
        waitForLoading("tabela-leiloes");
        
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(novoLeilao.getNome());
    }

    public Boolean isMensagensDeValidacaoVisiveis() {
        Boolean retorno = false;
        retorno = 
            isInPageSource("minimo 3 caracteres")
            && isInPageSource("não deve estar em branco")
            && isInPageSource("deve ser um valor maior de 0.1")
            && isInPageSource("deve ser uma data no formato dd/MM/yyyy");

        return retorno;
            
    }

    
}
