package items;

import java.util.HashMap;
import java.util.Map;

public class Items {

    Map<Integer, Item> weapon = new HashMap<>();
    Map<Integer, Item> armor = new HashMap<>();
    Map<Integer, Item> supplies = new HashMap<>();

    Map<Integer, Map<Integer, Item>> allItems = new HashMap<>();

    public Items() {
        weapon.put(1, new Item("매콤한 주먹", 3, ItemType.WEAPON, 5));
        weapon.put(2, new Item("전사 기본무기", 5, ItemType.WEAPON, 10));
        weapon.put(3, new Item("마법사 기본무기", 5, ItemType.WEAPON, 10));
        weapon.put(4, new Item("궁수 기본무기", 5, ItemType.WEAPON, 10));
        weapon.put(5, new Item("전사 히든무기", 8, ItemType.UNIQUE, 20));
        weapon.put(6, new Item("마법사 히든무기", 8, ItemType.UNIQUE, 20));
        weapon.put(7, new Item("궁수 히든무기", 8, ItemType.UNIQUE, 20));

        armor.put(1, new Item("담백한 상의", 3, ItemType.ARMOR, 5));
        armor.put(2, new Item("전사 기본방어구", 5, ItemType.ARMOR, 10));
        armor.put(3, new Item("마법사 기본방어구", 5, ItemType.ARMOR, 10));
        armor.put(4, new Item("궁수 기본방어구", 5, ItemType.ARMOR, 10));
        armor.put(5, new Item("전사 히든방어구", 8, ItemType.UNIQUE, 20));
        armor.put(6, new Item("마법사 히든방어구", 8, ItemType.UNIQUE, 20));
        armor.put(7, new Item("궁수 히든방어구", 8, ItemType.UNIQUE, 20));

        supplies.put(1, new Item("hp 회복 물약", 2, ItemType.SUPPLY, 20));
        supplies.put(2, new Item("mp 회복 물약", 2, ItemType.SUPPLY, 20));

        allItems.put(1, weapon);
        allItems.put(2, armor);
        allItems.put(3, supplies);
    }

    public Map<Integer, Item> getWeapon() {
        return weapon;
    }

    public void setWeapon(Map<Integer, Item> weapon) {
        this.weapon = weapon;
    }

    public Map<Integer, Item> getArmor() {
        return armor;
    }

    public void setArmor(Map<Integer, Item> armor) {
        this.armor = armor;
    }

    public Map<Integer, Item> getSupplies() {
        return supplies;
    }

    public void setSupplies(Map<Integer, Item> supplies) {
        this.supplies = supplies;
    }

    public Map<Integer, Map<Integer, Item>> getAllItems() {
        return allItems;
    }

    public void setAllItems(Map<Integer, Map<Integer, Item>> allItems) {
        this.allItems = allItems;
    }
}
