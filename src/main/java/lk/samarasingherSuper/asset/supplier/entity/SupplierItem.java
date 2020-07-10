package lk.samarasingherSuper.asset.supplier.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.samarasingherSuper.asset.item.entity.Item;
import lk.samarasingherSuper.asset.supplier.entity.Enum.ItemSupplierStatus;
import lk.samarasingherSuper.util.audit.AuditEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SupplierItem")
public class SupplierItem extends AuditEntity {

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ItemSupplierStatus itemSupplierStatus;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Supplier supplier;


}
