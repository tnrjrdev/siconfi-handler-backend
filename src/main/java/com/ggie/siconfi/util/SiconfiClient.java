package com.ggie.siconfi.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class SiconfiClient {

    private final RestTemplate restTemplate;

    public SiconfiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String mountUrl(String ano, String periodo, String documento, String anexo, String codEnte, String nomeEnte) {
        // verifica se os parâmetros estão corretos
        System.out.println("Parâmetros recebidos: ");
        System.out.println("ano: " + ano);
        System.out.println("periodo: " + periodo);
        System.out.println("documento: " + documento);
        System.out.println("anexo: " + anexo);
        System.out.println("codEnte: " + codEnte);
        System.out.println("nomeEnte: " + nomeEnte);

        // gerando a url
        String url = String.format("https://apidatalake.tesouro.gov.br/ords/siconfi/tt/rreo?an_exercicio=%s&nr_periodo=%s&co_tipo_demonstrativo=RREO&no_anexo=RREO-Anexo%%20%s&co_esfera=M&id_ente=%s",
                ano, periodo, anexo, codEnte);


        System.out.println("URL Gerada: " + url);

        return url;
    }


    public String fetchData(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            System.out.println("Erro 404: Recurso não encontrado na URL " + url);
            return "Not Found";
        } catch (Exception e) {
            System.out.println("Erro ao acessar a API: " + e.getMessage());
            return "Error";
        }
    }
}
