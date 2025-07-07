package com.clean.architecture.invoice.delegate;

import com.clean.architecture.invoice.dto.InvoiceLineDTO;
import com.clean.architecture.invoice.dto.create.InvoiceLineCreateDTO;
import com.clean.architecture.invoice.dto.modify.InvoiceLineModifyDTO;

import java.util.List;

public interface InvoiceLineDelegate {

    InvoiceLineDTO findById(Long id);

    List<InvoiceLineDTO> findByInvoiceId(Long invoiceId);

    InvoiceLineDTO create(InvoiceLineCreateDTO invoiceLineCreateDTO);

    InvoiceLineDTO modify(InvoiceLineModifyDTO invoiceLineModifyDTO);

    void deleteById(Long id);

}
