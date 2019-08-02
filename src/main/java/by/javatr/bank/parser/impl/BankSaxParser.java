package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.exception.FileIsNotValidException;
import by.javatr.bank.handler.BankSaxHandler;
import by.javatr.bank.parser.BankParser;
import by.javatr.bank.validator.XmlValidator;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class BankSaxParser implements BankParser {
    private static final Logger LOGGER = Logger.getLogger(BankSaxParser.class);

    private List<Bank> banks;
    private BankSaxHandler handler;
    private XMLReader reader;
    private XmlValidator validator;


    public BankSaxParser(XmlValidator validator) {
        this.validator = validator;
        handler = new BankSaxHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public List<Bank> getBanks() {
        return banks;
    }

    @Override
    public List<Bank> parse(String fileName) throws FileIsNotValidException {
        if (!validator.isValid(fileName)) {
            throw new FileIsNotValidException("File is not valid");
        }
            try {
                reader.parse(fileName);
            } catch (SAXException | IOException e) {
                LOGGER.info(e.getMessage(), e);
            }
            banks = handler.getBanks();
        return banks;
    }
}
