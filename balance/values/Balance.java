package balance.values;

public class Balance {

	private double balance = 0.0;

	public Balance() {

	}

	public Balance(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void addValues(double value) {
		this.balance += value;
	}

	public void removeValue(double value) {
		this.balance -= value;
	}

	public String toString() {
		return String.format("TOTAL EM CAIXA:  R$ %.2f", balance);
	}

}
