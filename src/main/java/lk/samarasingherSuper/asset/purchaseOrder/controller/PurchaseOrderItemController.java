package lk.samarasingherSuper.asset.purchaseOrder.controller;


import lk.samarasingherSuper.asset.commonAsset.service.CommonService;
import lk.samarasingherSuper.asset.item.service.ItemService;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrder;
import lk.samarasingherSuper.asset.purchaseOrder.entity.PurchaseOrderItem;
import lk.samarasingherSuper.asset.purchaseOrder.service.PurchaseOrderItemService;
import lk.samarasingherSuper.asset.purchaseOrder.service.PurchaseOrderService;
import lk.samarasingherSuper.asset.supplier.entity.Enum.ItemSupplierStatus;
import lk.samarasingherSuper.asset.supplier.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/purchaseOrderItem")
public class PurchaseOrderItemController {
    private final ItemService itemService;
    private final PurchaseOrderService purchaseOrderService;
    private final CommonService commonService;
 private final PurchaseOrderItemService purchaseOrderItemService;

    @Autowired
    public PurchaseOrderItemController(ItemService itemService, PurchaseOrderService purchaseOrderService, PurchaseOrderItemService purchaseOrderItemService, CommonService commonService) {
        this.itemService = itemService;
        this.purchaseOrderService = purchaseOrderService;
        this.commonService = commonService;
        this.purchaseOrderItemService = purchaseOrderItemService;
    }

    //purchaseOrders find form
    //suppler 1 or purchaseOrders -> need to select 1
    // suppler with items form
    //save purchaseOrderItem

    @GetMapping
    public String addForm(Model model) {
        model.addAttribute("purchaseOrder", new PurchaseOrder());
        model.addAttribute("searchAreaShow", true);
        return "purchaseOrder/addPurchaseOrderItem";
    }

    @PostMapping("/find")
    public String search(@Valid @ModelAttribute Supplier supplier, Model model) {
        return commonService.supplierItemAndPurchaseOrderSearch(supplier, model, "purchaseOrder/addPurchaseOrderItem");
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        commonService.supplierItemAndPurchaseOrderView(model, id);
        model.addAttribute("items", itemService.findAll());
        return "purchaseOrder/addPurchaseOrderItem";
    }

 /*   @PostMapping
    public String purchaseOrderItemPersist(@Valid @ModelAttribute PurchaseOrder purchaseOrder, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println(" i herer ");
        if (bindingResult.hasErrors()) {
            return "redirect:/purchaseOrderItem/" + purchaseOrder.getId();

        }
        for (PurchaseOrderItem purchaseOrderItem : purchaseOrder.getPurchaseOrderItems()) {
            purchaseOrderItem.setPurchaseOrder(purchaseOrder);
            //db purchaseOrder
            PurchaseOrderItem DBPurchaseOrderItem = purchaseOrderItemService.findByPurchaseOrderAndItem(purchaseOrder, purchaseOrderItem.getItem());
            if (DBPurchaseOrderItem != null) {
                DBPurchaseOrderItem.setPrice(purchaseOrderItem.getPrice());
            }
            purchaseOrderItemService.persist(purchaseOrderItem);
        }
        return "redirect:/purchaseOrder";
    }*/
    @GetMapping("/purchaseOrder/{id}")
    public String addPriceToPurchaseOrderItem(@PathVariable int id, Model model) {
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(id);
        purchaseOrder.setPurchaseOrderItems(purchaseOrderItemService.findByPurchaseOrder(purchaseOrder));
        model.addAttribute("itemSupplierStatus", ItemSupplierStatus.values());
        model.addAttribute("purchaseOrderDetail", purchaseOrder);
        model.addAttribute("purchaseOrderDetailShow", false);
        model.addAttribute("items", itemService.findAll());
        return "purchaseOrder/addPurchaseOrderItem";
    }


}
