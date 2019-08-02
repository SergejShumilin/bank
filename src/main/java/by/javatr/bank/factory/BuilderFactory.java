package by.javatr.bank.factory;

import by.javatr.bank.parser.BankParser;
import by.javatr.bank.parser.impl.BankDomParser;
import by.javatr.bank.parser.impl.BankSaxParser;
import by.javatr.bank.parser.type.BuilderType;

public class BuilderFactory {
    public BankParser create(BuilderType type){
        BankParser builder = null;
        switch (type){
            case SAX:
                builder = new BankSaxParser();
                break;
            case DOM:
                builder = new BankDomParser();
        }
        return builder;
    }
}
