package com.visma.warehouse.controllers;

import com.visma.warehouse.services.FileService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Profile("database")
@RequestMapping("/api/report")
public class ReportController {

    private FileService fileService;

    @GetMapping("/csv/{date}")
    public ResponseEntity<Resource> getSalesReportInCsv(@PathVariable @DateTimeFormat(pattern = "yyyy.MM.dd'T'HH") LocalDateTime date) throws FileNotFoundException {

        File file = fileService.readFile(date);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(resource);
    }
}
