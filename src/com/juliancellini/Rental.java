package com.juliancellini;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Rental {

    private Bike bike;
    private String user;
    private RentalType type;
    private Promotion promotion;
    private LocalDateTime from;
    private LocalDateTime to;

    public Rental(Bike bike, String user, LocalDateTime from, LocalDateTime to, Promotion promotion) throws IllegalArgumentException {

        if (from == null) {
            throw new IllegalArgumentException("from must be not null");
        }

        if (to == null) {
            throw new IllegalArgumentException("to must be not null");
        }

        if (from.isAfter(to) || from.isEqual(to)) {
            throw new IllegalArgumentException("from must be before to");
        }

        this.bike = bike;
        this.user = user;
        this.from = from;
        this.to = to;
        this.type = RentalType.getRentalType(from, to);
        this.promotion = promotion;

        this.bike.getSchedule().addPeriod(new RentalPeriod(this.from, this.to));
    }

    public Promotion getPromotion() {
        return this.promotion;
    }

    public BigDecimal listPrice() {
        return this.type.totalPrice(this.from, this.to);
    }

    public BigDecimal totalPrice() {
        return this.promotion.calcTotalAmount(this);
    }

}
