package items;

import java.util.HashMap;
import java.util.Map;

public class Items {

    Map<Integer, Item> weapon = new HashMap<>();
    Map<Integer, Item> armor = new HashMap<>();
    Map<Integer, Item> supplies = new HashMap<>();

    Map<Integer, Map<Integer, Item>> allItems = new HashMap<>();

    public Items() {
        weapon.put(1, new Item("시작", 3, ItemType.WEAPON));
        weapon.put(2, new Item("전사 기본무기", 5, ItemType.WEAPON));
        weapon.put(3, new Item("마법사 기본무기", 5, ItemType.WEAPON));
        weapon.put(4, new Item("궁수 기본무기", 5, ItemType.WEAPON));
        weapon.put(5, new Item("전사 히든무기", 8, ItemType.UNIQUE));
        weapon.put(6, new Item("마법사 히든무기", 8, ItemType.UNIQUE));
        weapon.put(7, new Item("궁수 히든무기", 8, ItemType.UNIQUE));

        armor.put(1, new Item("전사 기본방어구", 3, ItemType.ARMOR));
        armor.put(2, new Item("전사 기본방어구", 5, ItemType.ARMOR));
        armor.put(3, new Item("마법사 기본방어구", 5, ItemType.ARMOR));
        armor.put(4, new Item("궁수 기본방어구", 5, ItemType.ARMOR));
        armor.put(5, new Item("전사 히든방어구", 8, ItemType.UNIQUE));
        armor.put(6, new Item("마법사 히든방어구", 8, ItemType.UNIQUE));
        armor.put(7, new Item("궁수 히든방어구", 8, ItemType.UNIQUE));

        supplies.put(1, new Item("hp 회복 물약", 2, ItemType.SUPPLY));
        supplies.put(2, new Item("mp 회복 물약", 2, ItemType.SUPPLY));

        allItems.put(1, weapon);
        allItems.put(2, armor);
        allItems.put(3, supplies);
    }


}
