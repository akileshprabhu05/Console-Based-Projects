package Model;

public class LoanAccount extends Account {
    public LoanAccount(int AccountId, int balance) {
        super(AccountId, balance);
    }

    public void withdraw(int amount) {
        System.out.println("You can't withdraw from a Loan Account.");
    }

    public String toString() {
        return "Loan " + super.toString();
    }
}