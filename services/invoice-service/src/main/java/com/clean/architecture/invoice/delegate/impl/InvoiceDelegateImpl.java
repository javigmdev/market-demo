package com.clean.architecture.invoice.delegate.impl;

import com.clean.architecture.invoice.delegate.InvoiceDelegate;
import com.clean.architecture.invoice.dto.InvoiceDTO;
import com.clean.architecture.invoice.dto.create.InvoiceCreateDTO;
import com.clean.architecture.invoice.dto.filter.InvoiceFilterDTO;
import com.clean.architecture.invoice.dto.modify.InvoiceModifyDTO;
import com.clean.architecture.invoice.enums.InvoiceStatusEnum;
import com.clean.architecture.invoice.mapper.InvoiceMapper;
import com.clean.architecture.invoice.model.Invoice;
import com.clean.architecture.invoice.model.InvoiceStatus;
import com.clean.architecture.invoice.repository.specification.InvoiceSpecification;
import com.clean.architecture.invoice.service.InvoiceLineService;
import com.clean.architecture.invoice.service.InvoiceService;
import com.clean.architecture.invoice.validation.InvoiceValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class InvoiceDelegateImpl implements InvoiceDelegate {

    private final InvoiceService invoiceService;
    private final InvoiceLineService invoiceLineService;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceValidation invoiceValidation;

    @Override
    public InvoiceDTO findById(Long id) {
        Invoice invoice = invoiceService.findById(id);
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public Page<InvoiceDTO> findAll(InvoiceFilterDTO filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        Page<Invoice> invoicesPage = invoiceService.findAll(InvoiceSpecification.matching(filter), pageable);
        return invoiceMapper.toPageDto(invoicesPage);
    }

    @Override
    public InvoiceDTO create(InvoiceCreateDTO invoiceCreateDTO) {
        Invoice invoice = invoiceMapper.toCreateEntity(invoiceCreateDTO);
        invoiceValidation.validateForCreate(invoice);
        invoice = invoiceService.save(invoice);
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public InvoiceDTO modify(InvoiceModifyDTO invoiceModifyDTO) {
        Invoice invoice = invoiceService.findById(invoiceModifyDTO.getId());
        invoiceMapper.toModifyEntity(invoiceModifyDTO, invoice);
        invoiceValidation.validateForModify(invoice);
        invoice = invoiceService.save(invoice);
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public void send(Long id) {
        updateStatus(id, InvoiceStatusEnum.SENT, status -> invoiceValidation.validateForSend(status.getId()));
    }

    @Override
    public void pay(Long id) {
        updateStatus(id, InvoiceStatusEnum.PAID, status -> invoiceValidation.validateForPay(status.getId()));
    }

    private void updateStatus(Long id, InvoiceStatusEnum newStatus, Consumer<InvoiceStatus> validator) {
        Invoice invoice = invoiceService.findById(id);
        validator.accept(invoice.getStatus());
        invoice.setStatus(InvoiceStatus.builder().id(newStatus.getId()).build());
        invoiceService.save(invoice);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        invoiceService.deleteById(id);
        invoiceLineService.deleteByInvoiceId(id);
    }

}
