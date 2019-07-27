package by.javatr.bank.entity.type;

public enum BankEnum {
    BANKS("banks"),
    BANK("bank"),
    NAME("name"),
    COUNTRY("country"),
    DEPOSIT("deposit"),
    DEPOSITOR("depositor"),
    CONSTRAINS("constrains");

    private String value;

    private BankEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
