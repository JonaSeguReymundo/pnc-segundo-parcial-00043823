package com.uca.pncsegundoparcialcoworking.dto.request;

import com.uca.pncsegundoparcialcoworking.utils.SpaceType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class SpaceRequest {
    private Long id;

    @NotNull(message = "The name should not be empty")
    private String name;

    private String description;
    private SpaceType type;
    private Integer capacity;
    private BigDecimal pricePerHour;
    private Boolean available;
    private Integer floor;
    private String amenities;
}
