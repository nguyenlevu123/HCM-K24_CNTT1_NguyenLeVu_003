package com.example.demo.dto.request;

import com.example.demo.entity.MovementType;
import com.example.demo.entity.WatchStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchRequest{

    @NotBlank(message = "khong de trong")
    private String modelName;

    @NotBlank(message = "khong de trong")
    private String brand;

    @NotNull(message = "khong de trong")
    @DecimalMin(value = "0", message = "gias phai lon hon 0")
    private BigDecimal price;

    @NotNull(message = "khong de trong")
    private MovementType movementType;

    @NotNull(message = "khong de trong")
    private WatchStatus status;
}
