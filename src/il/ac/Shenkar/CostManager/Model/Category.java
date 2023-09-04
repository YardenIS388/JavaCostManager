package il.ac.Shenkar.CostManager.Model;

public class Category {
    private String name;

    // Constructor using setters
    public Category(String name) {
        setName(name);
    }

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
