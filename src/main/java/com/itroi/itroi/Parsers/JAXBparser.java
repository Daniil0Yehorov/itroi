package com.itroi.itroi.Parsers;

import com.itroi.itroi.Model.StoreData;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;


public class JAXBparser {
    private final String xsdPath;

    public JAXBparser(String xsdPath) {
        this.xsdPath = xsdPath;
    }

    public StoreData demarshal(String xmlFilePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StoreData.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Валідація за XSD
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdPath));
            unmarshaller.setSchema(schema);

            return (StoreData) unmarshaller.unmarshal(new File(xmlFilePath));
            //Може викинути САКСексепшн коли є помилка с читанням хмл файлу
        } catch (JAXBException | SAXException e) {
            System.err.println("Помилка при демаршалізації XML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void marshal(StoreData storeData, String xmlFilePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StoreData.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(storeData, new File(xmlFilePath));
        } catch (JAXBException e) {
            System.err.println("Помилка при маршалізації XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // with normal data
        String xmlFilePath = "C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/Full.xml";
        String xsdPath = "C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/Full.xsd";
        System.out.println("--== JAXB Parser ==--");
        String outputXmlFilePath = "outputJAXB.xml";

        JAXBparser jaxBparser = new JAXBparser(xsdPath);
        StoreData storeData = jaxBparser.demarshal(xmlFilePath);

        jaxBparser.marshal(storeData, outputXmlFilePath);

        // with invalid Data
        String xmlFilePath1 = "C:/Users/Даниил/Рабочий стол/itroi/invalid_storeData.xml";
        String outputXmlFilePath1 = "outputJAXB1.xml";

        StoreData storeData1 = jaxBparser.demarshal(xmlFilePath1);

        jaxBparser.marshal(storeData1, outputXmlFilePath1);
    }
}
