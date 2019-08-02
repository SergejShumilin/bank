package by.javatr.bank.handler;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.entity.type.BankEnum;
import by.javatr.bank.entity.type.CreditType;
import by.javatr.bank.entity.type.DepositType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class BankSaxHandler extends DefaultHandler {
    private List<Bank> banks;
    private Bank current = null;
    private BankEnum currentEnum = null;
    private EnumSet<BankEnum> enumSet;

    public BankSaxHandler() {
        banks = new ArrayList<>();
        enumSet = EnumSet.range(BankEnum.NAME, BankEnum.AMOUNT);
    }
    public List<Bank> getBanks() {
        return banks;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("deposit-bank".equals(localName)) {
            current = new DepositBank();
            int registrationNumber = Integer.parseInt(attrs.getValue(0));
            current.setRegistrationNumber(registrationNumber);
        } else if ("credit-bank".equals(localName)){
            current = new CreditBank();
            int registrationNumber = Integer.parseInt(attrs.getValue(0));
            current.setRegistrationNumber(registrationNumber);
        } else {
            BankEnum temp = BankEnum.valueOf(localName.toUpperCase());
            if (enumSet.contains(temp)) {
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
        String valueTag = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(valueTag);
                    break;
                case COUNTRY:
                    current.setCountry(valueTag);
                    break;
                case DEPOSIT:
                    DepositType depositType = DepositType.valueOf(valueTag);
                    ((DepositBank)current).setDeposit(depositType);
                    break;
                case DEPOSITOR:
                    ((DepositBank)current).setDepositor(valueTag);
                    break;
                case CONSTRAINS:
                    int constrains = Integer.parseInt(valueTag);
                    ((DepositBank)current).setConstrains(constrains);
                    break;
                case CREDIT:
                    CreditType creditType = CreditType.valueOf(valueTag);
                    ((CreditBank)current).setCredit(creditType);
                    break;
                case PROFITABILITY:
                    int profitability = Integer.parseInt(valueTag);
                    ((CreditBank)current).setProfitability(profitability);
                    break;
                case AMOUNT:
                    int amount = Integer.parseInt(valueTag);
                    ((CreditBank)current).setAmount(amount);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

}
