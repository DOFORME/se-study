package org.lc.se.time;

import org.junit.jupiter.api.Test;

import java.time.Instant;

public class TimeTest {

    @Test
    void testInstant() {
        Instant instant = Instant.now();
        System.out.println(instant.getNano());
    }
}
