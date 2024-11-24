package com.project.romero.service;

import com.project.romero.dto.CreateReceiptDto;
import com.project.romero.model.ReceiptModel;

import java.util.List;

public interface IReceiptService {

    double findById(String id);
    Object saveReceipt(CreateReceiptDto createReceiptDto);
    List<ReceiptModel> findAll();

}
