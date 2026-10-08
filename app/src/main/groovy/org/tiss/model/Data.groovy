package org.tiss.model

import java.time.YearMonth

class Data {

    static YearMonth converterData(String data) {
        Map<String, Integer> meses = [
                jan: 1, fev: 2, mar: 3, abr: 4,
                mai: 5, jun: 6, jul: 7, ago: 8,
                set: 9, out: 10, nov: 11, dez: 12
        ]

        String limpa = data
                .toLowerCase()
                .replaceAll(/[^\p{L}\d\/]/, '')

        def partes = limpa.split('/')

        return YearMonth.of(
                partes[1] as int,
                meses[partes[0].take(3)]
        )
    }

    static boolean comparaDatas(String data1, String data2) {
        return converterData(data1) >= converterData(data2)
    }
}
