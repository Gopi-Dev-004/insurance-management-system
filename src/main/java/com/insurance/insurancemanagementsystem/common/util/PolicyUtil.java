package com.insurance.insurancemanagementsystem.common.util;

import com.insurance.insurancemanagementsystem.common.enums.PolicyDuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class PolicyUtil {

    private static final BigDecimal THREE_YEAR_MULTIPLIER = BigDecimal.valueOf(3);
    private static final BigDecimal THREE_YEAR_DISCOUNT_PERCENT = BigDecimal.valueOf(10);

    private PolicyUtil() {
    }

    public static BigDecimal calculateFinalPremium(
            PolicyDuration policyDuration,
            BigDecimal yearlyPremium) {

        if (policyDuration == null) {
            throw new IllegalArgumentException("Policy duration must not be null");
        }

        if (yearlyPremium == null || yearlyPremium.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid premium amount");
        }

        switch (policyDuration) {

            case ONE_YEAR:
                return yearlyPremium;

            case THREE_YEARS:
                return calculateThreeYearPremium(yearlyPremium);

            default:
                throw new IllegalArgumentException("Unsupported policy duration");
        }
    }

    private static BigDecimal calculateThreeYearPremium(BigDecimal yearlyPremium) {

        BigDecimal threeYearPremium =
                yearlyPremium.multiply(THREE_YEAR_MULTIPLIER);

        BigDecimal discount =
                threeYearPremium
                        .multiply(THREE_YEAR_DISCOUNT_PERCENT)
                        .divide(BigDecimal.valueOf(100));

        return threeYearPremium.subtract(discount);
    }


    // for calculating car depreciation

    public static int calculateCarAge(LocalDate registrationDate) {

        if (registrationDate == null) {
            throw new IllegalArgumentException("Vehicle registration date must not be null");
        }

        if (registrationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Vehicle registration date cannot be in future");
        }

        return (int) ChronoUnit.YEARS.between(registrationDate, LocalDate.now());
    }

    public static int normalizeDepreciationAge(int carAge) {

        if (carAge < 0) {
            throw new IllegalArgumentException("Car age cannot be negative");
        }

        return carAge <= 5 ? carAge : 10;
    }

}

