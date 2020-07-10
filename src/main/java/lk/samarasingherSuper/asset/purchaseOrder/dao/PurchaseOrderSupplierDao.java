package lk.samarasingherSuper.asset.purchaseOrder.dao;

import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrderSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderSupplierDao extends JpaRepository<PurchaseOrderSupplier, Integer> {
}
