package com.uca.pncsegundoparcialcoworking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "space")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Enum type;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "pricePerHour")
    private BigDecimal pricePerHour;

    @Column(name="available")
    private Boolean available;

    @Column(name="floor")
    private Integer floor;

    @Column(name="amenities")
    private String amenities;
}
