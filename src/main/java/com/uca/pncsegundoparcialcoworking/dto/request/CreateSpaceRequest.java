package com.uca.pncsegundoparcialcoworking.dto.request;

import com.uca.pncsegundoparcialcoworking.utils.SpaceType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSpaceRequest {

    @NotBlank(message = "El nombre es requerido y no puede estar vacío.")
    @UniqueSpaceName
    private String name;

    private String description;

    @NotNull(message = "El tipo de espacio (type) es obligatorio.")
    private SpaceType type;

    @NotNull(message = "La capacidad es obligatoria.")
    @Min(value = 1, message = "La capacidad mínima debe ser de al menos 1 persona.")
    private Integer capacity;

    @NotNull(message = "El precio por hora es obligatorio.")
    @DecimalMin(value = "0.01", message = "El precio por hora debe ser mayor a cero. No se permiten espacios gratuitos.")
    private BigDecimal pricePerHour;

    @NotNull(message = "La disponibilidad inicial debe ser especificada.")
    private Boolean available;

    @NotNull(message = "El número de piso es obligatorio.")
    @Min(value = 0, message = "El número de piso no puede ser negativo.")
    private Integer floor;

    private String amenities;
}