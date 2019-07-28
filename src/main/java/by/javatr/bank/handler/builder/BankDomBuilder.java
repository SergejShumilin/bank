package by.javatr.bank.handler.builder;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;
import by.javatr.bank.entity.type.CreditType;
import by.javatr.bank.entity.type.DepositType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BankDomBuilder {
    private Set<Bank> banks;
    private DocumentBuilder docBuilder;
    public BankDomBuilder() {
        this.banks = new HashSet<Bank>();
// создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }
    public Set<Bank> getBanks() {
        return banks;
    }
    public void buildSetBanks(String fileName) {
        Document doc = null;
        try {
// parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
// получение списка дочерних элементов <deposit-bank>
            NodeList banksList1 = root.getElementsByTagName("deposit-bank");
            for (int i = 0; i < banksList1.getLength(); i++) {
                Element bankElement = (Element) banksList1.item(i);
                Bank bank = buildBank(bankElement);
                banks.add(bank);
            }
            // получение списка дочерних элементов <credit-bank>
            NodeList banksList2 = root.getElementsByTagName("credit-bank");
            for (int i = 0; i < banksList2.getLength(); i++) {
                Element bankElement = (Element) banksList2.item(i);
                Bank bank = buildBank(bankElement);
                banks.add(bank);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }
    private Bank buildBank(Element bankElement) {
        Bank bank = null;
//        Bank bank = new Bank();
        String tagName = bankElement.getTagName();
        if ("deposit-bank".equals(tagName)){
            bank = new DepositBank();
            bank.setName(getElementTextContent(bankElement, "name"));
            bank.setCountry(getElementTextContent(bankElement, "country"));
            DepositType deposit = DepositType.valueOf(getElementTextContent(bankElement, "deposit"));
            ((DepositBank)bank).setDeposit(deposit);
            ((DepositBank)bank).setDepositor(getElementTextContent(bankElement, "depositor"));
            Integer constrains = Integer.parseInt(getElementTextContent(bankElement, "constrains"));
            ((DepositBank)bank).setConstrains(constrains);
        } else if ("credit-bank".equals(tagName)){
            bank = new CreditBank();
            bank.setName(getElementTextContent(bankElement, "name"));
            bank.setCountry(getElementTextContent(bankElement, "country"));
            CreditType credit = CreditType.valueOf(getElementTextContent(bankElement, "credit"));
            ((CreditBank)bank).setCredit(credit);
            Integer profitability = Integer.parseInt(getElementTextContent(
                    bankElement,"profitability"));
            ((CreditBank)bank).setProfitability(profitability);
            Integer amount = Integer.parseInt(getElementTextContent(
                    bankElement,"amount"));
            ((CreditBank)bank).setAmount(amount);
        }
        return bank;
    }
    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

