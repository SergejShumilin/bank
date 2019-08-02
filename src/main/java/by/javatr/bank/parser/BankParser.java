package by.javatr.bank.parser;

import by.javatr.bank.entity.Bank;

import java.util.List;

public interface BankParser {
    public void parse(String path);
public List<Bank> getBanks();
}
