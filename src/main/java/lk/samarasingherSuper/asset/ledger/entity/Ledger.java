package lk.samarasingherSuper.asset.ledger.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.samarasingherSuper.asset.item.entity.ItemBatch;
import lk.samarasingherSuper.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Ledger")
public class Ledger extends AuditEntity {

    @ManyToOne
    private ItemBatch itemBatch;

    @Column(precision = 10, scale = 2)
    private BigDecimal sellPrice;

    private String quantity;
}
