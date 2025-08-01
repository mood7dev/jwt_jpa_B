package com.green.gallery_jwt_jpa.cart.model;

import lombok.Getter;

@Getter
public class CartGetRes {
    private int id;
    private int itemId;
    private String name;
    private int price;
    private String imgPath;
    private int discountPer;
}
