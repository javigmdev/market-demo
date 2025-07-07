package com.clean.architecture.invoice.delegate.impl;

import com.clean.architecture.invoice.delegate.InvoiceLineDelegate;
import com.clean.architecture.invoice.dto.InvoiceLineDTO;
import com.clean.architecture.invoice.dto.create.InvoiceLineCreateDTO;
import com.clean.architecture.invoice.dto.modify.InvoiceLineModifyDTO;
import com.clean.architecture.invoice.mapper.InvoiceLineMapper;
import com.clean.architecture.invoice.model.InvoiceLine;
import com.clean.architecture.invoice.service.InvoiceLineService;
import com.clean.architecture.invoice.service.InvoiceService;
import com.clean.architecture.invoice.validation.InvoiceLineValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InvoiceLineDelegateImpl implements InvoiceLineDelegate {

    private final InvoiceLineService invoiceLineService;
    private final InvoiceService invoiceService;
    private final InvoiceLineMapper invoiceLineMapper;
    private final InvoiceLineValidation invoiceLineValidation;

    @Override
    public InvoiceLineDTO findById(Long id) {
        InvoiceLine invoiceLine = invoiceLineService.findById(id);
        return invoiceLineMapper.toDto(invoiceLine);
    }

    @Override
    public List<InvoiceLineDTO> findByInvoiceId(Long invoiceId) {
        List<InvoiceLine> invoiceLines = invoiceLineService.findByInvoiceId(invoiceId);
        return invoiceLineMapper.toDtoList(invoiceLines);
    }

    @Override
    @Transactional
    public InvoiceLineDTO create(InvoiceLineCreateDTO invoiceLineCreateDTO) {
        InvoiceLine invoiceLine = invoiceLineMapper.toCreateEntity(invoiceLineCreateDTO);
        invoiceLineValidation.validateForCreate(invoiceLine);
        invoiceLine = invoiceLineService.save(invoiceLine);
        recalculateInvoiceTotalAmount(invoiceLine.getInvoice().getId());
        return invoiceLineMapper.toDto(invoiceLine);
    }

    @Override
    @Transactional
    public InvoiceLineDTO modify(InvoiceLineModifyDTO invoiceLineModifyDTO) {
        InvoiceLine invoiceLine = invoiceLineService.findById(invoiceLineModifyDTO.getId());
        invoiceLineMapper.toModifyEntity(invoiceLineModifyDTO, invoiceLine);
        invoiceLineValidation.validateForModify(invoiceLine);
        invoiceLine = invoiceLineService.save(invoiceLine);
        recalculateInvoiceTotalAmount(invoiceLine.getInvoice().getId());
        return invoiceLineMapper.toDto(invoiceLine);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        InvoiceLine invoiceLine = invoiceLineService.findById(id);
        if(invoiceLine == null) {
            throw new EntityNotFoundException("InvoiceLine not found with id: " + id);
        }
        invoiceLineService.deleteById(id);
        recalculateInvoiceTotalAmount(invoiceLine.getInvoice().getId());
    }

    private void recalculateInvoiceTotalAmount(Long invoiceId) {
        BigDecimal invoiceTotalAmount = invoiceLineService.sumTotalAmountByInvoiceId(invoiceId);
        invoiceService.recalculateTotalAmount(invoiceId, invoiceTotalAmount);
    }

}
