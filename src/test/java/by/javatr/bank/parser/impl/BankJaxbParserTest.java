package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.storage.Banks;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static by.javatr.bank.entity.type.CreditType.CONSUMER;
import static by.javatr.bank.entity.type.CreditType.LEASING;
import static by.javatr.bank.entity.type.DepositType.ACCUMULATIVE;
import static by.javatr.bank.entity.type.DepositType.URGENT;

public class BankJaxbParserTest {

    private final static Banks BANKS = new Banks();
    private final static BankJaxbParser PARSER = new BankJaxbParser();
    private final static String FILE_NAME = ".\\src\\main\\resources\\banks.xml";

    @Before
    public void init(){
        BANKS.add(new DepositBank(1, "ccc", "belarus", URGENT, "aaa", 1));
        BANKS.add(new DepositBank(2, "ddd", "belarus", ACCUMULATIVE, "bbb", 2));
        BANKS.add(new CreditBank(3, "aaa", "belarus", CONSUMER, 10, 1000));
        BANKS.add(new CreditBank(4, "bbb", "belarus", LEASING, 5, 5000));
    }

    @Test
    public void parse() {
        //given
        //when
        Banks parseBanks = PARSER.parse(FILE_NAME);
        //then
        Assert.assertEquals(BANKS, parseBanks);
    }
}