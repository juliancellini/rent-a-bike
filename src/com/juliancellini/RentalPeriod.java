package com.juliancellini;

import java.time.LocalDateTime;

public class RentalPeriod {

    private LocalDateTime from;
    private LocalDateTime to;

    public RentalPeriod(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {

        if (from == null) {
            throw new IllegalArgumentException("from must be not null");
        }

        if (to == null) {
            throw new IllegalArgumentException("to must be not null");
        }

        if (from.isAfter(to) || from.isEqual(to)) {
            throw new IllegalArgumentException("from must be before to");
        }

        this.from = from;
        this.to = to;
    }

    private boolean equalOrAfter(LocalDateTime one, LocalDateTime two){
        return one.isEqual(two) || one.isAfter(two);
    }

    private boolean equalOrABefore(LocalDateTime one, LocalDateTime two){
        return one.isEqual(two) || one.isBefore(two);
    }

    public boolean overlap(RentalPeriod other) {

        //this contained in other
        if (this.equalOrAfter(this.from, other.from) && this.equalOrABefore(this.to,other.to)) {
            return true;
        }

        //other contained in this
        if (this.equalOrABefore(this.from, other.from) && this.equalOrAfter(this.to, other.to)) {
            return true;
        }

        //partial overlap (this.from)
        if (this.equalOrAfter(this.from, other.from) && this.from.isBefore(other.to)) {
            return true;
        }

        //partial overlap (this.to)
        if (this.to.isAfter(other.from) && this.equalOrABefore(this.to, other.to)) {
            return true;
        }

        //None of the above
        return false;
    }

    // based in https://www.sitepoint.com/implement-javas-equals-method-correctly/
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        RentalPeriod rentalPeriod = (RentalPeriod) o;
        // field comparison
        return this.from.equals(rentalPeriod.from)
                && this.to.equals(rentalPeriod.to);
    }


}
