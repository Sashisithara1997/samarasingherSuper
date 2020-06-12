package lk.samarasingherSuper.asset.payment.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {

    CASH("Cash"),
    BANK("Bank");


    private final String paymentMethod;
}
