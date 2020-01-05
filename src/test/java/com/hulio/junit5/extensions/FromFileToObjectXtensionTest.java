package com.hulio.junit5.extensions;

import extensions.FromFileToObjectXtension;
import extensions.LoadFromJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(FromFileToObjectXtension.class)
class FromFileToObjectXtensionTest {

    @LoadFromJson("src/test/resources/a-sample-pojo.json")
    private SamplePojo loadMe;

    @Test
    public void setValueToAnnotated() {
        Assertions.assertNotNull(loadMe);
    }
    public static class SamplePojo {
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

}