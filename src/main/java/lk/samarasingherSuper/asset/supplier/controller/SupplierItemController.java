package lk.samarasingherSuper.asset.supplier.controller;

import lk.samarasingherSuper.asset.commonAsset.service.CommonService;
import lk.samarasingherSuper.asset.item.service.ItemService;
import lk.samarasingherSuper.asset.supplier.entity.Supplier;
import lk.samarasingherSuper.asset.supplier.service.SupplierItemService;
import lk.samarasingherSuper.asset.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    //suppliers find form
    //suppler 1 or suppliers -> need to select 1
    // suppler with items form
    //save supplierItem

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
    public String supplierItemPersist(@Valid @ModelAttribute Supplier supplier, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "redirect:/supplierItem/" + supplier.getId();
        }
        redirectAttributes.addFlashAttribute("items", supplierService.persist(supplier).getSupplierItems());
        return "redirect:/supplier";
    }

}
