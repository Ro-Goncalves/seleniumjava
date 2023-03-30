package br.com.rogon;

public enum Urls {
    LOGIN {
        @Override
        public String getUrl() {
            return "http://localhost:8080/login";
        }
    }, 
    LEILAO {
        @Override
        public String getUrl() {
            return "http://localhost:8080/leiloes";
        }
    },
    LEILAO_CADASTRO {
        @Override
        public String getUrl() {
            return "http://localhost:8080/leiloes/new";
        }
    }; 

    public abstract String getUrl();
}
