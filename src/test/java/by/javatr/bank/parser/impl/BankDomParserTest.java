package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.parser.impl.BankDomParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static by.javatr.bank.entity.type.CreditType.CONSUMER;
import static by.javatr.bank.entity.type.CreditType.LEASING;
import static by.javatr.bank.entity.type.DepositType.ACCUMULATIVE;
import static by.javatr.bank.entity.type.DepositType.URGENT;

public class BankDomParserTest {

    private List<Bank> EXPECTED_LIST = Arrays.asList(new DepositBank(1, "ccc", "belarus", URGENT, "aaa", 1),
            new DepositBank(2, "ddd", "belarus", ACCUMULATIVE, "bbb", 2),
            new CreditBank(3, "aaa", "belarus", CONSUMER, 10, 1000),
            new CreditBank(4, "bbb", "belarus", LEASING, 5, 5000));
    private final static BankDomParser DOM_BUILDER = new BankDomParser();
    private final static String FILE_NAME = ".\\src\\main\\resources\\banks.xml";


    @Test
    public void testParseShouldBuildListBanks() {
        //given
        //when
        DOM_BUILDER.parse(FILE_NAME);
        List<Bank> ACTUAL_LIST = DOM_BUILDER.getBanks();
        //then
        Assert.assertEquals(EXPECTED_LIST, ACTUAL_LIST);
    }

}