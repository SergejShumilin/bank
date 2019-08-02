package by.javatr.bank.entity;

import javax.xml.bind.annotation.*;

@XmlSeeAlso({DepositBank.class, CreditBank.class})
public class Bank {

    private int registrationNumber;

    private String name;

    private String country;

    public Bank() {
    }

    public Bank(int registrationNumber, String name, String country) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.country = country;
    }
    @XmlAttribute(name = "registration")
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Bank bank = (Bank) o;
        if (registrationNumber != bank.registrationNumber) {
            return false;
        }
        if (name != null ? !name.equals(bank.name) : bank.name != null) {
            return false;
        }
        return country != null ? country.equals(bank.country) : bank.country == null;
    }

    @Override
    public int hashCode() {
        int result = registrationNumber;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bank{");
        sb.append("registrationNumber='").append(registrationNumber).append('\'');
        sb.append(",  name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
