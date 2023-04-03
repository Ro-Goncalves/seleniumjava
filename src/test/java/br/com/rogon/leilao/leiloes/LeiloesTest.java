package br.com.rogon.leilao.leiloes;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import br.com.rogon.PagesUtil;
import br.com.rogon.Urls;
import br.com.rogon.leilao.dto.NovoLeilaoDto;

public class LeiloesTest {
    
    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage paginaDeCadastro;
    private String hoje;
    private NovoLeilaoDto novoLeilao;

    @BeforeEach
    void setUp(){
        WebDriver browser = new PagesUtil().login();
        this.leiloesPage = new LeiloesPage(browser);
        this.paginaDeCadastro = leiloesPage.loadForm();
        this.novoLeilao = new NovoLeilaoDto();
        this.hoje = LocalDate.now()
                               .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @AfterEach
    void tearDown(){
        this.leiloesPage.close();
    }

    @Test
    void deveriaCadastrarLeilao(){        
        novoLeilao.setNome("Leilão Teste Dia: " + hoje);
        novoLeilao.setValorInicial(new BigDecimal("500.00"));
        novoLeilao.setDataAbertura(hoje);

        this.leiloesPage = paginaDeCadastro.cadastrarLeilao(novoLeilao);
        Assertions.assertTrue(paginaDeCadastro.isLeilaoCadastrado(novoLeilao));
    }

    @Test
    void deveriaValidarCadastroDeLeilao(){       
        this.leiloesPage = paginaDeCadastro.cadastrarLeilaoVazio();

        Assertions.assertDoesNotThrow(() -> paginaDeCadastro.waitForLoading("nome"));
        Assertions.assertTrue(this.paginaDeCadastro.isPageIn(Urls.LEILAO.getUrl()));
        Assertions.assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis());
        
    }
}
