package org.tiss.parser

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.tiss.crawler.Http

import org.tiss.model.Tabela

class TabelaParser {
    private final Http http = new Http()


    List<String> getHeaderPorId(String url, String idUnicoTabela) {
        List<String> header = []

        Document doc = http.buscarPagina(url)
        //lendo header e colocando na primeira opção
        for (Element th in doc.select("${idUnicoTabela} table > thead > tr > th")) {
            header.add(th.text())
//        println(th.text())
        }
        return header
    }

    List<List<String>> getTbodyPorId(String url, String idUnicoTabela) {
        List<List<String>> body = []

        Document doc = http.buscarPagina(url)
        //lendo body e colocando na primeira opção
        for (Element linha in doc.select("${idUnicoTabela} table > tbody > tr")) {
            List<String> linhaText = []
            for (Element column in linha.select("td")) {
                linhaText.add(column.text())
            }
            body.add(linhaText)
        }
        return body
    }

//  <Parte, ElementosDaParte>
    Tabela getTabela(String url, String idTabela) {
        List<String> header = getHeaderPorId(url, idTabela)
        List<List<String>> body = getTbodyPorId(url, idTabela)

        Tabela table = new Tabela(header,body)

        return table
    }

    List<List<String>> filterBody(List<List<String>> body, List<Integer> nums) {
        List<List<String>> bodyFiltrado = []

        for (List<String> linha in body) {
            List<String> linhaFiltrada = []
            bodyFiltrado.add(linhaFiltrada)
        }

        return bodyFiltrado
    }

    Tabela filterTable(Tabela table, List<Integer> numsCol) {

        List<String> tableHeaderFiltered = []
        List<List<String>> tableBodyFiltered = []
        Tabela tableFiltered = new Tabela(tableHeaderFiltered, tableBodyFiltered)

        table.header.eachWithIndex { String col, int i ->
            if (numsCol.contains(i)) {
                tableHeaderFiltered.add(col)
            }
        }
        tableFiltered.header = tableHeaderFiltered

        table.body.each { linha ->
            List<String> linhaFiltered = []

            linha.eachWithIndex { String coluna, int indiceColuna ->
                if (numsCol.contains(indiceColuna)) {
                    linhaFiltered.add(coluna)
                }
            }

            tableBodyFiltered.add(linhaFiltered)
        }
        tableFiltered.body = tableBodyFiltered

        return tableFiltered
    }

    Tabela filterRows(Tabela table, Closure<Boolean> condicao) {
        Tabela tableFiltered = new Tabela()

        tableFiltered.header = table.header

        // Aqui aplicamos o filtro no corpo da tabela,
        // lembrando q o findAll retorna uma lista de elementos que satisfazem a condição
        bodyFiltrado = table.body.findAll(condicao)

        tableFiltered.body = bodyFiltrado
        return tableFiltered
    }

}
