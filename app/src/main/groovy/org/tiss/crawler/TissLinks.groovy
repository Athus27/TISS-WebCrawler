package org.tiss.crawler


class TissLinks {
    public static Navegador navegadorTiss = new Navegador()

    public static String urlBase
    public static String urlEspacoPortador

    public static String urlTISS
    public static String urlUltimaVersaoTISS
    public static String urlUlHistoricoTISS

    public static String urlDownloadComponente


    TissLinks() {
        urlBase = "https://www.gov.br/ans/pt-br"
        urlEspacoPortador = linkEspacoPortador(urlBase)
        urlTISS = linkTISS(urlEspacoPortador)
        urlUltimaVersaoTISS = linkUltimaVersaoTISS(urlTISS)
        urlDownloadComponente = linkDownloadComponenteComunicacao(urlUltimaVersaoTISS)
        urlUlHistoricoTISS = urlHistoricoPadraoTISS(urlTISS)
    }

    String linkEspacoPortador(String url) {
        return navegadorTiss.navegarEBuscar(url, "div.cover-banner-tile.tile-content div > a[href]") {
            String href ->
                href.containsIgnoreCase("prestador")
        }
    }


    String linkTISS(String urlPortador) {
        return navegadorTiss.navegarEBuscar(urlPortador, "a.govbr-card-content[href]") { String href ->
            href.containsIgnoreCase("troca") &&
            href.containsIgnoreCase("informacao") &&
            href.containsIgnoreCase("suplementar")
        }
    }

    // Entrar na página da versão mais recente do TISS
    String linkUltimaVersaoTISS(String urlTISS) {
        return navegadorTiss.navegarEBuscar(urlTISS, "a[href]") { String href ->
            // As páginas de versão específica sempre contêm "padrao-tiss-" seguido do mês
            href.containsIgnoreCase("padrao-tiss-")
        }
    }

    //Pega o link do histórico de versões do TISS, que é uma página separada
    String urlHistoricoPadraoTISS(String urlTISS) {
        String urlHistoricoTISS = navegadorTiss.navegarEBuscar(urlTISS, "a.internal-link[href]") { String href ->
            href.containsIgnoreCase("historico") &&
            href.containsIgnoreCase("versoes")
        }
        return urlHistoricoTISS
    }

    //usadno "estrutura" para fugir do bug do "Contedoe"
    String linkDownloadComponenteComunicacao(String urlVersaoTISS) {
        return navegadorTiss.navegarEBuscar(urlVersaoTISS, "a[href]") { String href ->
            href.containsIgnoreCase("padro") &&//Padrão
            href.containsIgnoreCase("tiss") &&
            href.containsIgnoreCase("comunicao") &&
            // comunicação
            href.toLowerCase().endsWith(".zip")     // Garante que é o botão de download
        }
    }


}
