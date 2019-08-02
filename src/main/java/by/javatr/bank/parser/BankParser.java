package by.javatr.bank.parser;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.exception.FileIsNotValidException;

import java.util.List;

public interface BankParser {
    public List<Bank> parse(String path) throws FileIsNotValidException;

}
