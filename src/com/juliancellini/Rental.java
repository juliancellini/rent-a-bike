package com.juliancellini;

import java.time.LocalDateTime;

public class Rental {

    private String bike;
    private String user;
    private RentalType type;
    private Promotion promotion;
    private LocalDateTime from;
    private LocalDateTime to;

    public Rental(String bike, String user, LocalDateTime from, LocalDateTime to, Promotion promotion) throws IllegalArgumentException {
        this.bike = bike;
        this.user = user;
        this.from = from;
        this.to = to;
        this.type = RentalType.getRentalType(from, to);
        this.promotion = promotion;
    }

    public Double totalPrice() {
        Double price = this.type.totalPrice(this.from, this.to);
        return price - price * this.promotion.getDiscount();
    }

}
