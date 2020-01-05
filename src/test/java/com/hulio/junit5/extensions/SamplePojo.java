package com.hulio.junit5.extensions;

public class SamplePojo {
    private final String name;
    private final int amount;

    public SamplePojo(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
