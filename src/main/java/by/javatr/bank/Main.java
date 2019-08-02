package by.javatr.bank;

import by.javatr.bank.factory.BuilderFactory;
import by.javatr.bank.parser.BankParser;
import by.javatr.bank.parser.impl.BankJaxbParser;
import by.javatr.bank.parser.type.BuilderType;
import by.javatr.bank.storage.Banks;

import javax.xml.bind.JAXBException;

public class Main {
    public static void main(String[] args) throws JAXBException {
        String path = ".\\src\\main\\resources\\banks.xml";

        BuilderFactory factory = new BuilderFactory();

//        BankParser saxBuilder = factory.create(BuilderType.SAX);
//        saxBuilder.parse(path);
//        System.out.println(saxBuilder.getBanks());
//
//        BankParser domBuilder = factory.create(BuilderType.DOM);
//        domBuilder.parse(path);
//        System.out.println(domBuilder.getBanks());

        BankJaxbParser parser = new BankJaxbParser();
//        Banks parse = parser.parse(path);
//        System.out.println(parse);

        parser.unmarsh(path);
//        try {
//            JAXBContext jc = JAXBContext.newInstance(Banks.class);
//            Unmarshaller u = jc.createUnmarshaller();
//            FileReader reader = new FileReader(path);
//            Banks banks = (Banks) u.unmarshal(reader);
//            System.out.println(banks);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
