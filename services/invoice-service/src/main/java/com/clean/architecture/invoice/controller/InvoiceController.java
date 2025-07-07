package com.clean.architecture.invoice.controller;

import com.clean.architecture.invoice.delegate.InvoiceDelegate;
import com.clean.architecture.invoice.dto.InvoiceDTO;
import com.clean.architecture.invoice.dto.create.InvoiceCreateDTO;
import com.clean.architecture.invoice.dto.filter.InvoiceFilterDTO;
import com.clean.architecture.invoice.dto.modify.InvoiceModifyDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceDelegate invoiceDelegate;

    @GetMapping("/{id}")
    public InvoiceDTO findById(@PathVariable Long id) {
        return invoiceDelegate.findById(id);
    }

    @PostMapping("/search")
    public Page<InvoiceDTO> findAll(@Valid @RequestBody InvoiceFilterDTO filter) {
        return invoiceDelegate.findAll(filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDTO create(@Valid @RequestBody InvoiceCreateDTO invoiceCreateDTO) {
        return invoiceDelegate.create(invoiceCreateDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public InvoiceDTO modify(@Valid @RequestBody InvoiceModifyDTO invoiceModifyDTO) {
        return invoiceDelegate.modify(invoiceModifyDTO);
    }

    @PutMapping("/send/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void send(@PathVariable Long id) {
        invoiceDelegate.send(id);
    }

    @PutMapping("/pay/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void pay(@PathVariable Long id) {
        invoiceDelegate.pay(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        invoiceDelegate.deleteById(id);
    }

}
