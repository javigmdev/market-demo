package com.clean.architecture.invoice.validation;

import com.clean.architecture.invoice.model.InvoiceLine;
import com.clean.architecture.invoice.service.InvoiceLineService;
import com.clean.architecture.invoice.service.InvoiceService;
import com.clean.architecture.shared.exception.core.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceLineValidation {

    private final InvoiceLineService invoiceLineService;
    private final InvoiceService invoiceService;

    public void validateForCreate(InvoiceLine invoiceLine) {
        ValidationResult validationResult = new ValidationResult();
        if (invoiceLine.getInvoice() == null || !invoiceService.existsById(invoiceLine.getInvoice().getId())) {
            validationResult.addError("invoice", "The invoice does not exist");
        }
        validationResult.throwIfInvalid();
    }

    public void validateForModify(InvoiceLine invoiceLine) {
        ValidationResult validationResult = new ValidationResult();
        if(!invoiceLineService.existsById(invoiceLine.getId())) {
            validationResult.addError("id", "The invoice line does not exist");
        }
        validationResult.throwIfInvalid();
    }

}
