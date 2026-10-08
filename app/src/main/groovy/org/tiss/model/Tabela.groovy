package org.tiss.model

import groovy.transform.Canonical

@Canonical
class Tabela {
    List<String> header = []
    List<List<String>> body = []
}