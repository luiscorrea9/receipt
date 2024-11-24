package com.project.receipt.util;

import com.project.receipt.dto.CreateItemDto;
import com.project.receipt.model.ItemModel;
import com.project.receipt.model.ReceiptModel;

import java.text.Normalizer;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static double count(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        String result = normalized.replaceAll("[^A-Za-z0-9]", "");
        return (double) result.length();
    }

    public static double roundDollar(double total){
        if(total%1!=0){
            return 0;
        }return 50;
    }

    public static double multipleTwentyFiveCents(double total){
        if(total%0.25==0){
            return  25;
        }return 0;
    }

    public static double forTwoItems(Integer length){
        double points = 0;
        if(length%2==1 && length !=1){
            points += ((double) (length - 1) /2) * 5;
        }else{
            points += ((double) length /2) * 5;
        }

        return points;
    }

    public static double multipleOfDescription(List<ItemModel> itemModel){
        double points = 0;
        for (ItemModel model : itemModel) {
            String normalized = Normalizer.normalize(model.getShortDescription(), Normalizer.Form.NFD);
            String result = normalized.replaceAll("[^A-Za-z0-9]", "");

            if (result.length() % 3 == 0) {
                double newPrice = Math.ceil(model.getPrice() * 0.2);
                points += newPrice;
            }
        }
        return points;
    }

    public static double dayOdd(Integer day){
       double points = 0;
       if(day%2==1){
           points += 6;
       }
     return points;
    }

    public static double pointForHours(LocalTime hours){
        double points = 0;
        LocalTime startTime = LocalTime.of(14, 0);
        LocalTime endTime = LocalTime.of(16, 0);

        if(hours.isAfter(startTime) && hours.isBefore(endTime)){
            points += 10;
        }
        return points;
    }

    public static List<ItemModel> createItem(List<CreateItemDto> createItemDto){
        List<ItemModel> itemModels = new ArrayList<>();
        for (CreateItemDto item : createItemDto) {
            itemModels.add(new ItemModel(item.getShortDescription(), item.getPrice()));
        }
        return itemModels;
    }

    public static double getTotal(List<CreateItemDto> createItemDto){
        double total = 0;
        for (CreateItemDto item : createItemDto) {
            total += item.getPrice();
        }
        return total;
    }


    public static double allPoints(ReceiptModel receiptModel){

        double points = 0.0;

        points += count(receiptModel.getRetailer());

        points +=roundDollar(receiptModel.getTotal());

        points += multipleTwentyFiveCents(receiptModel.getTotal());

        points += forTwoItems(receiptModel.getItems().size());

        points += multipleOfDescription(receiptModel.getItems());

        points += dayOdd(receiptModel.getPurchaseDate().atStartOfDay().getDayOfMonth());

        points += pointForHours(receiptModel.getPurchaseTime());



        return points;
    }




}
