import java.util.HashMap;
import java.util.Map;

public class Items {

    public class Item {
        String name;
        int cost;

        public Item(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    Map<Integer, Item> weapon = new HashMap<>();
    Map<Integer, Item> armor = new HashMap<>();
    Map<Integer, Item> supplies = new HashMap<>();

    public Items() {
        weapon.put(1, new Item("시작", 3));
        weapon.put(2, new Item("전사 기본무기", 5));
        weapon.put(3, new Item("마법사 기본무기", 5));
        weapon.put(4, new Item("궁수 기본무기", 5));
        weapon.put(5, new Item("전사 히든무기", 8));
        weapon.put(6, new Item("마법사 히든무기", 8));
        weapon.put(7, new Item("궁수 히든무기", 8));

        armor.put(1, new Item("전사 기본방어구", 3));
        armor.put(2, new Item("전사 기본방어구", 5));
        armor.put(3, new Item("마법사 기본무기", 5));
        armor.put(4, new Item("궁수 기본무기", 5));
        armor.put(5, new Item("전사 히든무기", 8));
        armor.put(6, new Item("마법사 히든무기", 8));
        armor.put(7, new Item("궁수 히든무기", 8));


        this.supplies = supplies;
    }
}
