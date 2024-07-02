package com.wallet.dto;

import com.wallet.enums.OperationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingDataDto {
    @NotNull
    private UUID walletId;
    @NotNull
    private OperationType operationType;
    @Positive
    private BigDecimal amount;
}
