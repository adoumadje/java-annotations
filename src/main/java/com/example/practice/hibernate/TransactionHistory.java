package com.example.practice.hibernate;


@Table(name = "transaction_history")
public class TransactionHistory {
    @PrimaryKey(name = "id")
    private Long transactionID;
    @Column(name = "name")
    private String name;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "amount")
    private Double amount;

    public TransactionHistory() {

    }

    public TransactionHistory(String name, String cardType, Double amount) {
        this.name = name;
        this.cardType = cardType;
        this.amount = amount;
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "transactionID=" + transactionID +
                ", name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
