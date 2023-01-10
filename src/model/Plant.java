package model;

public class Plant {
    public int id;
    public String plantName;
    public int price;
    public int quantity;

    public Plant(int id, String plantName, int price, int quantity) {
        this.id = id;
        this.plantName = plantName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return plantName + " -> " + price + " Ron; " + "Available: " + quantity;
    }

}

