package lk.samarasingherSuper.asset.purchaseOrder.dao;


import lk.samarasingherSuper.asset.item.entity.Item;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrder;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrderItem;
import lk.samarasingherSuper.asset.supplier.entity.SupplierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderItemDao extends JpaRepository<PurchaseOrderItem, Integer> {


    List<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder);

    PurchaseOrderItem findByPurchaseOrderAndItem(PurchaseOrder purchaseOrder, Item item);
}
