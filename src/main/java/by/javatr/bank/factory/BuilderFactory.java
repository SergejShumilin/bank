package by.javatr.bank.factory;

import by.javatr.bank.parser.BankParser;
import by.javatr.bank.parser.impl.BankDomParser;
import by.javatr.bank.parser.impl.BankJaxbParser;
import by.javatr.bank.parser.impl.BankSaxParser;
import by.javatr.bank.factory.parser.ParserType;
import by.javatr.bank.validator.XmlValidator;

public class BuilderFactory {

    public BankParser create(ParserType type, String pathXsd){
        BankParser parser = null;
        switch (type){
            case SAX:
                parser = new BankSaxParser(new XmlValidator(pathXsd));
                break;
            case DOM:
                parser = new BankDomParser(new XmlValidator(pathXsd));
                break;
            case JAXB:
                parser = new BankJaxbParser(new XmlValidator(pathXsd));
                break;
        }
        return parser;
    }
}
