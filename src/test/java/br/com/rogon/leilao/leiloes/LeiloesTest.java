package br.com.rogon.leilao.leiloes;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import br.com.rogon.PagesUtil;

public class LeiloesTest {
    private LeiloesPage leiloesPage;    

    @AfterEach
    void tearDown(){
        this.leiloesPage.close();
    }

    @Test
    void deveriaCadastrarLeilao(){
        PagesUtil.login(leiloesPage.getBrowser());
    }
}
