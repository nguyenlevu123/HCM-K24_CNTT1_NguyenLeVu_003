package com.example.demo.dto.response;

import com.example.demo.entity.MovementType;
import com.example.demo.entity.WatchStatus;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WatchResponse{

    private Long id;
    private String modelName;
    private String brand;
    private BigDecimal price;
    private MovementType movementType;
    private WatchStatus status;
    private Boolean isDeleted;
}
