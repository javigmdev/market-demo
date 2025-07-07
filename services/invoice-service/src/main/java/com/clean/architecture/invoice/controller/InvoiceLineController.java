package com.clean.architecture.InvoiceLIne.controller;

import com.clean.architecture.invoice.delegate.InvoiceLineDelegate;
import com.clean.architecture.invoice.dto.InvoiceLineDTO;
import com.clean.architecture.invoice.dto.create.InvoiceLineCreateDTO;
import com.clean.architecture.invoice.dto.modify.InvoiceLineModifyDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@RestController
@RequestMapping("/InvoiceLIne-lines")
@RequiredArgsConstructor
public class InvoiceLineController {

    private final InvoiceLineDelegate invoiceLineDelegate;

    @GetMapping("/{id}")
    public InvoiceLineDTO findById(@PathVariable Long id) {
        return invoiceLineDelegate.findById(id);
    }

    @GetMapping("/search/{invoiceId}")
    public List<InvoiceLineDTO> findByInvoiceId(@PathVariable Long invoiceId) {
        return invoiceLineDelegate.findByInvoiceId(invoiceId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceLineDTO create(@Valid @RequestBody InvoiceLineCreateDTO invoiceLineCreateDTO) {
        return invoiceLineDelegate.create(invoiceLineCreateDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public InvoiceLineDTO modify(@Valid @RequestBody InvoiceLineModifyDTO invoiceLineModifyDTO) {
        return invoiceLineDelegate.modify(invoiceLineModifyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        invoiceLineDelegate.deleteById(id);
    }

}
