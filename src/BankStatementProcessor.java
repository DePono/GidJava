import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor implements BankTransactionSummarizer, BankTransactionFilter{
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }
    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer){
        double result=0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            result=bankTransaction.summarize(result,bankTransaction);
        }
        return result;
    }
    public double calculateTotalInMonth (final Month month){
        double total=0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth()==month){
                total+=bankTransaction.getAmount();
            }
        }
        return total;
    }
    public double calculateTotalForCategory (final String category){
        double total=0;
        for (final BankTransaction bankTransaction: bankTransactions){
            if (bankTransaction.getDescription().equals(category)){
                total+=bankTransaction.getAmount();
                total+=bankTransaction.getAmount();
            }
        }
        return total;
    }
    public List<BankTransaction> findTransactionGreaterThanEqual (final int amount){
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction:bankTransactions){
            if (bankTransaction.getAmount()>=amount){
                result.add(bankTransaction);
            }
        }
        return result;
    }
    public List<BankTransaction> findTransactionsInMonth (final Month month){
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction:bankTransactions){
            if (bankTransaction.getDate().getMonth().equals(month)){
                result.add(bankTransaction);
            }
        }
        return result;
    }
    public List<BankTransaction> findTransactionsInMonthAndGreater (final int amount, final Month month){
        final List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction bankTransaction:bankTransactions){
            if (bankTransaction.getDate().getMonth().equals(month)&&bankTransaction.getAmount()>=amount){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    @Override
    public boolean test(BankTransaction bankTransaction) {
        return false;
    }

    @Override
    public double summarize(double accumulator, BankTransaction bankTransaction) {
        return 0;
    }
}
