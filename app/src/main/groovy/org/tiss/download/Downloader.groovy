package org.tiss.download

class Downloader {
    void baixarArquivo(String urlDoArquivo, String nomeArquivoDestino) {

        File dir = new File("../../../../../output")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        File arquivo = new File(dir, nomeArquivoDestino)
        if (arquivo.exists()) {
            println("arquivo já existe, excluindo...")
            arquivo.delete()
        }

        println("A iniciar o download...")

        //URL é localizador e URI é Uniform Resource Identifier. URL é uma URI
        URI.create(urlDoArquivo).toURL().withInputStream { input ->
            //withOutputStream sobrescreve
            arquivo.withOutputStream { output ->
                output << input
            }
        }
        println("Download concluído. Arquivo salvo em: ${arquivo.path}")
    }

}
