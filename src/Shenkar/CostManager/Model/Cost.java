package Shenkar.CostManager.Model;

import java.math.BigDecimal;

public class Cost {

    private int id; // Unique identifier for the cost
    private String category; // Category to which the cost belongs
    private BigDecimal sum; // The amount of the cost
    private String currency; // Currency of the cost (e.g., USD, EUR)
    private String description; // Description of the cost
    private String date; // Date of the cost (you can use a String or a Date object)

    // Constructor
    public Cost(int id, String category, BigDecimal sum, String currency, String description, String date) {
        this.id = id;
        this.category = category;
        this.sum = sum;
        this.currency = currency;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public String toString() {
        /**
         *   toString method to display the cost's information
         */
        return "Cost{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
