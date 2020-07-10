package lk.samarasingherSuper.asset.purchaseOrder.service;


import lk.samarasingherSuper.asset.purchaseOrder.dao.PurchaseOrderSupplierDao;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrderSupplier;
import lk.samarasingherSuper.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "purchaseOrderSupplier")
public class PurchaseOrderSupplierService implements AbstractService<PurchaseOrderSupplier, Integer> {
    private final PurchaseOrderSupplierDao purchaseOrderSupplierDao;

    @Autowired
    public PurchaseOrderSupplierService(PurchaseOrderSupplierDao purchaseOrderSupplierDao) {
        this.purchaseOrderSupplierDao = purchaseOrderSupplierDao;
    }

    public List<PurchaseOrderSupplier> findAll() {
        return purchaseOrderSupplierDao.findAll();
    }


    public PurchaseOrderSupplier persist(PurchaseOrderSupplier purchaseOrderSupplier) {
        return purchaseOrderSupplierDao.save(purchaseOrderSupplier);
    }


    public PurchaseOrderSupplier findById(Integer id) {
        return purchaseOrderSupplierDao.getOne(id);
    }

    public boolean delete(Integer id) {
        purchaseOrderSupplierDao.deleteById(id);
        return false;
    }

    public List<PurchaseOrderSupplier> search(PurchaseOrderSupplier purchaseOrderSupplier) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<PurchaseOrderSupplier> purchaseRequestExample = Example.of(purchaseOrderSupplier, matcher);
        return purchaseOrderSupplierDao.findAll(purchaseRequestExample);
    }
}
