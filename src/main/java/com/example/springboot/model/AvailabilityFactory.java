package com.example.springboot.model;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component; 
@Component
public class AvailabilityFactory {

    public static Availability create(LocalDateTime start,
                                      LocalDateTime end) {

        Availability availability = new Availability();
        availability.setStartTime(start);
        availability.setEndTime(end);
        availability.setStatus(AvailabilityStatus.AVAILABLE);

        return availability;
    }
}
