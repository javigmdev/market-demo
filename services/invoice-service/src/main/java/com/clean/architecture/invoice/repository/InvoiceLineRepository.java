package com.clean.architecture.invoice.repository;

import com.clean.architecture.invoice.model.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Long>, JpaSpecificationExecutor<InvoiceLine> {

    List<InvoiceLine> findByInvoiceId(Long invoiceId);

    @Query("SELECT SUM(il.totalAmount) FROM InvoiceLine il WHERE il.invoice.id = :invoiceId")
    BigDecimal sumTotalAmountByInvoiceId(Long invoiceId);

    void deleteByInvoiceId(Long invoiceId);

}
