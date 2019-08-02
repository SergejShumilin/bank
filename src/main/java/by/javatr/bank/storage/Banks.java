package by.javatr.bank.storage;

import by.javatr.bank.entity.Bank;
import by.javatr.bank.entity.CreditBank;
import by.javatr.bank.entity.DepositBank;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Banks")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Banks")
public class Banks {

    @XmlElements
            ({
                    @XmlElement(name = "deposit-bank", type = DepositBank.class, required = true),
                    @XmlElement(name = "credit-bank", type = CreditBank.class, required = true),
            })
    private List<Bank> list = new ArrayList<>();

    public Banks() {
        super();
    }

    public void setList(ArrayList<Bank> list) {
        this.list = list;
    }

    public List<Bank> getListOfBanks()
    {
        return list;
    }

    public boolean add(Bank st) {
        return list.add(st);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Banks banks = (Banks) o;
        return list != null ? list.equals(banks.list) : banks.list == null;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Banks [list=" + list + "]";
    }
}
