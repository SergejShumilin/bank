package by.javatr.bank.entity;

import by.javatr.bank.entity.type.DepositType;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "deposit-bank")
public class DepositBank extends Bank {

    private DepositType depositType;

    private String depositorName;

    private int constrains;

    public DepositBank() {

    }

    public DepositBank(int registrationNumber, String name, String country, DepositType depositType, String depositorName, int constrains) {
        super(registrationNumber, name, country);
        this.depositType = depositType;
        this.depositorName = depositorName;
        this.constrains = constrains;
    }
    @XmlElement
    public DepositType getDeposit() {
        return depositType;
    }

    public void setDeposit(DepositType depositType) {
        this.depositType = depositType;
    }
    @XmlElement
    public String getDepositor() {
        return depositorName;
    }

    public void setDepositor(String depositorName) {
        this.depositorName = depositorName;
    }
    @XmlElement
    public int getConstrains() {
        return constrains;
    }

    public void setConstrains(int constrains) {
        this.constrains = constrains;
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

        DepositBank that = (DepositBank) o;
        if (constrains != that.constrains) {
            return false;
        }
        if (depositType != that.depositType) {
            return false;
        }
        return depositorName != null ? depositorName.equals(that.depositorName) : that.depositorName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (depositType != null ? depositType.hashCode() : 0);
        result = 31 * result + (depositorName != null ? depositorName.hashCode() : 0);
        result = 31 * result + constrains;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DepositBank{");
        sb.append("registration='").append(getRegistrationNumber()).append('\'');
        sb.append(",  name='").append(getName()).append('\'');
        sb.append(", country='").append(getCountry()).append('\'');
        sb.append(", deposit='").append(depositType).append('\'');
        sb.append(", depositor='").append(depositorName).append('\'');
        sb.append(", constrains=").append(constrains);
        sb.append('}');
        return sb.toString();
    }
}
