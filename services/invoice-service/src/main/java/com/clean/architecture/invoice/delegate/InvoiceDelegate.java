package com.clean.architecture.invoice.delegate;

import com.clean.architecture.invoice.dto.InvoiceDTO;
import com.clean.architecture.invoice.dto.create.InvoiceCreateDTO;
import com.clean.architecture.invoice.dto.filter.InvoiceFilterDTO;
import com.clean.architecture.invoice.dto.modify.InvoiceModifyDTO;
import org.springframework.data.domain.Page;

public interface InvoiceDelegate {

    InvoiceDTO findById(Long id);

    Page<InvoiceDTO> findAll(InvoiceFilterDTO filter);

    InvoiceDTO create(InvoiceCreateDTO invoiceCreateDTO);

    InvoiceDTO modify(InvoiceModifyDTO invoiceModifyDTO);

    void send(Long id);

    void pay(Long id);

    void deleteById(Long id);

}
