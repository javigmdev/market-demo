package com.clean.architecture.invoice.service;

import com.clean.architecture.invoice.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public interface InvoiceService {

    Invoice findById(Long id);

    Page<Invoice> findAll(Specification<Invoice> specification, Pageable pageable);

    boolean existsById(Long id);

    boolean existsByInvoiceNumber(String invoiceNumber);

    boolean existsByInvoiceNumberAndIdNot(String invoiceNumber, Long id);

    Invoice save(Invoice invoice);

    void recalculateTotalAmount(Long id, BigDecimal totalAmount);

    void deleteById(Long id);

}
