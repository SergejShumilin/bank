package by.javatr.bank.validator;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private String pathXsd;

    public XmlValidator(String pathXsd) {
        this.pathXsd = pathXsd;
    }

    public boolean isValid(String pathXml) {
        Schema schema = null;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            schema = factory.newSchema(new File(pathXsd));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            parser.parse(pathXml, new DefaultHandler());
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return true;
    }
}
