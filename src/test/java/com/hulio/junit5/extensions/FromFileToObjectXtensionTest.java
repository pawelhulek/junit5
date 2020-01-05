package com.hulio.junit5.extensions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(FromFileToObjectXtension.class)
class FromFileToObjectXtensionTest {
    @LoadFromJson("a-sample-pojo.json")
    private SamplePojo loadMe;

    @Test
    public void a() {
        Assertions.assertNotNull(loadMe);
    }

}