# Selenium: Testes Automatizados De Aceitação Em Java

## Controlando o navegador

E vamos ao tão esperado, ao menos por mim, Selenium. Vi algo sobre esse tema quando estudava um pouco de Python, já foi muito legal, e me deu ideias incrivéis de lugares em que eu poderia implementar alguma automatização, por exemplo a exclusão do Gravame. O foco do curso será a automatização do testes de aceitação, veja um grande potêncial dessa ferramenta na robotização de processos, e é isso que eu estou de olho, é aqui que quero chegar.

Voltamos ao tema do curso, testes, não o meu curso, isso que você está lendo não é um curso. **Pirâmide de testes** define uma forma de ver os testes, o quanto devemos ter de cada teste e o quando de custo teremos. Podemos definir três níveis de teste *Unidade, Integração e Ponta a Ponta*, cada um tem sua responsabilidade.

O teste de **Unidade** é o mais simples e barato de se criar, por isso tem um custo baixo e é criado em quantidade superior aos outros. Como o nome indica ele testa a menor unidade do sistema, algo entre classes e método. Não é meu foco aqui.

O teste de **Integração** está no meio; testa como as unidades interagem entre si; leva um pouco mais de tempo para serem criados; seu custo é maior do que o de **Unidade** e a quantidade menor. Não é meu foco aqui.

O teste **Ponta a Ponta** visa simular o comportamento do usuário na aplicação; tem um alto tempo de desenvolvimento e custo; só é criados para aqueles cenários realmente importantes. Esse é o meu foco.

Dentre as tantas ferramentes que existem para esse propósito, iremos utilizar o **Selenium WebDriver**. Agora um pouco sobre a configuração do ambiente. Estou utilizando o **mavem**. No *pom* precisamos colocar o seguinte:

```html
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-firefox-driver</artifactId>
</dependency>
```

Vale observar que em **artifactId** devemos escolher o que melhor se adequa ao nosso navegador. Além disso é preciso fazer o download do **geckodriver**, que também depende do navegador. É só dar um google que conseguiremos achar fácil.

E para não dizer que só configuramos o ambiente, segue o primeiro teste.

```java
@Test
public void hello(){
    System.setProperty("webdriver.gecko.driver", "assets/geckodriver.exe");
    WebDriver browser = new FirefoxDriver();
    browser.navigate().to("http://localhost:8080/leiloes");
    browser.quit();
}   
```

Contextualizando, no curso, da Alura, é passado um projeto que contém um site de leiloes, essa é a aplicação que iremos testar, por isso da linha `browser.navigate().to("http://localhost:8080/leiloes");`

Enfim, para saber mais, veja o curso. Procurarei aqui comentar mais sobre os testes em si.

## Testando O Login

Antes de escrever o código precisamos intender o que queremos fazer, para isso executamos o caso de uso manualmente em nossa aplicação.

## Referências

[ONEDAYTESTING - Pirâmide De Testes](https://blog.onedaytesting.com.br/piramide-de-teses/)

[Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/)
