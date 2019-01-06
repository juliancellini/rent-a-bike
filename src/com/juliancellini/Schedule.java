package com.juliancellini;

import java.time.Period;
import java.util.ArrayList;

public class Schedule {

    private ArrayList<RentalPeriod> rentalPeriods;

    public Schedule() {
        this.rentalPeriods = new ArrayList<>();
    }

    public boolean isFree(RentalPeriod rentalPeriod) {
        for (RentalPeriod rp : this.rentalPeriods) {
            if (rp.overlap(rentalPeriod)) {
                return false;
            }
        }
        return true;
    }

    public void addPeriod(RentalPeriod rentalPeriod) {
        if (this.isFree(rentalPeriod)) {
            this.rentalPeriods.add(rentalPeriod);
        } else {
            throw new IllegalArgumentException("Period is not free.");
        }
    }

    public boolean removePeriod(RentalPeriod rentalPeriod) {
        return this.rentalPeriods.remove(rentalPeriod);
    }
}
