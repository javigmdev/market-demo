package com.clean.architecture.invoice.dto.modify;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceModifyDTO {

    @NotNull(message = "Invoice ID is required")
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Customer name cannot exceed 100 characters")
    private String customerName;

    @NotBlank(message = "Customer email is required")
    @Email(message = "Customer email must be valid")
    @Size(max = 100, message = "Customer email cannot exceed 100 characters")
    private String customerEmail;

    @NotBlank(message = "Customer address is required")
    @Size(max = 255, message = "Customer address cannot exceed 255 characters")
    private String customerAddress;

}
