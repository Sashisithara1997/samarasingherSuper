package lk.samarasingherSuper.asset.supplier.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.samarasingherSuper.asset.item.entity.Item;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrder;
import lk.samarasingherSuper.util.audit.AuditEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Supplier")
public class Supplier extends AuditEntity {

    @Size(min = 5, message = "Your Company name cannot be accepted")
    private String name;

    @Column(unique = true)
    private String code;

    @Size(min = 2, message = "Your BRN cannot be accepted")
    private String brn;

    @Size(max = 10, min = 9, message = "Mobile number length should be contained 10 and 9")
    private String contactOne;

    private String contactTwo;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL", length = 255)
    private String address;

    @OneToMany(mappedBy = "supplier")
    private List<PurchaseOrder> purchaseOrders;

    @OneToMany(mappedBy = "supplier")
    private List<SupplierItem> supplierItems;
}
