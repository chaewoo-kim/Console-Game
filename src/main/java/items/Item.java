package items;

public class Item {
    String name;
    int cost;
    ItemType itemType;

    public Item() {
    }

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

    public Item getRandomItem(){
        Items items = new Items();
        int l=items.allItems.size();//3
        int r=(int)(Math.random()*l);//0~3
        int rr=(int)(Math.random()*(items.allItems.get(r).size()));
        return items.allItems.get(r).get(r);
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