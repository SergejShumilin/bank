package by.javatr.bank.entity;

import by.javatr.bank.entity.type.DepositType;

import java.util.StringJoiner;

public class DepositBank extends Bank {
    private DepositType deposit;
    private String depositor;
    private int constrains;

    public DepositBank() {

    }

    public DepositBank(String name, String country, DepositType deposit, String depositor, int constrains) {
        super(name, country);
        this.deposit = deposit;
        this.depositor = depositor;
        this.constrains = constrains;
    }

    public DepositType getDeposit() {
        return deposit;
    }

    public void setDeposit(DepositType deposit) {
        this.deposit = deposit;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public int getConstrains() {
        return constrains;
    }

    public void setConstrains(int constrains) {
        this.constrains = constrains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        DepositBank that = (DepositBank) o;
        if (constrains != that.constrains){
            return false;
        }
        if (deposit != that.deposit){
            return false;
        }
        return depositor != null ? depositor.equals(that.depositor) : that.depositor == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (deposit != null ? deposit.hashCode() : 0);
        result = 31 * result + (depositor != null ? depositor.hashCode() : 0);
        result = 31 * result + constrains;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DepositBank{");
        sb.append("name='").append(getName()).append('\'');
        sb.append(", country='").append(getCountry()).append('\'');
        sb.append(", deposit='").append(deposit).append('\'');
        sb.append(", depositor='").append(depositor).append('\'');
        sb.append(", constrains=").append(constrains);
        sb.append('}');
        return sb.toString();
    }
}
