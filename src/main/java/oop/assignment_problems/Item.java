package oop.assignment_problems;
public class Item {
    private String itemName;
    private int stock;
    public Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }
    public void restock(int stock) {
        this.stock += stock;
    }
    public void printStock() {
        System.out.println(itemName + " | Final Stock: " + stock);
    }
    public static void main(String[] args) {
        String[] itemNames = {"Rice","Wheat","Milk","Juice"};
        int[] stocks = {15,40,8,25};
        Item[] items = new Item[itemNames.length];
        for (int i = 0; i < itemNames.length; i++) {
            items[i] = new Item(itemNames[i], stocks[i]);
            items[i].restock(20);
            items[i].printStock();}
    }
}