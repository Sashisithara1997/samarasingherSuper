package lk.samarasingherSuper.asset.invoice.dao;

import lk.samarasingherSuper.asset.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {
}
