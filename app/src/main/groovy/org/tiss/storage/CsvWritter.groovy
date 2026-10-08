package org.tiss.storage

import org.tiss.model.Tabela

class CsvWriter {

    void salvar(Tabela tabela, String caminho) {
        File arquivo = new File(caminho)
        arquivo.parentFile.mkdirs()

        arquivo.withWriter('UTF-8') { writer ->
            writer.writeLine(formatar(tabela.header))

            tabela.body.each { linha ->
                writer.writeLine(formatar(linha))
            }
        }
        println("CSV salvo em: ${arquivo.path}")
    }

    private String formatar(List<String> linha) {
        linha.collect { valor ->
            "\"${(valor ?: '').replace('"', '""')}\""
        }.join(';')
    }
}