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
import br.com.rogon.leilao.dto.NovoLeilaoDto;

public class LeiloesTest {
    
    private LeiloesPage leiloesPage;

    // @BeforeEach
    // void setUp(){
        
    // }

    @AfterEach
    void tearDown(){
        this.leiloesPage.close();
    }

    @Test
    void deveriaCadastrarLeilao(){
        WebDriver browser = new PagesUtil().login();
        this.leiloesPage = new LeiloesPage(browser);
        CadastroLeilaoPage paginaDeCadastro = leiloesPage.loadForm();

        String hoje = LocalDate.now()
                               .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // NovoLeilaoDto novoLeilao = new NovoLeilaoDto();
        // novoLeilao.setNome("Leilão Teste Dia: " + hoje);
        // novoLeilao.setValorInicial(new BigDecimal("500.00"));
        // novoLeilao.setDataAbertura(hoje);

        // Assertions.assertDoesNotThrow(() -> paginaDeCadastro.waitForLoading("tabela-leiloes"));
        // this.leiloesPage = paginaDeCadastro.cadastrarLeilao(novoLeilao);
        // Assertions.assertTrue(paginaDeCadastro.isLeilaoCadastrado(novoLeilao));
    }
}
