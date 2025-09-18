package com.ggie.siconfi.model;

import lombok.Data;
import java.util.List;

@Data
public class ExtractRequest {
    private List<String> anos;
    private List<String> periodos;
    private String documento;
    private String anexo;
    private List<String> codEntes;
    private List<String> nomeEntes;
}