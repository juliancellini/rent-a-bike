package com.juliancellini;

public class Promotion {

    public static Promotion FAMILY = new Promotion(0.3);
    public static Promotion NULL = new Promotion(0.0);

    private Double discount;

    public Double getDiscount() {
        return this.discount;
    }

    private Promotion(Double discount){
        this.discount = discount;
    }

}
