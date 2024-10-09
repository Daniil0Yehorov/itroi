package Controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import java.io.IOException;

@RestController
public class XMLController {

    @GetMapping("/xml/korm.xml")
    public ResponseEntity<ClassPathResource> getXml() throws IOException {
        ClassPathResource xmlFile = new ClassPathResource("src/main/resources/static/xml/korm.xml");
        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(xmlFile);
    }
    @GetMapping("/xml/test.xslt")
    public ResponseEntity<ClassPathResource> getXslt() throws IOException {
        ClassPathResource xsltFile = new ClassPathResource("src/main/resources/static/xml/test.xslt");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml");

        return new ResponseEntity<>(xsltFile, headers, HttpStatus.OK);
    }
}