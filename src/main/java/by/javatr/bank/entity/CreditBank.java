package by.javatr.bank.entity;

import by.javatr.bank.entity.type.CreditType;

public class CreditBank extends Bank {
    private CreditType credit;
    private int profitability;
    private int amount;

    public CreditBank() {
    }

    public CreditBank(String name, String country, CreditType credit, int profitability, int amount) {
        super(name, country);
        this.credit = credit;
        this.profitability = profitability;
        this.amount = amount;
    }

    public CreditType getCredit() {
        return credit;
    }

    public void setCredit(CreditType credit) {
        this.credit = credit;
    }

    public int getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CreditBank that = (CreditBank) o;

        if (profitability != that.profitability) return false;
        if (amount != that.amount) return false;
        return credit != null ? credit.equals(that.credit) : that.credit == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + profitability;
        result = 31 * result + amount;
        return result;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditBank{");
        sb.append("name='").append(getName()).append('\'');
        sb.append(", country='").append(getCountry()).append('\'');
        sb.append(", credit='").append(credit).append('\'');
        sb.append(", profitability=").append(profitability);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
