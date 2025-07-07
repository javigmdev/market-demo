package com.clean.architecture.invoice.service.impl;

import com.clean.architecture.invoice.model.InvoiceLine;
import com.clean.architecture.invoice.repository.InvoiceLineRepository;
import com.clean.architecture.invoice.service.InvoiceLineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvoiceLineServiceImpl implements InvoiceLineService {

    private final InvoiceLineRepository repository;

    @Override
    public InvoiceLine findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InvoiceLine not found with id: " + id));
    }

    @Override
    public List<InvoiceLine> findByInvoiceId(Long invoiceId) {
        return repository.findByInvoiceId(invoiceId);
    }

    @Override
    public BigDecimal sumTotalAmountByInvoiceId(Long invoiceId) {
        return repository.sumTotalAmountByInvoiceId(invoiceId);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public InvoiceLine save(InvoiceLine invoiceLine) {
        return repository.save(invoiceLine);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Invoice  not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByInvoiceId(Long invoiceId) {
        repository.deleteByInvoiceId(invoiceId);
    }

}
