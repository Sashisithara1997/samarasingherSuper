package lk.samarasingherSuper.asset.supplier.controller;

import lk.samarasingherSuper.asset.commonAsset.service.CommonService;
import lk.samarasingherSuper.asset.item.entity.Item;
import lk.samarasingherSuper.asset.item.service.ItemService;
import lk.samarasingherSuper.asset.supplier.entity.Enum.ItemSupplierStatus;
import lk.samarasingherSuper.asset.supplier.entity.Supplier;
import lk.samarasingherSuper.asset.supplier.entity.SupplierItem;
import lk.samarasingherSuper.asset.supplier.service.SupplierItemService;
import lk.samarasingherSuper.asset.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/supplierItem")
public class SupplierItemController {
    private final ItemService itemService;
    private final SupplierService supplierService;
    private final CommonService commonService;
    private final SupplierItemService supplierItemService;

    @Autowired
    public SupplierItemController(ItemService itemService, SupplierService supplierService, CommonService commonService, SupplierItemService supplierItemService) {
        this.itemService = itemService;
        this.supplierService = supplierService;
        this.commonService = commonService;
        this.supplierItemService = supplierItemService;
    }

    @GetMapping
    public String addForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("searchAreaShow", true);
        return "supplier/addSupplierItem";
    }

    @PostMapping("/find")
    public String search(@Valid @ModelAttribute Supplier supplier, Model model) {
        return commonService.supplierItemAndPurchaseOrderSearch(supplier, model, "supplier/addSupplierItem");
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        commonService.supplierItemAndPurchaseOrderView(model, id);
        model.addAttribute("items", itemService.findAll());
        return "supplier/addSupplierItem";
    }

    @PostMapping
    public String supplierItemPersist(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            var value = false;
            for (SupplierItem supplierItem : supplier.getSupplierItems()) {
                if (supplierItem.getId() != null) {
                    value = true;
                    break;
                }
            }
            if (value) {
                return "redirect:/supplierItem/edit/" + supplier.getId();
            } else {
                return "redirect:/supplierItem/" + supplier.getId();
            }
        }
        //items from front item relevant to supplier
        List<SupplierItem> supplierItems = supplier.getSupplierItems();
        for (SupplierItem supplierItem : supplierItems) {
            if (supplierItem.getId() == null) {
                supplierItem.setSupplier(supplier);
            }
           // System.out.println("  hhhh  " + supplierItem.getItem().getId().toString());
            supplierItemService.persist(supplierItem);
        }
        return "redirect:/supplier";
    }

    @GetMapping("/supplier/{id}")
    public String addPriceToSupplierItem(@PathVariable int id, Model model) {
        Supplier supplier = supplierService.findById(id);
        List<SupplierItem> supplierItems = supplierItemService.findBySupplier(supplier);
        // supplier.setSupplierItems(supplierItems);
        model.addAttribute("itemSupplierStatus", ItemSupplierStatus.values());
        model.addAttribute("supplierDetail", supplier);
      //aluthin hadapu thena-baka baka
        //model.addAttribute("supplier", new Supplier());
        model.addAttribute("supplierDetailShow", false);
        model.addAttribute("supplierItemEdit", false);
        model.addAttribute("currentlyBuyingItems", supplierItems);
        List<Item> items = itemService.findAll();

        if (!supplierItems.isEmpty()) {
            for (Item item : itemService.findAll()) {
                for (SupplierItem supplierItem : supplierItems) {
                    if (item.equals(supplierItem.getItem())) {
                        items.remove(item);
                    }
                }
            }
        }

        model.addAttribute("items", items);
        return "supplier/addSupplierItem";
    }

    @GetMapping("/edit/{id}")
    public String addEditToSupplierItem(@PathVariable int id, Model model) {
        Supplier supplier = supplierService.findById(id);
        List<SupplierItem> supplierItems = supplierItemService.findBySupplier(supplier);
        model.addAttribute("itemSupplierStatus", ItemSupplierStatus.values());
        model.addAttribute("supplierDetail", supplier);
        model.addAttribute("supplierDetailShow", false);
        model.addAttribute("currentlyBuyingItems", supplierItems);
        model.addAttribute("supplierItemEdit", true);
        return "supplier/addSupplierItem";
    }

}
