package items;

import Controller.ItemController;

import java.util.HashMap;
import java.util.Map;

public class Items {

    int id = 0;

    Map<Integer, Item> weapon = new HashMap<>();
    Map<Integer, Item> armor = new HashMap<>();
    Map<Integer, Item> supplies = new HashMap<>();

    Map<Integer, Map<Integer, Item>> allItems = new HashMap<>();
    ItemController itemController = new ItemController();

    public Items() {
        weapon.put(1, new Item(1, "매콤한 주먹", 3, "WEAPON", 5));
        weapon.put(2, new Item(2,"전사 기본무기", 5, "WEAPON", 10));
        weapon.put(3, new Item(3, "마법사 기본무기", 5, "WEAPON", 10));
        weapon.put(4, new Item(4, "궁수 기본무기", 5, "WEAPON", 10));
        weapon.put(5, new Item(5, "전사 히든무기", 8, "WEAPON", 20));
        weapon.put(6, new Item(6, "마법사 히든무기", 8, "WEAPON", 20));
        weapon.put(7, new Item(7, "궁수 히든무기", 8, "WEAPON", 20));

        armor.put(1, new Item(8, "담백한 상의", 3, "ARMOR", 5));
        armor.put(2, new Item(9, "전사 기본방어구", 5, "ARMOR", 10));
        armor.put(3, new Item(10, "마법사 기본방어구", 5, "ARMOR", 10));
        armor.put(4, new Item(11, "궁수 기본방어구", 5, "ARMOR", 10));
        armor.put(5, new Item(12, "전사 히든방어구", 8, "UNIQUE", 20));
        armor.put(6, new Item(13, "마법사 히든방어구", 8, "UNIQUE", 20));
        armor.put(7, new Item(14, "궁수 히든방어구", 8, "UNIQUE", 20));

        supplies.put(1, new Item(15, "hp 회복 물약", 2, "SUPPLY", 20));
        supplies.put(2, new Item(16, "mp 회복 물약", 2, "SUPPLY", 20));

        allItems.put(1, weapon);
        allItems.put(2, armor);
        allItems.put(3, supplies);

        itemController.insertItem(new Item(++id, "매콤한 주먹", 3, "WEAPON", 5));
        itemController.insertItem(new Item(++id,"전사 기본무기", 5, "WEAPON", 10));
        itemController.insertItem(new Item(++id, "마법사 기본무기", 5, "WEAPON", 10));
        itemController.insertItem(new Item(++id, "궁수 기본무기", 5, "WEAPON", 10));
        itemController.insertItem(new Item(++id, "전사 히든무기", 8, "WEAPON", 20));
        itemController.insertItem(new Item(++id, "마법사 히든무기", 8, "WEAPON", 20));
        itemController.insertItem(new Item(++id, "궁수 히든무기", 8, "WEAPON", 20));

        itemController.insertItem(new Item(++id, "담백한 상의", 3, "ARMOR", 5));
        itemController.insertItem(new Item(++id, "전사 기본방어구", 5, "ARMOR", 10));
        itemController.insertItem(new Item(++id, "마법사 기본방어구", 5, "ARMOR", 10));
        itemController.insertItem(new Item(++id, "궁수 기본방어구", 5, "ARMOR", 10));
        itemController.insertItem(new Item(++id, "전사 히든방어구", 8, "UNIQUE", 20));
        itemController.insertItem(new Item(++id, "마법사 히든방어구", 8, "UNIQUE", 20));
        itemController.insertItem(new Item(++id, "궁수 히든방어구", 8, "UNIQUE", 20));

        itemController.insertItem(new Item(++id, "hp 회복 물약", 2, "SUPPLY", 20));
        itemController.insertItem(new Item(++id, "mp 회복 물약", 2, "SUPPLY", 20));
        itemController.insertItem(new Item(++id, "카드키", 0, "UNIQUE", 0));

        itemController.insertItem(new Item(++id, "드래곤 우 히든무기", 15, "UNIQUE", 35));
        itemController.insertItem(new Item(++id, "채우차우 히든무기", 15, "UNIQUE", 35));
        itemController.insertItem(new Item(++id, "이상준 히든무기", 15, "UNIQUE", 35));

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
