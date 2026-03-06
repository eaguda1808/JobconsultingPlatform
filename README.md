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

Admin Subsystem Integration Status (UC11-UC12)

Admin subsystem backend logic is complete for the following:

* **UC11 (Approve Consultant Registration):** Service ready to approve or reject consultants. Consultant status transitions: `PENDING` → `APPROVED` or `REJECTED`.
* **UC12 (Define System Policies):** Full policy management implemented via `PolicyManager` singleton. Supports adding, updating, viewing, and removing system-wide policies (cancellation rules, refund rates, pricing strategy, notification settings).

Admin Subsystem File Structure

src/main/java/com/example/springboot/
├── model/
│   ├── Consultant.java               <-- [UC11] Consultant Entity & Registration Status
│   ├── RegistrationStatus.java       <-- [UC11] Enum: PENDING, APPROVED, REJECTED
│   └── SystemPolicy.java             <-- [UC12] Policy Entity (name, value, description)
├── service/
│   ├── AdminService.java             <-- [UC11 & UC12] Business Logic Layer
│   └── PolicyManager.java            <-- [Pattern] GoF Singleton - System Policy Store
└── controller/
    └── AdminController.java          <-- [UC11 & UC12] REST API Endpoints

Applied Design Patterns (GoF) — Admin Subsystem

3. **Singleton Pattern**: `PolicyManager` ensures only one instance of the policy store exists across the entire application. All services read from the same policy state, preventing inconsistencies in cancellation rules, refund rates, and platform fees.


To run the backend, navigate to the /backend directory and execute ./mvnw spring-boot:run.


UML DIAGRAM LINK - https://drive.google.com/drive/folders/1sLBP1VWvGi7FPfEO00Vlpjaj2igJYeDf?dmr=1&ec=wgc-drive-%5Bmodule%5D-goto 
