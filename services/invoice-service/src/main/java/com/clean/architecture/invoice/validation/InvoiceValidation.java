package com.clean.architecture.invoice.validation;

import com.clean.architecture.invoice.enums.InvoiceStatusEnum;
import com.clean.architecture.invoice.model.Invoice;
import com.clean.architecture.invoice.service.InvoiceService;
import com.clean.architecture.shared.exception.core.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceValidation {

    private final InvoiceService invoiceService;

    public void validateForCreate(Invoice invoice) {
        ValidationResult validationResult = new ValidationResult();
        if(invoiceService.existsByInvoiceNumber(invoice.getInvoiceNumber())) {
            validationResult.addError("invoiceNumber", "The invoice number already exists");
        }
        validationResult.throwIfInvalid();
    }

    public void validateForModify(Invoice invoice) {
        ValidationResult validationResult = new ValidationResult();
        if(!invoiceService.existsById(invoice.getId())) {
            validationResult.addError("id", "The invoice does not exist");
        }
        if(invoiceService.existsByInvoiceNumberAndIdNot(invoice.getInvoiceNumber(), invoice.getId())) {
            validationResult.addError("invoiceNumber", "The invoice number already exists");
        }
        validationResult.throwIfInvalid();
    }

    public void validateForSend(Long statusId) {
        ValidationResult validationResult = new ValidationResult();
        if(!statusId.equals(InvoiceStatusEnum.CREATED.getId())) {
            validationResult.addError("status", "The invoice must be in CREATED status to be sent");
        }
        validationResult.throwIfInvalid();
    }

    public void validateForPay(Long statusId) {
        ValidationResult validationResult = new ValidationResult();
        if(!statusId.equals(InvoiceStatusEnum.SENT.getId())) {
            validationResult.addError("status", "The invoice must be in SENT status to be paid");
        }
        validationResult.throwIfInvalid();
    }

}
