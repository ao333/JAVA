import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toMap;


public class SharpeScreener {

    public static void main(String[] args) throws IOException {

        // Parse CSV
        String file = "data/S&P_TSX_close.csv";
        Reader reader = Files.newBufferedReader(Paths.get(file));
        CSVParser parsed = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withIgnoreHeaderCase().withTrim());

        // Parse tickers
        Map<String, Integer> header = parsed.getHeaderMap();
        List<String> tickerList = new ArrayList<>(header.keySet());
        Map<String, Double> hashMap = new HashMap<>();

        // Iterator list
        List<CSVRecord> records = new ArrayList<>();
        for (CSVRecord record : parsed) records.add(record);

        // Iterate through each column
        for (int x = 1; x < tickerList.size(); x++) {

            // Accessing closing price by Header names
            List<Double> closeList = new ArrayList<>();
            for (CSVRecord record : records) {
                String stringClose = record.get(x);
                Double close = Double.valueOf(stringClose);
                closeList.add(close);
            }

            // Percentage change
            List<Double> pctList = new ArrayList<>();
            for (int i = 1; i < closeList.size(); i++) {
                Double pct = closeList.get(i) / closeList.get(i - 1) - 1;
                pctList.add(pct);
            }

            // Statistics
            Double sum = 0.0, var = 0.0, mean, sd, rfr, sr;
            // Mean
            for (Double num : pctList) sum += num;
            mean = sum / pctList.size();
            // Standard Deviation
            for (Double num : pctList) var += Math.pow(num - mean, 2);
            sd = Math.sqrt(var / pctList.size());
            // Risk Free Rate
            rfr = Math.pow((1 + 0.032), (1 / 12)) - 1;
            // Sharpe Ratio
            sr = Math.sqrt(12) * ((mean - rfr) / sd);

            // Add Ticker-Sharpe pairs to hashMap
            hashMap.put(tickerList.get(x), sr);
        }

        // Sort Ticker-Sharpe pairs by Sharpe
        Map<String, Double> sorted = hashMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,LinkedHashMap::new));

        // Write if Sharpe is greater than Amazon
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(
                file.substring(0,file.length()-10) + file.substring(file.length()-4)));
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Ticker","Sharpe Ratio"));
        for (Map.Entry<String, Double> entry : sorted.entrySet())
            //if(entry.getValue() >= sorted.get("AMZN")) printer.printRecord(entry.getKey(),entry.getValue());
            if(entry.getValue() >= 1.2236079936706479) printer.printRecord(entry.getKey(),entry.getValue());
        printer.flush();
    }
}