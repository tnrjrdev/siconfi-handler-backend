package com.ggie.siconfi.controller;

import com.ggie.siconfi.model.ExtractRequest;
import com.ggie.siconfi.service.SiconfiService;
import com.ggie.siconfi.model.SiconfiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/siconfi")
public class SiconfiController {

    private final SiconfiService siconfiService;

    @Autowired
    public SiconfiController(SiconfiService siconfiService) {
        this.siconfiService = siconfiService;
    }

    @PostMapping("/extrair")
    public List<SiconfiData> extrairDados(@RequestBody ExtractRequest request) {
        return siconfiService.extractData(request.getAnos(), request.getPeriodos(), request.getDocumento(), request.getAnexo(), request.getCodEntes(), request.getNomeEntes());
    }
}

