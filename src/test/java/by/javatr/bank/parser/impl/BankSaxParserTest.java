package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.parser.impl.BankSaxParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static by.javatr.bank.entity.type.CreditType.CONSUMER;
import static by.javatr.bank.entity.type.CreditType.LEASING;
import static by.javatr.bank.entity.type.DepositType.ACCUMULATIVE;
import static by.javatr.bank.entity.type.DepositType.URGENT;

public class BankSaxParserTest {

    private List<Bank> EXPECTED_LIST = Arrays.asList(new DepositBank(1, "ccc", "belarus", URGENT, "aaa", 1),
            new DepositBank(2, "ddd", "belarus", ACCUMULATIVE, "bbb", 2),
            new CreditBank(3, "aaa", "belarus", CONSUMER, 10, 1000),
            new CreditBank(4, "bbb", "belarus", LEASING, 5, 5000));;
    private final static BankSaxParser SAX_BUILDER = new BankSaxParser();
    private final static String FILE_NAME = ".\\src\\main\\resources\\banks.xml";

    @Test
    public void testParseShouldBuildListBanks() {
        //given
        //when
        SAX_BUILDER.parse(FILE_NAME);
        List<Bank> ACTUAL_LIST = SAX_BUILDER.getBanks();
        //then
        Assert.assertEquals(EXPECTED_LIST, ACTUAL_LIST);
    }
}