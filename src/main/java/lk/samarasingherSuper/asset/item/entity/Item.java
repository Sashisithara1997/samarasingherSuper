package lk.samarasingherSuper.asset.item.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import lk.samarasingherSuper.asset.item.category.entity.Category;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrder;
import lk.samarasingherSuper.asset.supplier.entity.SupplierItem;
import lk.samarasingherSuper.util.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Item")
public class Item extends AuditEntity {

    @Size(min = 5, message = "Your name cannot be accepted")
    private String name;

    private Integer rop;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "item")
    private List<SupplierItem> supplierItems;
}
