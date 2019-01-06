package com.juliancellini;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Promotion {

    public static Promotion FAMILY = new Promotion(new BigDecimal(0.3));
    public static Promotion NULL = new Promotion(BigDecimal.ZERO);

    private BigDecimal discount;

    private Promotion(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public BigDecimal calcTotalAmount(Rental rental) {
        BigDecimal listPrice = rental.listPrice();
        return listPrice.subtract(listPrice.multiply(this.discount)).setScale(2, RoundingMode.HALF_EVEN);
    }

}
