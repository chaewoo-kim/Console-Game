package items;

import Controller.ItemController;

public class Item {
    int id;
    String name;
    int cost;
    int value;
    String itemType;
    ItemController itemController;

    public Item() {
    }

    public Item(String name, int cost, String itemType, int value) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.itemType = itemType;
    }

    public Item(int id, String name, int cost, String itemType, int value) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.itemType = itemType;
        this.value = value;
        this.itemController = new ItemController();
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", value=" + value +
                ", itemType='" + itemType + '\'' +
                '}';
    }

    public Item getRandomItem(){
        Items items = new Items();
        int l=items.allItems.size();//3
        int r=(int)(Math.random()*l)+1;
        if (items.allItems.get(r) == null || items.allItems.get(r).isEmpty()) {
            return itemController.selectItemByName("매콤한 주먹");
        }
        
        int rr=1+(int)(Math.random()*(items.allItems.get(r).size()));
        return items.allItems.get(r).get(rr);  // Fixed: use rr instead of r
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return itemType;
    }

    public void setType(String itemType) {
        this.itemType = itemType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}