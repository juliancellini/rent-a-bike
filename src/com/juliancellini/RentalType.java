package com.juliancellini;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class RentalType {

    public enum RentalUnit {
        HOUR, DAY, WEEK
    }

    private static RentalType RENTAL_TYPE_HOUR = new RentalType(5.0, RentalUnit.HOUR);
    private static RentalType RENTAL_TYPE_DAY = new RentalType(20.0, RentalUnit.DAY);
    private static RentalType RENTAL_TYPE_WEEK = new RentalType(60.0, RentalUnit.WEEK);


    private RentalType(Double price, RentalUnit unit) {
        this.price = price;
        this.unit = unit;
    }

    private Double price;
    private RentalUnit unit;

    public Double getPrice() {
        return this.price;
    }

    public RentalUnit getUnit() {
        return this.unit;
    }

    public Double totalPrice(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("from must be before to");
        }

        long units = 0;

        switch (this.unit) {
            case HOUR:
                units = from.until(to, ChronoUnit.HOURS);
                break;
            case DAY:
                units = from.until(to, ChronoUnit.DAYS);
                break;
            case WEEK:
                units = from.until(to, ChronoUnit.WEEKS);
                break;
        }

        if (units <= 0) {
            units = 1;
        }

        return units * this.price;
    }

    public static RentalType getRentalType(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("from must be before to")
                    ;
        }

        if (from.until(to, ChronoUnit.HOURS) < 4)
            return RENTAL_TYPE_HOUR;

        if (from.until(to, ChronoUnit.DAYS) < 3)
            return RENTAL_TYPE_DAY;

        return RENTAL_TYPE_WEEK;
    }

}
