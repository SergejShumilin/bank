package by.javatr.bank.handler;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.entity.type.BankEnum;
import by.javatr.bank.entity.type.CreditType;
import by.javatr.bank.entity.type.DepositType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class BankSaxHandler extends DefaultHandler {
    private Set<Bank> banks;

    private Bank current = null;
    private BankEnum currentEnum = null;
    private EnumSet<BankEnum> withText;
    public BankSaxHandler() {
        banks = new HashSet<>();
        withText = EnumSet.range(BankEnum.NAME, BankEnum.AMOUNT);
    }
    public Set<Bank> getBanks() {
        return banks;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("deposit-bank".equals(localName)) {
            current = new DepositBank();
        } else if ("credit-bank".equals(localName)){
            current = new CreditBank();
        } else {
            BankEnum temp = BankEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }
    public void endElement(String uri, String localName, String qName) {
        if ("deposit-bank".equals(localName)) {
            banks.add(current);
        }
        if ("credit-bank".equals(localName)){
            banks.add(current);
        }
    }
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case COUNTRY:
                    current.setCountry(s);
                    break;
                case DEPOSIT:
                    ((DepositBank)current).setDeposit(DepositType.valueOf(s));
                    break;
                case DEPOSITOR:
                    ((DepositBank)current).setDepositor(s);
                    break;
                case CONSTRAINS:
                    ((DepositBank)current).setConstrains(Integer.parseInt(s));
                    break;
                case CREDIT:
                    ((CreditBank)current).setCredit(CreditType.valueOf(s));
                    break;
                case PROFITABILITY:
                    ((CreditBank)current).setProfitability(Integer.parseInt(s));
                    break;
                case AMOUNT:
                    ((CreditBank)current).setAmount(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

}
