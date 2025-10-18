public interface InterestBearing {
    double calculateInterest();                 // concrete class must supply this

    default void depositMonthlyInterest() {
        double interest = calculateInterest();
        if (this instanceof Account acc && interest > 0) {
            acc.deposit(interest);
        }
    }
}