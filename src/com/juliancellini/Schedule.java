package com.juliancellini;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<RentalPeriod> rentalPeriods;

    public Schedule() {
        this.rentalPeriods = new ArrayList<>();
    }

    boolean isFree(RentalPeriod rentalPeriod) {
        for (RentalPeriod rp : this.rentalPeriods) {
            if (rp.overlap(rentalPeriod)) {
                return false;
            }
        }
        return true;
    }

    void addPeriod(RentalPeriod rentalPeriod) {
        if (this.isFree(rentalPeriod)) {
            this.rentalPeriods.add(rentalPeriod);
        } else {
            throw new IllegalArgumentException("Period overlaps.");
        }
    }

    boolean removePeriod(RentalPeriod rentalPeriod) {
        return this.rentalPeriods.remove(rentalPeriod);
    }
}
