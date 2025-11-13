package items;

public class Item {
    String name;
    int cost;
    int value;
    ItemType itemType;

    public Item() {
    }

    public Item(String name, int cost, ItemType itemType, int value) {
        this.name = name;
        this.cost = cost;
        this.itemType = itemType;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", value=" + value +
                ", itemType=" + itemType +
                '}';
    }

    public Item getRandomItem(){
        Items items = new Items();
        int l=items.allItems.size();//3
        int r=(int)(Math.random()*l)+1;
        if (items.allItems.get(r) == null || items.allItems.get(r).isEmpty()) {
            return new Item("기본 아이템", 10, ItemType.WEAPON, 5);
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

    public ItemType getType() {
        return itemType;
    }

    public void setType(ItemType itemType) {
        this.itemType = itemType;
    }
}