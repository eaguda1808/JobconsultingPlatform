src/main/java/com/example/springboot/
├── model/
│   └── PaymentRequest.java        <-- [UC6] Secure Data Storage / DTOs
├── strategy/
│   ├── PaymentStrategy.java       <-- [Pattern] GoF Strategy Interface
│   ├── CreditCardStrategy.java    <-- [UC5] Card Validation Logic
│   ├── PayPalStrategy.java        <-- [UC5] PayPal Integration Logic
│   └── BankTransferStrategy.java  <-- [UC5] Bank Transfer Logic
├── service/
│   ├── PaymentService.java        <-- [UC5] Payment Workflow Orchestration
│   ├── PaymentMethodService.java  <-- [UC6] Payment Method CRUD Management
│   └── BookingHistoryService.java <-- [UC4] Transaction History & Audit Logic
└── repository/
    └── BookingRepository.java     <-- Data Access Layer (JPA/Hibernate)


    ### 🛡️ Payment Subsystem Integration Status (UC4-UC7)
I have completed the backend logic for the following:
* **UC4 (Booking History):** Service ready to fetch from `BookingRepository`.
* **UC5 (Payment Processing):** **Strategy Pattern** implemented for Credit/Debit, PayPal, and Bank Transfer. Includes 2-3s simulation delay.
* **UC6 (Manage Methods):** CRUD operations for saved payment profiles.
* **UC7 (Payment History):** Tracking for successful/pending payments and refunds.

**Note to UC1-UC3 devs:** My `PaymentService` is ready to receive your `Booking` objects. Just ensure your `Booking` entity has a `setStatus()` method so I can transition it to `PAID` after the simulation.

### Applied Design Patterns (GoF)

1. **Strategy Pattern**: Used to encapsulate different validation and processing algorithms for Credit Card, PayPal, and Bank Transfers. This allows for easy extension of new payment methods without modifying the core service.
2. **Factory Pattern**: Implemented `PaymentStrategyFactory` to decouple the creation and selection of payment strategies from the `PaymentService`. This centralizes the strategy lookup logic.


To run the backend, navigate to the /backend directory and execute ./mvnw spring-boot:run.