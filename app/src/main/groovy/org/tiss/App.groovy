package org.tiss

import org.tiss.crawler.TissLinks
import org.tiss.download.Downloader

import org.tiss.model.Tabela
import org.tiss.parser.TabelaParser
import org.tiss.storage.CsvWriter
//============================================================
//                  TAREFA 1: Baixar o Componente de Comunicação
//============================================================

TissLinks tissLinks = new TissLinks()
Downloader downloader = new Downloader()

downloader.baixarArquivo(
        tissLinks.urlDownloadComponente,
        "ComponenteComunicação.zip"
)

//============================================================
//                  TAREFA 2: LER TABELA EM HISTORICO TISS
//============================================================
def urlHistorico = tissLinks.urlUlHistoricoTISS

def tabelaParser = new TabelaParser()

Tabela tabelaHistoricoTiss = tabelaParser.getTabela(urlHistorico, "#parent-fieldname-text")
//printTabela(tabelaHistoricoTiss)

new CsvWriter().salvar(
        tabelaHistoricoTiss,
        "../../../../../output/historico_tiss.csv"
)

//============================================================
//                  funções auxiliares
//============================================================
void printTabela(Tabela tabela) {
    if (tabela == null) {
        println(null)
        return
    }
    def nColunas = tabela.header.size()
    def nLinhas = tabela.body.size()

    println(
            "---------------------" * nColunas +
            "\nlinhas: ${nLinhas}\t\tcolunas:${nColunas}\n"
    )
    tabela.header.each {coluna->printf("|${coluna}| ")}
    println("\n\n")
    tabela.body.eachWithIndex{
        linha,index->
        printf("\n[${String.format("%02d", index)}] ${linha}")
    }
}