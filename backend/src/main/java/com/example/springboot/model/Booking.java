package com.example.springboot.model;

import com.example.springboot.observer.BookingObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Booking {

    private Long id;
    private Long clientId;
    private Consultant consultant;
    private LocalDateTime sessionTime;
    private double basePrice;

    private BookingState state;

    private List<BookingObserver> observers = new ArrayList<>();


    public Booking(Long id, Long clientId, Consultant consultant,
                   LocalDateTime sessionTime, double basePrice) {

        this.id = id;
        this.clientId = clientId;
        this.consultant = consultant;
        this.sessionTime = sessionTime;
        this.basePrice = basePrice;

        this.state = BookingState.REQUESTED;
    }


    // Observer methods
    public void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BookingObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (BookingObserver observer : observers) {
            observer.update(this);
        }
    }


    // Getters
    public Long getId() { return id; }

    public Long getClientId() { return clientId; }

    public Consultant getConsultant() { return consultant; }

    public BookingState getState() { return state; }

    public double getBasePrice() { return basePrice; }


    // Lifecycle actions

    public void confirm() {
        if (state != BookingState.REQUESTED) {
            throw new IllegalStateException("Booking already processed: " + state);
        }

        state = BookingState.CONFIRMED;
        notifyObservers();
    }


    public void reject() {
        if (state != BookingState.REQUESTED) {
            throw new IllegalStateException("Booking already processed: " + state);
        }

        state = BookingState.REJECTED;
        notifyObservers();
    }


    public void markPendingPayment() {
        if (state != BookingState.CONFIRMED) {
            throw new IllegalStateException("Booking must be confirmed first");
        }

        state = BookingState.PENDING_PAYMENT;
        notifyObservers();
    }


    public void markPaid() {
        if (state != BookingState.PENDING_PAYMENT) {
            throw new IllegalStateException("Booking must be pending payment");
        }

        state = BookingState.PAID;
        notifyObservers();
    }


    public void cancel() {
        if (state == BookingState.COMPLETED) {
            throw new IllegalStateException("Completed booking cannot be cancelled");
        }

        state = BookingState.CANCELLED;
        notifyObservers();
    }


    public void complete() {
        if (state != BookingState.PAID) {
            throw new IllegalStateException("Booking must be paid before completion");
        }

        state = BookingState.COMPLETED;
        notifyObservers();
    }


    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", state=" + state.getLabel() +
                ", consultant=" + consultant.getName() +
                ", time=" + sessionTime +
                '}';
    }
}