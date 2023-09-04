package il.ac.Shenkar.CostManager.Model;

import java.math.BigDecimal;
import java.util.Date;

public class Cost {

    private int id; // Unique identifier for the cost
    private String category; // Category to which the cost belongs
    private BigDecimal sum; // The amount of the cost
    private String currency; // Currency of the cost (e.g., USD, EUR)
    private String description; // Description of the cost
    private Date date; // Date of the cost (you can use a String or a Date object)

    // Constructor
    public Cost(int id, String category, BigDecimal sum, String currency, String description, Date date) {
        setId(id);
        setCategory(category); // Use the setter method to set the category
        setSum(sum);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
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

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }



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
