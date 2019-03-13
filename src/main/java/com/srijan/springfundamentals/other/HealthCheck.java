package com.srijan.springfundamentals.other;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check();
        if(errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code " , errorCode)
                    .build();
        }
        return Health.up().build();
    }

    public int check() {
        System.out.println("-- Checking health --");
        return 0;
    }
}
