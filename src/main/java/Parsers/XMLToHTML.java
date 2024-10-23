package Parsers;

import javax.xml.XMLConstants;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.io.*;

public class XMLToHTML {
    public static void main(String[] args) {
        try {
            String xmlFile = "C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/Full.xml";
            String xsltFile = "C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/test20.xslt";
            String outputHtmlFile = "C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/output.html";

            validateXMLSchema("C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/Full.xsd", xmlFile);

            transformXMLToHTML(xmlFile, xsltFile, outputHtmlFile);
            System.out.println("Трансформация завершена. Результат в файле: " + outputHtmlFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validateXMLSchema(String xsdPath, String xmlPath) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdPath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlPath)));
        System.out.println("XML файл валідний.");
    }


    public static void transformXMLToHTML(String xmlFile, String xsltFile, String outputFile) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new File(xsltFile)));
        transformer.transform(new StreamSource(new File(xmlFile)), new StreamResult(new File(outputFile)));
    }
}