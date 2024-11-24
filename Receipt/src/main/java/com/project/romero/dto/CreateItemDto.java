package com.project.romero.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateItemDto {

    private String shortDescription;
    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price must be zero or a positive value")
    private Double price;

}
