package Model;

public class SavingsAccount extends Account {
    public SavingsAccount(int AccountId, int balance) {
        super(AccountId, balance);
    }

    public void withdraw(int amount) {
        if (amount > 0 && getBalance() >= amount) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    public String toString() {
        return "Savings " + super.toString();
    }
}