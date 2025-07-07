package com.clean.architecture.invoice.mapper;

import com.clean.architecture.invoice.dto.InvoiceLineDTO;
import com.clean.architecture.invoice.dto.create.InvoiceLineCreateDTO;
import com.clean.architecture.invoice.dto.modify.InvoiceLineModifyDTO;
import com.clean.architecture.invoice.model.InvoiceLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceLineMapper {

    InvoiceLineDTO toDto(InvoiceLine invoiceLine);

    List<InvoiceLineDTO> toDtoList(List<InvoiceLine> invoiceLines);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalAmount", expression = "java(calculateTotalAmount(invoiceLineCreateDTO.getQuantity(), invoiceLineCreateDTO.getUnitPrice()))")
    InvoiceLine toCreateEntity(InvoiceLineCreateDTO invoiceLineCreateDTO);

    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "totalAmount", expression = "java(calculateTotalAmount(invoiceLineModifyDTO.getQuantity(), invoiceLineModifyDTO.getUnitPrice()))")
    void toModifyEntity(InvoiceLineModifyDTO invoiceLineModifyDTO, @MappingTarget InvoiceLine invoiceLine);

    default Page<InvoiceLineDTO> toPageDto(Page<InvoiceLine> invoiceLinesPage) {
        return invoiceLinesPage.map(this::toDto);
    }

    default BigDecimal calculateTotalAmount(BigDecimal quantity, BigDecimal unitPrice) {
        return quantity != null && unitPrice != null ? quantity.multiply(unitPrice) : null;
    }

}
