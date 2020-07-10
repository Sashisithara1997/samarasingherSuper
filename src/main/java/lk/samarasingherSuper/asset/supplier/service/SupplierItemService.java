package lk.samarasingherSuper.asset.supplier.service;


import lk.samarasingherSuper.asset.item.entity.Item;
import lk.samarasingherSuper.asset.supplier.dao.SupplierItemDao;
import lk.samarasingherSuper.asset.supplier.entity.Enum.ItemSupplierStatus;
import lk.samarasingherSuper.asset.supplier.entity.Supplier;
import lk.samarasingherSuper.asset.supplier.entity.SupplierItem;
import lk.samarasingherSuper.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "supplierItem")
public class SupplierItemService implements AbstractService<SupplierItem, Integer> {
    private final SupplierItemDao supplierItemDao;

    @Autowired
    public SupplierItemService(SupplierItemDao supplierItemDao) {
        this.supplierItemDao = supplierItemDao;
    }

    public List<SupplierItem> findAll() {
        return supplierItemDao.findAll();
    }

    public SupplierItem findById(Integer id) {
        return supplierItemDao.getOne(id);
    }

    public SupplierItem persist(SupplierItem supplierItem) {
        if (supplierItem.getId() == null) {
            supplierItem.setItemSupplierStatus(ItemSupplierStatus.CURRENTLTLY_BUYING);
        }
        return supplierItemDao.save(supplierItem);
    }

    public boolean delete(Integer id) {
        supplierItemDao.deleteById(id);
        return false;
    }

    public List<SupplierItem> search(SupplierItem supplierItem) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<SupplierItem> supplierItemExample = Example.of(supplierItem, matcher);
        return supplierItemDao.findAll(supplierItemExample);
    }

    public SupplierItem findBySupplierAndItem(Supplier supplier, Item item) {
        return supplierItemDao.findBySupplierAndItem(supplier, item);
    }

    public List<SupplierItem> findBySupplier(Supplier supplier) {
        return supplierItemDao.findBySupplier(supplier);
    }
}
