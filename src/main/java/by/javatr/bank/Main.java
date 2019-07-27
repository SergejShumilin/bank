package by.javatr.bank;

import by.javatr.bank.handler.BankSaxBuilder;

public class Main {
    public static void main(String[] args) {
        String path = ".\\src\\main\\resources\\banks.xml";
        BankSaxBuilder saxBuilder = new BankSaxBuilder();
        saxBuilder.buildSetBanks(path);
        System.out.println(saxBuilder.getBanks());
    }
}
