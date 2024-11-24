package com.project.receipt.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateReceiptDto {

   @NotEmpty(message = "Retailer shouldn't be empty")
   private String retailer;
   @PastOrPresent
   private LocalDate purchaseDate;
   private LocalTime purchaseTime;

   private double points = 0.0;

   @NotEmpty(message = "Items shouldn't be empty")
   @Valid
   private List<CreateItemDto> items;

   @PositiveOrZero
   private Double total;

}
