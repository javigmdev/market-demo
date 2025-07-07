package com.clean.architecture.invoice.service.impl;

import com.clean.architecture.invoice.model.Invoice;
import com.clean.architecture.invoice.repository.InvoiceRepository;
import com.clean.architecture.invoice.service.InvoiceService;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repository;

    @Override
    public Invoice findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found with id: " + id));
    }

    @Override
    public Page<Invoice> findAll(Specification<Invoice> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByInvoiceNumber(String invoiceNumber) {
        return repository.existsByInvoiceNumber(invoiceNumber);
    }

    @Override
    public boolean existsByInvoiceNumberAndIdNot(String invoiceNumber, Long id) {
        return repository.existsByInvoiceNumberAndIdNot(invoiceNumber, id);
    }

    @Override
    @Transactional
    public Invoice save(Invoice invoice) {
        return repository.save(invoice);
    }

    @Override
    @Transactional
    public void recalculateTotalAmount(Long id, BigDecimal totalAmount) {
        Invoice invoice = findById(id);
        if (invoice != null) {
            invoice.setTotalAmount(totalAmount);
            save(invoice);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
