package com.juliancellini;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class RentalTest {

    RentalPeriod rp1;
    RentalPeriod rp1_;
    RentalPeriod rp2;
    RentalPeriod rp3;
    RentalPeriod rp4;
    RentalPeriod rp5;
    RentalPeriod rpAllday;


    @Test
    public void cannotCreateRentalPeriodNullPeriod() {
        try {
            RentalPeriod rp = new RentalPeriod(
                    null,
                    LocalDateTime.of(2019, 1, 1, 10, 0)
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
        try {
            RentalPeriod rp = new RentalPeriod(
                    LocalDateTime.of(2019, 1, 1, 10, 0),
                    null
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
        try {
            RentalPeriod rp = new RentalPeriod(
                    LocalDateTime.of(2019, 1, 1, 10, 0),
                    LocalDateTime.of(2019, 1, 1, 10, 0)
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
    }

    @Test
    public void equalRentalPeriod() {

        Assert.assertEquals(rp1, rp1);
        Assert.assertEquals(rp1, rp1_);
        Assert.assertEquals(rp1_, rp1);

        Assert.assertNotEquals(rp1, rp2);
        Assert.assertNotEquals(null, rp2);
        Assert.assertNotEquals(rp1, null);
        Assert.assertNotEquals(rp1, new Bike("I am not a RentalPeriod"));
        Assert.assertNotEquals(new Bike("I am not a RentalPeriod"), rp1);
    }


    //==================================================================================================================

    @Test
    public void canRentalAdjacent() {
        Bike bike = new Bike("canRentalAdjacent");

        try {
            bike.getSchedule().addPeriod(rp4);
            bike.getSchedule().addPeriod(rp5);
        } catch (IllegalArgumentException ex) {
            Assert.fail();
        }
    }

    @Test
    public void cannotRentalTwice() {
        Bike bike = new Bike("cannotRentalTwice");
        bike.getSchedule().addPeriod(rp1);

        Assert.assertFalse(bike.getSchedule().isFree(rp1));
    }

    @Test
    public void cannotRentalOverlapInside() {
        Bike bike = new Bike("cannotRentalOverlapInside");
        bike.getSchedule().addPeriod(rp1);

        Assert.assertFalse(bike.getSchedule().isFree(rp2));
    }

    @Test
    public void cannotRentalOverlapOutside() {
        Bike bike = new Bike("cannotRentalOverlapOutside");
        bike.getSchedule().addPeriod(rp1);

        Assert.assertFalse(bike.getSchedule().isFree(rp3));
    }

    @Test
    public void cannotRentalOverlapPartialBegin() {
        Bike bike = new Bike("cannotRentalOverlapPartialBegin");
        bike.getSchedule().addPeriod(rp1);

        Assert.assertFalse(bike.getSchedule().isFree(rp4));
    }

    @Test
    public void cannotRentalOverlapPartialEnd() {
        Bike bike = new Bike("cannotRentalOverlapPartialEnd");
        bike.getSchedule().addPeriod(rp1);

        Assert.assertFalse(bike.getSchedule().isFree(rp5));
    }

    @Test
    public void cannotRentalOverlapTwiceError() {
        Bike bike = new Bike("cannotRentalOverlapTwiceError");
        bike.getSchedule().addPeriod(rp1);

        try {
            bike.getSchedule().addPeriod(rp1);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
    }

    @Test
    public void cannotRentalOverlapAllDay() {
        Bike bike = new Bike("cannotRentalOverlapTwiceError");
        bike.getSchedule().addPeriod(rpAllday);
        Assert.assertFalse(bike.getSchedule().isFree(rpAllday));
        Assert.assertFalse(bike.getSchedule().isFree(rp1));
        Assert.assertFalse(bike.getSchedule().isFree(rp2));
        Assert.assertFalse(bike.getSchedule().isFree(rp3));
    }

    @Test
    public void canRentalAfterRemove() {
        Bike bike = new Bike("canRentalAfterRemove");

        try {
            bike.getSchedule().addPeriod(rp1);
            bike.getSchedule().removePeriod(rp1);
            bike.getSchedule().addPeriod(rp1);

            bike.getSchedule().removePeriod(rp1_);
            bike.getSchedule().removePeriod(rp1);

        } catch (IllegalArgumentException ex) {
            Assert.fail();
        }
    }

    //==================================================================================================================

    @Test
    public void cannotGetRentalTypeNullPeriod() {
        try {
            RentalType rt = RentalType.getRentalType(
                    null,
                    LocalDateTime.of(2019, 1, 1, 10, 0)
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
        try {
            RentalType rt = RentalType.getRentalType(
                    LocalDateTime.of(2019, 1, 1, 10, 0),
                    null
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
        try {
            RentalType rt = RentalType.getRentalType(
                    LocalDateTime.of(2019, 1, 1, 10, 0),
                    LocalDateTime.of(2019, 1, 1, 10, 0)
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
    }

    @Test
    public void getCorrectRentalType() {

        RentalType rt;

        rt = RentalType.getRentalType(
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 1, 11, 0)
        );
        Assert.assertEquals(rt.getUnit(), RentalType.RentalUnit.HOUR);

        rt = RentalType.getRentalType(
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 1, 13, 59, 59)
        );
        Assert.assertEquals(rt.getUnit(), RentalType.RentalUnit.HOUR);

        rt = RentalType.getRentalType(
                LocalDateTime.of(2019, 1, 1, 10, 1),
                LocalDateTime.of(2019, 1, 1, 14, 0)
        );
        Assert.assertEquals(rt.getUnit(), RentalType.RentalUnit.HOUR);

        rt = RentalType.getRentalType(
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 1, 14, 0)
        );
        Assert.assertEquals(rt.getUnit(), RentalType.RentalUnit.DAY);

        rt = RentalType.getRentalType(
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 2, 10, 0)
        );
        Assert.assertEquals(rt.getUnit(), RentalType.RentalUnit.DAY);

        rt = RentalType.getRentalType(
                LocalDateTime.of(2019, 1, 1, 0, 0),
                LocalDateTime.of(2019, 1, 3, 23, 59, 59)
        );
        Assert.assertEquals(rt.getUnit(), RentalType.RentalUnit.DAY);

        rt = RentalType.getRentalType(
                LocalDateTime.of(2019, 1, 1, 0, 0),
                LocalDateTime.of(2019, 1, 4, 0, 0)
        );
        Assert.assertEquals(rt.getUnit(), RentalType.RentalUnit.WEEK);

        rt = RentalType.getRentalType(
                LocalDateTime.of(2019, 1, 1, 0, 0),
                LocalDateTime.of(2019, 1, 14, 0, 0)
        );
        Assert.assertEquals(rt.getUnit(), RentalType.RentalUnit.WEEK);

    }

    //==================================================================================================================

    @Test
    public void cannotGetRentalNullPeriod() {
        Bike b = new Bike("cannotGetRentalNullPeriod");

        try {
            Rental r = new Rental(b,
                    "user",
                    null,
                    LocalDateTime.of(2019, 1, 1, 10, 0),
                    Promotion.NULL
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
        try {
            Rental r = new Rental(b,
                    "user",
                    LocalDateTime.of(2019, 1, 1, 10, 0),
                    null,
                    Promotion.NULL
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
        try {
            Rental r = new Rental(b,
                    "user",
                    LocalDateTime.of(2019, 1, 1, 10, 0),
                    LocalDateTime.of(2019, 1, 1, 10, 0),
                    Promotion.NULL
            );
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //Assert.pass()
        }
    }

    @Test
    public void correctRental() {
        Bike b = new Bike("correctRental");

        Rental r = new Rental(b,
                "user",
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 1, 12, 0),
                Promotion.NULL
        );

        Assert.assertEquals(r.totalPrice(), new BigDecimal(10).setScale(2));
    }

    @Test
    public void correctRentalFamily() {
        Bike b = new Bike("correctRentalFamily");

        Rental r = new Rental(b,
                "user",
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 1, 12, 0),
                Promotion.FAMILY
        );

        Assert.assertEquals(r.totalPrice(), new BigDecimal(7).setScale(2));
    }

    @Test
    public void correctRentalPeriods() {
        Bike b = new Bike("correctRentalPeriods");

        Rental r = new Rental(b,
                "user",
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 1, 12, 0),
                Promotion.FAMILY
        );

        Assert.assertEquals(r.totalPrice(), new BigDecimal(7).setScale(2));

        Rental r2 = new Rental(b,
                "user",
                LocalDateTime.of(2019, 1, 2, 10, 0),
                LocalDateTime.of(2019, 1, 3, 20, 0),
                Promotion.FAMILY
        );

        Assert.assertEquals(r2.totalPrice(), new BigDecimal(28).setScale(2));

        Rental r3 = new Rental(b,
                "user",
                LocalDateTime.of(2019, 1, 5, 10, 0),
                LocalDateTime.of(2019, 1, 21, 20, 0),
                Promotion.FAMILY
        );

        Assert.assertEquals(r3.totalPrice(), new BigDecimal(126).setScale(2));

    }


    @Before
    public void setUp() throws Exception {
        rp1 = new RentalPeriod(
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 1, 20, 0)
        );
        rp1_ = new RentalPeriod(
                LocalDateTime.of(2019, 1, 1, 10, 0),
                LocalDateTime.of(2019, 1, 1, 20, 0)
        );
        rp2 = new RentalPeriod(
                LocalDateTime.of(2019, 1, 1, 14, 0),
                LocalDateTime.of(2019, 1, 1, 18, 0)
        );
        rp3 = new RentalPeriod(
                LocalDateTime.of(2019, 1, 1, 8, 0),
                LocalDateTime.of(2019, 1, 1, 22, 0)
        );
        rp4 = new RentalPeriod(
                LocalDateTime.of(2019, 1, 1, 8, 0),
                LocalDateTime.of(2019, 1, 1, 12, 0)
        );
        rp5 = new RentalPeriod(
                LocalDateTime.of(2019, 1, 1, 12, 0),
                LocalDateTime.of(2019, 1, 1, 22, 0)
        );
        rpAllday = new RentalPeriod(
                LocalDateTime.of(2019, 1, 1, 0, 0),
                LocalDateTime.of(2019, 1, 2, 0, 0)
        );
    }

    @Test
    public void create() {
    }
}