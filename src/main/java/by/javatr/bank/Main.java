package by.javatr.bank;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.handler.builder.BankDomBuilder;
import by.javatr.bank.handler.builder.BankSaxBuilder;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String path = ".\\src\\main\\resources\\banks.xml";
//        BankSaxBuilder saxBuilder = new BankSaxBuilder();
//        saxBuilder.buildSetBanks(path);
//        Set<Bank> banks = saxBuilder.getBanks();
//        for (Bank b :banks) {
//            System.out.println(b);
//        }

        BankDomBuilder domBuilder = new BankDomBuilder();
        domBuilder.buildSetBanks(path);
        System.out.println(domBuilder.getBanks());
    }
}
