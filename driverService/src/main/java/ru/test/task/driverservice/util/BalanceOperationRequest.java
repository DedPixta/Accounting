package ru.test.task.driverservice.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BalanceOperationRequest {

    @Schema(description = "Driver's id with the account of which the operation is to be carried out", example = "5")
    @NonNull
    private Long driverId;

    @Schema(description = "Amount of money for the operation", example = "5")
    @NonNull
    private BigDecimal amount;

    @Schema(description = "Currency type", example = "RED")
    @NonNull
    private CurrencyType currencyType;
}
