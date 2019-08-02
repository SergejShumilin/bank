package by.javatr.bank.parser.impl;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.entity.type.CreditType;
import by.javatr.bank.entity.type.DepositType;
import by.javatr.bank.parser.BankParser;
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

    private DocumentBuilder docBuilder;

    public BankDomParser() {
        this.banks = new ArrayList<>();
// создание DOM-анализатора
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
    public void parse(String fileName) {
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
        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
        } catch (SAXException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    private Bank buildBank(Element bankElement) {
        Bank bank = null;
        String tagName = bankElement.getTagName();
        if ("deposit-bank".equals(tagName)) {
            bank = new DepositBank();
            bank.setRegistrationNumber(Integer.parseInt(bankElement.getAttribute("registration")));
            bank.setName(getElementTextContent(bankElement, "name"));
            bank.setCountry(getElementTextContent(bankElement, "country"));
            DepositType deposit = DepositType.valueOf(getElementTextContent(bankElement, "deposit"));
            ((DepositBank) bank).setDeposit(deposit);
            ((DepositBank) bank).setDepositor(getElementTextContent(bankElement, "depositor"));
            int constrains = Integer.parseInt(getElementTextContent(bankElement, "constrains"));
            ((DepositBank) bank).setConstrains(constrains);
        } else if ("credit-bank".equals(tagName)) {
            bank = new CreditBank();
            bank.setRegistrationNumber(Integer.parseInt(bankElement.getAttribute("registration")));
            bank.setName(getElementTextContent(bankElement, "name"));
            bank.setCountry(getElementTextContent(bankElement, "country"));
            CreditType credit = CreditType.valueOf(getElementTextContent(bankElement, "credit"));
            ((CreditBank) bank).setCredit(credit);
            int profitability = Integer.parseInt(getElementTextContent(
                    bankElement, "profitability"));
            ((CreditBank) bank).setProfitability(profitability);
            int amount = Integer.parseInt(getElementTextContent(
                    bankElement, "amount"));
            ((CreditBank) bank).setAmount(amount);
        }
        return bank;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

