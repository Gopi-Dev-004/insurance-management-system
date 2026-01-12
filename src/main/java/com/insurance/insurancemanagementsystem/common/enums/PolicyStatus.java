package com.insurance.insurancemanagementsystem.common.enums;

public enum PolicyStatus
{
    // Before activation
    PENDING_PAYMENT,
    PAYMENT_FAILED,
    PAYMENT_EXPIRED,

    // After activation
    ACTIVE,
    EXPIRED,
    CANCELLED,
    LAPSED
}
