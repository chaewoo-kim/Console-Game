package items;

public class Item {
    String name;
    int cost;
    ItemType itemType;

    public Item(String name, int cost, ItemType itemType) {
        this.name = name;
        this.cost = cost;
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
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

    public ItemType getType() {
        return itemType;
    }

    public void setType(ItemType itemType) {
        this.itemType = itemType;
    }
}