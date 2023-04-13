# Selenium: Testes Automatizados De Aceitação Em Java

## Controlando o navegador

E vamos ao tão esperado, ao menos por mim, Selenium. Vi algo sobre esse tema quando estudava um pouco de Python, já foi muito legal, e me deu ideias incrivéis de lugares em que eu poderia implementar alguma automatização. O foco do curso será a automatização do testes de aceitação, veja um grande potêncial dessa ferramenta na robotização de processos, e é isso que eu estou de olho, é aqui que quero chegar.

Primeiro a motivação do curso: **Pirâmide de testes**, ela define uma forma de ver os testes, o quanto devemos ter de cada teste e o quando de custo teremos. Podemos definir três níveis de teste *Unidade, Integração e Ponta a Ponta*, cada um tem sua responsabilidade.

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

Enfim, para saber mais, veja o curso.

## Page Objects

Essa é uma recomendação do desenvolvedor do Selenium, com esse padrão conseguimos separar o código que controla a página dó código que realizará as acertivas, isso facilita muito a vida, pois possibilita que reutilizemos os POs. Não tem muito mais o que falar, em resumo é isso aí.

## Alguns comando úteis

Primeiro de tudo devemos instnaciar um `WebDriver`, vamos chama-lo de **browser**. Cada navegador terá seu próprio WebDrive

```java
private void setWebDriver(){
    System.setProperty("webdriver.gecko.driver", "assets/geckodriver.exe");
    this.browser = new FirefoxDriver();   
}
```

Isso fará com que o Selenium abrar uma instância do navagador, e com esse objeto teremos o poder de manupular essa página.

Primeiro teremos que navagar até o elemento que quereremos realizar alguma operação sobre, para isso podemos usar `browser.findElement`, e para saber qual o objeto estamos procurando, podemos usar a classe `By`.

```java
browser.findElement(By.id("username"))
```

Existem varias formas de buscar um elemento, conforme aparecer algum caso *diferentão* basta olhar a documentação.

Agora podemos executar algumas ações sobre esse elemento, por exemplo digitar alguma mensagem com `sendKeys("fulano")`

```java
browser.findElement(By.id("username")).sendKeys("fulano");
```

Enviar um formulário com `submit()`

```java
browser.findElement(By.id("login-form")).submit();
```

Navegar entre as urls com `navigate().to()`

```java
browser.navigate().to(url);
```

Buscar a url atual com `getCurrentUrl()`

```java
browser.getCurrentUrl();
```

Pegar todo o conteúdo da página com `getPageSource()`

```java
browser.getPageSource();
```

O nevagador pode demorar um pouco para carregar a página, é legar criar um `FluentWait`.

```java
private void setWait(){
    this.wait = new FluentWait<WebDriver>(browser)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);
}
```

Ou um `implicitlyWait`.

```java
private void setManage(){
    this.browser.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
}
```

Podemos pegar o testo do elemento com `getText()`

```java
browser.findElement(By.id("usuario-logado")).getText(); 
```

Enfim, existe uma infinidade de coisas que da para fazer com essa biblioteca, com o que foi exposto aqui já da para brincar bastante.

FIM???

## Referências

[ONEDAYTESTING - Pirâmide De Testes](https://blog.onedaytesting.com.br/piramide-de-teses/)

[Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/)
