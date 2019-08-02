package by.javatr.bank;

import by.javatr.bank.exception.FileIsNotValidException;
import by.javatr.bank.factory.BuilderFactory;
import by.javatr.bank.parser.BankParser;
import by.javatr.bank.factory.parser.ParserType;

public class Main {
    public static void main(String[] args) throws FileIsNotValidException {
        String path = ".\\src\\main\\resources\\banks.xml";
        String pathXsd = ".\\src\\main\\resources\\bank.xsd";

        BuilderFactory factory = new BuilderFactory();

        BankParser saxParser = factory.create(ParserType.SAX, pathXsd);
        System.out.println(saxParser.parse(path));


        BankParser domParser = factory.create(ParserType.DOM, pathXsd);
        System.out.println(domParser.parse(path));

        BankParser jaxbParser = factory.create(ParserType.JAXB, pathXsd);
        System.out.println(jaxbParser.parse(path));

//        XmlValidator validator = new XmlValidator();
//        validator.isValid(path, ".\\src\\main\\resources\\bank.xsd");
    }
}
