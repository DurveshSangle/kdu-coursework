package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static Logging log = new Logging();

    public static void main(String[] args) {
        String filePath = "src/main/resources/small_transaction.json";
        try{
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            CountDownLatch latch = new CountDownLatch(jsonNode.size());
            executeTransactions(jsonNode, latch);
        }
        catch (IOException e) {
            // Handle IOException (e.g., file not found, read error)
            e.printStackTrace();
        }
    }

    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) {
        try{
            Path coinsFilePath = Path.of("src/main/resources/coins.csv");
            Path tradersFilePath = Path.of("src/main/resources/traders.csv");
            List<String[]> coinsData = parseCSV(coinsFilePath);
            List<String[]> tradersData = parseCSV(tradersFilePath);
            Market market = new Market(coinsData,tradersData);
            Transaction[] transactionList = objectMapper.treeToValue(jsonTransactions, Transaction[].class);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for(Transaction transaction:transactionList){
                executorService.execute(new ExecuteTransaction(transaction,latch,market));
            }
            shutDownExecutor(executorService,latch);
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public static List<String[]> parseCSV(Path filePath){
        ArrayList<String[]> csvData = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            boolean isFirstRecord = true;
            for (CSVRecord csvRecord : csvParser) {
                if(isFirstRecord) {
                    isFirstRecord = false;
                    continue;
                }
                String[] row = new String[csvRecord.size()];
                for (int i = 0; i < csvRecord.size(); i++) {
                    row[i] = csvRecord.get(i);
                }
                csvData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }
    public static JsonNode parseJsonFile(String filePath) throws IOException{
        return objectMapper.readTree(new File(filePath));
    }
    public static void shutDownExecutor(ExecutorService executorService, CountDownLatch latch){
        try {
            // org.example.Main thread waits until the count reaches zero
            latch.await();
            log.logInfo("All tasks have completed.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            // Shutdown the ExecutorService
            executorService.shutdown();
        }
    }

}


