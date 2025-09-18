package com.ggie.siconfi.service;

import com.ggie.siconfi.util.SiconfiClient;
import com.ggie.siconfi.model.SiconfiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiconfiService {

    private final SiconfiClient siconfiClient;

    @Autowired
    public SiconfiService(SiconfiClient siconfiClient) {
        this.siconfiClient = siconfiClient;
    }

    public List<SiconfiData> extractData(List<String> anos, List<String> periodos, String documento, String anexo, List<String> codEntes, List<String> nomeEntes) {
        List<SiconfiData> result = new ArrayList<>();
        for (String ano : anos) {
            for (String periodo : periodos) {
                for (int i = 0; i < codEntes.size(); i++) {
                    String codEnte = codEntes.get(i);
                    String nomeEnte = nomeEntes.get(i);
                    String url = siconfiClient.mountUrl(ano, periodo, documento, anexo, codEnte, nomeEnte);
                    String data = siconfiClient.fetchData(url);
                    SiconfiData siconfiData = new SiconfiData();
                    // Aqui mapeia os dados de 'data' para o modelo SiconfiData
                    result.add(siconfiData);
                }
            }
        }
        return result;
    }
}

