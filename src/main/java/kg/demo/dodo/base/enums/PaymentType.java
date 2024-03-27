package kg.demo.dodo.base.enums;

public enum PaymentType {

    CASH("Наличные"),
    CASHLESS("Базналичные"),
    CAR("Оплата картой курьеру");

    private final String def;


    PaymentType(String def) {
        this.def = def;
    }

    public String getDef() {
        return def;
    }
}
