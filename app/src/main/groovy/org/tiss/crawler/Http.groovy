package org.tiss.crawler

import static groovyx.net.http.HttpBuilder.configure
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class Http {
    Document buscarPagina(String url) {
        configure {
            request.uri = url
        }.get()
    }
}
