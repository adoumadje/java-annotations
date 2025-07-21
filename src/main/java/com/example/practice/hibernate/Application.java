package com.example.practice.hibernate;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class Application {
    private static Hibernate<TransactionHistory> hibernate;

    public static void main(String[] args) throws SQLException {
        hibernate = new Hibernate<>();

        TransactionHistory history = new TransactionHistory("Valer Germain",
                "Credit Card", 50_000.0);
        TransactionHistory history1 = new TransactionHistory("Eduardo Camavinga",
                "Debit card", 400.0);
        TransactionHistory history2 = new TransactionHistory("Cole Palmer",
                "Gift Card", 159.0);
        TransactionHistory history3 = new TransactionHistory("Desire Doue",
                "Black Card", 200_000.0);

        List<TransactionHistory> histories = List.of(history, history1, history2, history3);

//        saveHistories(histories);

        readHistory(1L);

    }

    private static void readHistory(Long id) {
        try {
            TransactionHistory fromDb = hibernate.read(TransactionHistory.class, id);
            System.out.println(fromDb);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                 | IllegalAccessException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveHistories(List<TransactionHistory> histories) {
        for(TransactionHistory history: histories) {
            try {
                hibernate.write(history);
            } catch (SQLException | NoSuchMethodException | IllegalAccessException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
