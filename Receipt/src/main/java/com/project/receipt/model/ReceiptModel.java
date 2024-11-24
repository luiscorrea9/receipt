package com.project.receipt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptModel {

    private String id;
    private String retailer;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;
    private double total = 0.0;
    private double points = 0.0;
    private List<ItemModel> items;


}
