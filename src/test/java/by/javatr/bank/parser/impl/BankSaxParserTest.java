package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.entity.type.CreditType;
import by.javatr.bank.entity.type.DepositType;
import by.javatr.bank.exception.FileIsNotValidException;
import by.javatr.bank.validator.XmlValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class BankSaxParserTest {
    private List<Bank> EXPECTED_LIST;
    private final XmlValidator validator = Mockito.mock(XmlValidator.class);
    private final BankSaxParser SAX_BUILDER = new BankSaxParser(validator);
    private final static String FILE_NAME = ".\\src\\main\\resources\\banks.xml";


    @Before
    public void init(){
        EXPECTED_LIST = Arrays.asList(new DepositBank(1, "ccc", "belarus", DepositType.URGENT, "aaa", 1),
                new DepositBank(2, "ddd", "belarus", DepositType.ACCUMULATIVE, "bbb", 2),
                new CreditBank(3, "aaa", "belarus", CreditType.CONSUMER, 10, 1000),
                new CreditBank(4, "bbb", "belarus", CreditType.LEASING, 5, 5000));
        Mockito.when(validator.isValid(FILE_NAME)).thenReturn(true);
    }
    @Test
    public void testParseShouldBuildListBanks() throws FileIsNotValidException {
        //given
        //when
        List<Bank> actualList = SAX_BUILDER.parse(FILE_NAME);
        //then
        Assert.assertEquals(EXPECTED_LIST, actualList);
    }
}