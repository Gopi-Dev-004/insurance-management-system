package com.insurance.insurancemanagementsystem.common.enums;

public enum SeatingCapacity {

    TWO(2),
    FIVE(5),
    SEVEN(7);
    private final int value;

    SeatingCapacity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
