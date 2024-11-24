package com.project.romero.service;

import com.project.romero.dto.CreateReceiptDto;
import com.project.romero.model.ItemModel;
import com.project.romero.model.ReceiptModel;
import com.project.romero.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.List;


@Service
public class ReceiptService implements IReceiptService{

    List<ReceiptModel> model = new ArrayList<>();


    @Override
    public double findById(String id) {
        ReceiptModel findReceipt = model.stream().filter(
                m -> Objects.equals(m.getId(), id)).findFirst().orElseThrow();
        return findReceipt.getPoints();
    }

    @Override
    public String saveReceipt(CreateReceiptDto createReceiptDto) {
        double total = Utils.getTotal(createReceiptDto.getItems());
        List<ItemModel> itemModels = Utils.createItem(createReceiptDto.getItems());

        ReceiptModel receipt = new ReceiptModel(UUID.randomUUID().toString().replace("-", ""),
                createReceiptDto.getRetailer(),
                createReceiptDto.getPurchaseDate(),
                createReceiptDto.getPurchaseTime(),
                total,
                0.0,
                itemModels);

        double points = Utils.allPoints(receipt);
        receipt.setPoints(points);

        try{
            model.add(receipt);
        }catch (Error error){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, error.getMessage(), null);
        }
        return receipt.getId();
    }

        @Override
    public List<ReceiptModel> findAll() {
        return model;
    }
}
