
public class BankAccount {
	
	    private Integer balance;
	    
	    // Create a constructor
	   // public BankAccount(Integer initialBalance) {
	       // balance = initialBalance;
	    //}

		public BankAccount(Integer initialBalance) {
			balance = initialBalance;
			// TODO Auto-generated constructor stub
		}

		// Add method to calculate
	    // balance amount after withdrawal
	    public Integer withdraw(Integer amount) {
	        if (balance < amount) {
	            throw new NotEnoughFundsException(amount, balance);
	        }
	        balance -= amount;
	        return balance;
	    }
	}


