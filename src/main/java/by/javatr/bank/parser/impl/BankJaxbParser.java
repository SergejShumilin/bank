package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.storage.Banks;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class BankJaxbParser {
    private static final Logger LOGGER = Logger.getLogger(BankJaxbParser.class);

    public Banks parse(String path) {
        Banks banks = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(Banks.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(path);
            banks = (Banks) u.unmarshal(reader);
        } catch (JAXBException | FileNotFoundException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return banks;
    }

    public void unmarsh(String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Banks.class);
        Unmarshaller unmarhsaller = context.createUnmarshaller();
        Banks listOfBank = (Banks) unmarhsaller.unmarshal(new File(path));
        List<Bank> listOfBanks = listOfBank.getListOfBanks();
        System.out.println(listOfBanks);
    }

}
