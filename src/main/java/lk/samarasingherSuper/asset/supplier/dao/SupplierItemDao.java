package lk.samarasingherSuper.asset.supplier.dao;

import lk.samarasingherSuper.asset.item.entity.Item;
import lk.samarasingherSuper.asset.supplier.entity.Supplier;
import lk.samarasingherSuper.asset.supplier.entity.SupplierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierItemDao extends JpaRepository<SupplierItem, Integer> {
    SupplierItem findBySupplierAndItem(Supplier supplier, Item item);

    List<SupplierItem> findBySupplier(Supplier supplier);
}
