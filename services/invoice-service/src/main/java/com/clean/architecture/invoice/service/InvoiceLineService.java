package com.clean.architecture.invoice.service;

import com.clean.architecture.invoice.model.InvoiceLine;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceLineService {

    InvoiceLine findById(Long id);

    List<InvoiceLine> findByInvoiceId(Long invoiceId);

    BigDecimal sumTotalAmountByInvoiceId(Long invoiceId);

    boolean existsById(Long id);

    InvoiceLine save(InvoiceLine invoiceLine);

    void deleteById(Long id);

    void deleteByInvoiceId(Long invoiceId);

}
