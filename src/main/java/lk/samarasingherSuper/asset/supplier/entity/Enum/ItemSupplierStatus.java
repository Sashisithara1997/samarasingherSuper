package lk.samarasingherSuper.asset.supplier.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ItemSupplierStatus {

    CURRENTLTLY_BUYING("Currently_Buying"),
    STOPPED("Stopped");

    private final String itemSupplierStatus;

}
