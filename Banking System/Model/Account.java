package Model;

public abstract class Account {
    private int AccountId;
    private int balance;

    public Account(int AccountId, int balance) {
        this.AccountId = AccountId;
        this.balance = balance;
    }

    public int getAccountId() {
        return AccountId;
    }

    public int getBalance() {
        return balance;
    }

    protected void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }

    public abstract void withdraw(int amount);

    public String toString() {
        return "Account ID: " + AccountId + ", Balance: " + balance;
    }
}
