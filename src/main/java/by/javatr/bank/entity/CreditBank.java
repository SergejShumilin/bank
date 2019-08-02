package by.javatr.bank.entity;

import by.javatr.bank.entity.type.CreditType;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "deposit-bank")
public class CreditBank extends Bank {

    private CreditType creditType;

    private int profitability;

    private int amount;

    public CreditBank() {
    }

    public CreditBank(int registrationNumber, String name, String country, CreditType creditType, int profitability, int amount) {
        super(registrationNumber, name, country);
        this.creditType = creditType;
        this.profitability = profitability;
        this.amount = amount;
    }
    @XmlElement
    public CreditType getCredit() {
        return creditType;
    }

    public void setCredit(CreditType creditType) {
        this.creditType = creditType;
    }
    @XmlElement
    public int getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }
    @XmlElement
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CreditBank that = (CreditBank) o;
        if (profitability != that.profitability) {
            return false;
        }
        if (amount != that.amount) {
            return false;
        }
        return creditType != null ? creditType.equals(that.creditType) : that.creditType == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (creditType != null ? creditType.hashCode() : 0);
        result = 31 * result + profitability;
        result = 31 * result + amount;
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditBank{");
        sb.append("registration='").append(getRegistrationNumber()).append('\'');
        sb.append(",  name='").append(getName()).append('\'');
        sb.append(", country='").append(getCountry()).append('\'');
        sb.append(", credit='").append(creditType).append('\'');
        sb.append(", profitability=").append(profitability);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
