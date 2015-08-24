import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by fereshteh ghaffari on 8/23/2015.
 */
public  class Deposit implements Comparable<Deposit>{

    private BigDecimal balance;
    private int duration;
    private String customerNumber;
    private BigDecimal payedInterest;
    private DepositType depositType;

    public Deposit(BigDecimal balance, int duration, String customerNumber, DepositType depositType) {
        this.balance = balance;
        this.duration = duration;
        this.customerNumber = customerNumber;
        this.depositType = depositType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BigDecimal getPayedInterest(){
        return payedInterest;
    }

    public void setPayedInterest(BigDecimal payedInterest){
        this.payedInterest = payedInterest;
    }

    public  void calculatePaiedInterest(){
        payedInterest = balance.multiply(BigDecimal.valueOf(depositType.getInterestRate()*duration));
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "balance=" + balance +
                ", duration=" + duration +
                ", customerNumber=" + customerNumber +
                ", payedInterest=" + payedInterest +
                '}' + "\n";

    }

    @Override
    public int compareTo(Deposit o) {
        return o.getPayedInterest().compareTo( this.getPayedInterest());
    }
}
