import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzer {
    public static final String RESOURCES = "";
    private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        double total = 0d;
        for (final String line: lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total+=amount;
        }
        System.out.println("The total for all transactions is " + total);
    }
}