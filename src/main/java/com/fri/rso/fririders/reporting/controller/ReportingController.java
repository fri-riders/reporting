package com.fri.rso.fririders.reporting.controller;

import com.fri.rso.fririders.reporting.clients.AccommodationsClient;
import com.fri.rso.fririders.reporting.data.Accommodation;
import com.fri.rso.fririders.reporting.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "v1/reporting")
public class ReportingController {

    private final AccommodationsClient accommodationsClient;

    private final PdfGeneratorService pdfGeneratorService;

    @Autowired
    public ReportingController(AccommodationsClient accommodationsClient, PdfGeneratorService pdfGeneratorService) {
        this.accommodationsClient = accommodationsClient;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @RequestMapping(value = "/accommodations", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() throws IOException {

        List<Accommodation> accommodations = accommodationsClient.getAll();

        ByteArrayInputStream bis = pdfGeneratorService.generateReport("Accommodations list", accommodations);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=accommodations.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
