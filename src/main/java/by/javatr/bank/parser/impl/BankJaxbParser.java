package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.exception.FileIsNotValidException;
import by.javatr.bank.parser.BankParser;
import by.javatr.bank.storage.Banks;
import by.javatr.bank.validator.XmlValidator;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class BankJaxbParser implements BankParser {
    private static final Logger LOGGER = Logger.getLogger(BankJaxbParser.class);
    private List<Bank> listOfBanks;
    private XmlValidator validator;

    public BankJaxbParser(XmlValidator validator) {
        this.validator = validator;
    }

    @Override
    public List<Bank> parse(String pathXml) throws FileIsNotValidException {
        if (!validator.isValid(pathXml)) {
            throw new FileIsNotValidException("File is not valid");
        }
            try {
                JAXBContext context = JAXBContext.newInstance(Banks.class);
                Unmarshaller unmarhsaller = context.createUnmarshaller();
                Banks banks = (Banks) unmarhsaller.unmarshal(new File(pathXml));
                listOfBanks = banks.getListOfBanks();
            } catch (JAXBException e) {
                LOGGER.info(e.getMessage(), e);
            }
        return listOfBanks;
    }
}
