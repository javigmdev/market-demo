package com.clean.architecture.invoice.mapper;

import com.clean.architecture.invoice.dto.InvoiceDTO;
import com.clean.architecture.invoice.dto.create.InvoiceCreateDTO;
import com.clean.architecture.invoice.dto.modify.InvoiceModifyDTO;
import com.clean.architecture.invoice.enums.InvoiceStatusEnum;
import com.clean.architecture.invoice.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", imports = {InvoiceStatusEnum.class, BigDecimal.class})
public interface InvoiceMapper {

    InvoiceDTO toDto(Invoice invoice);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status.id", expression = "java(InvoiceStatusEnum.CREATED.getId())")
    @Mapping(target = "totalAmount", expression = "java(BigDecimal.ZERO)")
    Invoice toCreateEntity(InvoiceCreateDTO invoiceCreateDTO);

    @Mapping(target = "invoiceNumber", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    void toModifyEntity(InvoiceModifyDTO invoiceCreateDTO, @MappingTarget Invoice invoice);

    default Page<InvoiceDTO> toPageDto(Page<Invoice> invoicesPage) {
        return invoicesPage.map(this::toDto);
    }

}
