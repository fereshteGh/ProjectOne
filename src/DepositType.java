import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by DotinSchool2 on 8/23/2015.
 */
public  class DepositType {
    private int interestRate;
    private String depositName;

    protected DepositType(int interestRate, String depositName) {
        this.interestRate = interestRate;
        this.depositName = depositName;
    }
    public int getInterestRate() {
        return interestRate;
    }
}
