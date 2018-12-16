package com.bumperjumper.android.easyinsure.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PremiumAmoutPojo {

    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal premium;

    public PremiumAmoutPojo(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        this.tax = totalAmount.multiply(BigDecimal.valueOf(0.18)).setScale(2, RoundingMode.UP);
        this.premium = totalAmount.subtract(tax).setScale(2, RoundingMode.UP);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getPremium() {
        return premium;
    }
}
