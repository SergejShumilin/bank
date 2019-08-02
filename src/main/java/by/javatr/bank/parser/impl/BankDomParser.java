package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.entity.type.CreditType;
import by.javatr.bank.entity.type.DepositType;
import by.javatr.bank.exception.FileIsNotValidException;
import by.javatr.bank.parser.BankParser;
import by.javatr.bank.validator.XmlValidator;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankDomParser implements BankParser {
    private static final Logger LOGGER = Logger.getLogger(BankDomParser.class);
    private List<Bank> banks;
    private XmlValidator validator;
    private DocumentBuilder docBuilder;

    public BankDomParser(XmlValidator validator) {
        this.validator = validator;
        this.banks = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public List<Bank> getBanks() {
        return banks;
    }

    @Override
    public List<Bank> parse(String fileName) throws FileIsNotValidException {
        if (!validator.isValid(fileName)) {
            throw new FileIsNotValidException("File is not valid");
        }
                Document doc = null;
                try {
                    doc = docBuilder.parse(fileName);
                    Element root = doc.getDocumentElement();
                    NodeList depositBankList = root.getElementsByTagName("deposit-bank");
                    for (int i = 0; i < depositBankList.getLength(); i++) {
                        Element bankElement = (Element) depositBankList.item(i);
                        Bank bank = buildBank(bankElement);
                        banks.add(bank);
                    }
                    NodeList creditBankList = root.getElementsByTagName("credit-bank");
                    for (int i = 0; i < creditBankList.getLength(); i++) {
                        Element bankElement = (Element) creditBankList.item(i);
                        Bank bank = buildBank(bankElement);
                        banks.add(bank);
                    }
                } catch (IOException | SAXException e) {
                    LOGGER.info(e.getMessage(), e);
                }

                return banks;
        }

    private Bank buildBank(Element bankElement) {
        Bank bank = null;
        String tagName = bankElement.getTagName();
        if ("deposit-bank".equals(tagName)) {
            bank = buildDepositBank(bankElement);
        } else if ("credit-bank".equals(tagName)) {
           bank = buildCreditBank(bankElement);
        }
        return bank;
    }

    private Bank buildDepositBank(Element bankElement){
        Bank bank = new DepositBank();
        int registration = Integer.parseInt(bankElement.getAttribute("registration"));
        bank.setRegistrationNumber(registration);
        String name = getElementTextContent(bankElement, "name");
        bank.setName(name);
        String country = getElementTextContent(bankElement, "country");
        bank.setCountry(country);
        String depositType = getElementTextContent(bankElement, "deposit");
        DepositType deposit = DepositType.valueOf(depositType);
        ((DepositBank) bank).setDeposit(deposit);
        String depositorName = getElementTextContent(bankElement, "depositor");
        ((DepositBank) bank).setDepositor(depositorName);
        String valueConstrains = getElementTextContent(bankElement, "constrains");
        int constrains = Integer.parseInt(valueConstrains);
        ((DepositBank) bank).setConstrains(constrains);
        return bank;
    }

    private Bank buildCreditBank(Element bankElement){
        Bank bank = new CreditBank();
        int registration = Integer.parseInt(bankElement.getAttribute("registration"));
        bank.setRegistrationNumber(registration);
        String name = getElementTextContent(bankElement, "name");
        bank.setName(name);
        String country = getElementTextContent(bankElement, "country");
        bank.setCountry(country);
        String creditType = getElementTextContent(bankElement, "credit");
        CreditType credit = CreditType.valueOf(creditType);
        ((CreditBank) bank).setCredit(credit);
        String valueProfitability = getElementTextContent(bankElement, "profitability");
        int profitability = Integer.parseInt(valueProfitability);
        ((CreditBank) bank).setProfitability(profitability);
        String valueAmount = getElementTextContent(bankElement, "amount");
        int amount = Integer.parseInt(valueAmount);
        ((CreditBank) bank).setAmount(amount);
        return bank;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

