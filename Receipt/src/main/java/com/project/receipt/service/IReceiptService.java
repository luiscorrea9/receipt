package com.project.receipt.service;

import com.project.receipt.dto.CreateReceiptDto;
import com.project.receipt.model.ReceiptModel;

import java.util.List;

public interface IReceiptService {

    double findById(String id);
    Object saveReceipt(CreateReceiptDto createReceiptDto);
    List<ReceiptModel> findAll();

}
