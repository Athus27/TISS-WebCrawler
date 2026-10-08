# TISS - WebCrawler

> Aplicação em groovy utilizada para acessar o espaço do **prestador de serviços da saúde** pelo portal **Gov** 

## Autor

- Athus Silva Souza
- TISS - WebCrawler

## Objetivo
> fazer uma requisição **http** pelo http builder e utilizando o JSoup modelar um crawler com o objetivo de extrair alguns dados.


## Funcionalidades
- Acessar sites
- Baixar arquivos
- Ler dados de tabelas 
- Criar tabelas.csv

## Tecnologias utilizadas

- Groovy
- Httpbuilder
- Jsoup
 

## Estrutura do projeto

```text
.
├── app
│   ├── build.gradle
│   ├── output
│   │   ├── ComponenteComunicação.zip
│   │   └── historico_tiss.csv
│   └── src
│       ├── main
│       │   ├── groovy
│       │   │   └── org
│       │   │       └── tiss
│       │   │           ├── App.groovy
│       │   │           ├── crawler
│       │   │           ├── download
│       │   │           ├── model
│       │   │           ├── parser
│       │   │           └── storage
│       │   └── resources
│       └── test
│           ├── groovy
│           │   └── org
│           └── resources
├── gradle.properties
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle

```



## Pré-requisitos

- Java 21
- Conexão com a Internet

## Como executar
Na raiz do projeto, execute:

```bash
git clone https://github.com/Athus27/TISS-WebCrawler.git

# dps entra na pasta do projeto:
cd TISS-WebCrawler

# execute 
./gradlew app:run

# Caso apareça erro de permissão:
chmod +x gradlew
./gradlew app:run
```

## Arquivos gerados

Os arquivos gerados são armazenados em:

```text
app/output/
├── ComponenteComunicação.zip
└── historico_tiss.csv

```



## Arquitetura / Fluxo

- `App.groovy`: inicia e coordena a execução das tarefas.
- `crawler/Http.groovy`: realiza as requisições HTTP utilizando HTTP Builder.
- `crawler/Navegador.groovy`: analisa as páginas e procura links com seletores CSS.
- `crawler/TissLinks.groovy`: percorre as páginas da ANS e localiza os recursos do TISS.
- `download/Downloader.groovy`: baixa o componente de comunicação.
- `parser/TabelaParser.groovy`: extrai o cabeçalho e as linhas das tabelas HTML.
- `model/Tabela.groovy`: representa uma tabela por meio de `header` e `body`.
- `model/Data.groovy`: normaliza e compara as competências encontradas.
- `storage/CsvWriter.groovy`: converte a tabela extraída em um arquivo CSV.

### Fluxo

```text
App
 ├── TissLinks
 │    └── Navegador
 │         └── HttpBuilder + Jsoup
 │
 ├── Downloader
 │    └── ComponenteComunicação.zip
 │
 └── TabelaParser
      ├── Tabela
      └── CsvWriter
           └── historico_tiss.csv
```
1. A aplicação acessa o portal da ANS.
2. O crawler encontra as páginas do prestador e do padrão TISS.
3. O link da versão mais recente do componente de comunicação é localizado.
4. O arquivo ZIP é baixado.
5. A página do histórico de versões é acessada.
6. O Jsoup extrai o cabeçalho e as linhas da tabela.
7. Os dados são representados pelo objeto Tabela.
8. A tabela é gravada no formato CSV.


