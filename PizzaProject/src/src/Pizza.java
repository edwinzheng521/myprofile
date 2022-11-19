package sample;

public class Pizza {
    private String name;
    private String size;
    private String topping;

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public Pizza(){

    }

    public Pizza(String name, String size, double price){
        this.name = name;
        this.size = size;
        this.price = price;
    }
}
