package Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import java.io.IOException;

@RestController
public class XMLController {


    @GetMapping("/xml/Full.xml")
    public ResponseEntity<ClassPathResource> getanotherXml() throws IOException {
        ClassPathResource xmlFile = new ClassPathResource("src/main/resources/static/xml/Full.xml");
        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(xmlFile);
    }
}