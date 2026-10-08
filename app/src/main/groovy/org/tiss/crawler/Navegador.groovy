package org.tiss.crawler

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class Navegador {
    private static Http navegador = new Http()

    String navegarEBuscar(String url, String seletorCss, Closure<Boolean> condicao) {
        Document doc = navegador.buscarPagina(url)

        for (Element link in doc.select(seletorCss)) {
            String href = link.attr("abs:href")

            // Se a condição for verdadeira para esse href, retorna direto
            if (condicao(href)) {
                return href
            }
        }
        println("não encontrou")
        return ''
    }


}
