package com.juliancellini;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class RentalType {

    private static final long RENTAL_TOLERANCE_MINUTES = 10;
    private static RentalType RENTAL_TYPE_HOUR = new RentalType(new BigDecimal(5), RentalUnit.HOUR);
    private static RentalType RENTAL_TYPE_DAY = new RentalType(new BigDecimal(20), RentalUnit.DAY);
    private static RentalType RENTAL_TYPE_WEEK = new RentalType(new BigDecimal(60), RentalUnit.WEEK);
    private BigDecimal price;
    private RentalUnit unit;

    private RentalType(BigDecimal price, RentalUnit unit) {
        this.price = price;
        this.unit = unit;
    }

    public static RentalType getRentalType(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {

        if (from == null) {
            throw new IllegalArgumentException("from must be not null");
        }

        if (to == null) {
            throw new IllegalArgumentException("to must be not null");
        }

        if (from.isAfter(to) || from.isEqual(to)) {
            throw new IllegalArgumentException("from must be before to");
        }

        if (from.until(to, ChronoUnit.HOURS) < 4)
            return RENTAL_TYPE_HOUR;

        if (from.until(to, ChronoUnit.DAYS) < 3)
            return RENTAL_TYPE_DAY;

        return RENTAL_TYPE_WEEK;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public RentalUnit getUnit() {
        return this.unit;
    }

    public BigDecimal totalPrice(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {

        if (from == null) {
            throw new IllegalArgumentException("from must be not null");
        }

        if (to == null) {
            throw new IllegalArgumentException("to must be not null");
        }

        if (from.isAfter(to) || from.isEqual(to)) {
            throw new IllegalArgumentException("from must be before to");
        }

        long units = 0;

        LocalDateTime from_with_tolerance = from.plusMinutes(RENTAL_TOLERANCE_MINUTES);

        switch (this.unit) {
            case HOUR:
                units = from_with_tolerance.until(to, ChronoUnit.HOURS) + 1;
                break;
            case DAY:
                units = from_with_tolerance.until(to, ChronoUnit.DAYS) + 1;
                break;
            case WEEK:
                units = from_with_tolerance.until(to, ChronoUnit.WEEKS) + 1;
                break;
        }

        if (units <= 0) {
            units = 1;
        }

        return this.price.multiply(new BigDecimal(units));
    }

    public enum RentalUnit {
        HOUR, DAY, WEEK
    }

}
