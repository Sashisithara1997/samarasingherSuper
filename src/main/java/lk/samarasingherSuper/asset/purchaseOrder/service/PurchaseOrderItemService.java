package lk.samarasingherSuper.asset.purchaseOrder.service;


import lk.samarasingherSuper.asset.item.entity.Item;
import lk.samarasingherSuper.asset.purchaseOrder.dao.PurchaseOrderItemDao;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrder;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrderItem;
import lk.samarasingherSuper.asset.supplier.entity.SupplierItem;
import lk.samarasingherSuper.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "purchaseOrderItem")
public class PurchaseOrderItemService implements AbstractService<PurchaseOrderItem, Integer> {
    private final PurchaseOrderItemDao purchaseOrderItemDao;

    @Autowired
    public PurchaseOrderItemService(PurchaseOrderItemDao purchaseOrderDao) {
        this.purchaseOrderItemDao = purchaseOrderDao;
    }

    public List<PurchaseOrderItem> findAll() {
        return purchaseOrderItemDao.findAll();
    }

    public PurchaseOrderItem findById(Integer id) {
        return purchaseOrderItemDao.getOne(id);
    }

    public PurchaseOrderItem persist(PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemDao.save(purchaseOrderItem);
    }

    public boolean delete(Integer id) {
        purchaseOrderItemDao.deleteById(id);
        return false;
    }

    public List<PurchaseOrderItem> search(PurchaseOrderItem purchaseOrderItem) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<PurchaseOrderItem> purchaseRequestExample = Example.of(purchaseOrderItem, matcher);
        return purchaseOrderItemDao.findAll(purchaseRequestExample);
    }

    public PurchaseOrderItem findByPurchaseOrderAndItem(PurchaseOrder purchaseOrder, Item item) {
        return purchaseOrderItemDao.findByPurchaseOrderAndItem(purchaseOrder, item);

    }

    public List<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderItemDao.findByPurchaseOrder(purchaseOrder);
    }

}
