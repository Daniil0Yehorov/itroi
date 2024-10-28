package com.itroi.itroi.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import java.io.IOException;

@RestController
public class XMLController {

//will delete later
    @GetMapping("/xml/Full.xml")
    public ResponseEntity<ClassPathResource> getXml() throws IOException {
        ClassPathResource xmlFile = new ClassPathResource("static/xml/Full.xml");
        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(xmlFile);
    }
}