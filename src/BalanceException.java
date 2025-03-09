/**
 * Created by DotinSchool2 on 8/23/2015.
 *
 */
public class BalanceException extends Exception {
    public BalanceException() {
        super("Balance could not be negative!");
    }
}
