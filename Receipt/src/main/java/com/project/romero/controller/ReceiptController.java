package com.project.romero.controller;

import com.project.romero.dto.CreateItemDto;
import com.project.romero.dto.CreateReceiptDto;
import com.project.romero.handler.ResponseHandler;
import com.project.romero.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping()
    public ResponseEntity<Object>getAll(){
        return ResponseHandler.generateResponse("Receipts", HttpStatus.OK,
                this.receiptService.findAll());
    }


    @GetMapping("{id}")
    public ResponseEntity<Object>getOne(@PathVariable("id") String id){
       return ResponseHandler.generateResponse("Receipt", HttpStatus.OK,
               this.receiptService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> saveReceipt(@Validated @RequestBody CreateReceiptDto createReceiptDto) {

        return ResponseHandler.generateResponse(
                "Save receipt",
                HttpStatus.OK,
                this.receiptService.saveReceipt(createReceiptDto)
        );
    }




}
