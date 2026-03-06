package com.example.springboot.observer;

import com.example.springboot.model.Booking;

public class ClientNotificationObserver implements BookingObserver {

    @Override
    public void update(Booking booking) {
        System.out.println(
            "Notification sent to client " + booking.getClientId() +
            ": Booking " + booking.getId() +
            " is now " + booking.getState().getLabel()
        );
    }
}