package by.javatr.bank.handler;

import by.javatr.bank.entity.Bank;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class BankSaxBuilder {
    private Set<Bank> banks;
    private BankHandler sh;
    private XMLReader reader;

    public BankSaxBuilder() {
// создание SAX-анализатора
        sh = new BankHandler();
        try {
// создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }
    public Set<Bank> getBanks() {
        return banks;
    }

    public void buildSetBanks(String fileName) {
        try {
// разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        banks = sh.getBanks();
    }
}
