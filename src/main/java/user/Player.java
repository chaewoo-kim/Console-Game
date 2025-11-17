package user;

import Controller.*;
import items.Item;
import items.ItemType;
import items.Items;
import monster.Monster;

import javax.security.auth.callback.CallbackHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private int level;
    private int hp;
    private int maxHp;
    private String job;
    private String skill;
    private int mp;
    private int maxMp;
    private int cost;
    private List<Item> itemList;            // -> 고유 아이템 리스트
    private List<Item> weapons;
    private List<Item> armors;
    private List<Item> supplies;        // -> 소모품
    private List<Item> inventory; // -> 인벤토리
    private ItemController itemController;
    private PlayerController playerController;
    private WeaponController weaponController;
    private ArmorController armorController;
    private InventoryController inventoryController;
    private int result;

    Items items = new  Items();

    public Player(){}

    public Player(String name) {  // -> 1층에서는 기본적으로 모험가 직업을가짐.
        this.name = name;
        this.level = 1;
        this.hp = 100;
        this.maxHp = 100;
        this.mp = 10;
        this.maxMp = 50;
        this.job = "ADVENTURER";
        this.skill = null;
        this.cost = 0;
        this.itemList = new ArrayList<>();
        this.weapons = new ArrayList<>();
        weapons.add(items.getWeapon().get(1));
        this.armors = new ArrayList<>();
        armors.add(items.getArmor().get(1));
        this.supplies = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.itemController = new ItemController();
        this.playerController = new PlayerController();
        this.weaponController = new WeaponController();
        this.armorController = new ArmorController();
        this.inventoryController = new InventoryController();
        itemController.deleteAll("INVENTORY");
        playerController.deleteAll("PLAYER");
        weaponController.deleteAll();
        armorController.deleteAll();
    }



    public void usePotion(Player player) {
        // -> SUPPLIES 리스트에서 포션 찾기 : ItermType이 String 타입이고, getType도 String을 반환하기떄문이다.
        Item potion = inventoryController.selectByName("hp 회복 포션");

        // 포션이 있으면 사용, 없으면 메시지 출력
        if (potion != null) {
            this.hp = this.maxHp;
            inventoryController.deleteByName("hp 회복 포션");
            System.out.println(name + "이(가) " + potion.getName() + "을(를) 사용했습니다.");
            System.out.println(name + "의 HP가 " + maxHp + "로 회복되었습니다.");
            playerController.updatePlayer(player);
        } else {
            System.out.println(name + "의 인벤토리에 포션이 없습니다.");
        }
    }

    // -> 아이템 추가 메서드(무기, 장비, 소모품 구분)
    public void addItem(Item item) {
        inventory.add(item); //  -> 인벤토리 전체에 무조건 추가
        switch (item.getType()) {
            case "WEAPON":
                weapons.add(item);
                break;
            case "ARMOR":
                armors.add(item);
                break;
            case "SUPPLY":
                supplies.add(item);
                break;
            case "UNIQUE":
                itemList.add(item);
                break;
        }
    }

    public void levelUp() {     // ->  레벨업 시 최대체력과 최대마나 일부 증가 예시
        this.level += 1;
        this.maxHp += 20;
        this.maxMp += 10;
        this.hp = maxHp;
        this.mp = maxMp;
        System.out.println(name + "의 레벨이 " + level + "로 상승했습니다!");
    }

    public void Inventory() {
        System.out.println("===== " + name + "의 인벤토리 =====");
        if (inventory.isEmpty()) {
            System.out.println("인벤토리가 비어 있습니다.");
        } else {
            for (Item item : inventory) {
                System.out.println("- " + item.getName() + " (가격: " + item.getCost() + ")");
            }
        }
    }

    public void chooseJob(String newJob) {     // -> 2층에서 직업정하기.
        this.job = newJob;

        switch (newJob) {
            case "WARRIOR":
                maxHp = 130;
                maxMp = 20;
                skill = "대가리뽀사기";
                Item warriorWeapon = itemController.selectItemByName("전사 기본무기");
                Item weaponItem = weaponController.select("WEAPON");
                inventoryController.insertItem(weaponItem);
                weaponController.insertItem(warriorWeapon);
                weaponController.deleteByName(weaponItem.getName());
                System.out.println("전사 직업 전용 무기 착용: " + warriorWeapon.getName());
                break;
            case "ARCHER":
                maxHp = 110;
                maxMp = 30;
                skill = "주몽 원샷";
                Item archerWeapon = itemController.selectItemByName("궁수 기본무기");
                Item archerItem = weaponController.select("WEAPON");
                inventoryController.insertItem(archerItem);
                weaponController.insertItem(archerWeapon);
                weaponController.deleteByName(archerItem.getName());
                System.out.println("궁수 직업 전용 무기 착용: " + archerWeapon.getName());
                break;
            case "MAGE":
                maxHp = 90;
                maxMp = 50;
                skill = "아이스 에이지";
                Item mageWeapon = itemController.selectItemByName("마법사 기본무기");
                Item mageItem = weaponController.select("WEAPON");
                inventoryController.insertItem(mageItem);
                weaponController.insertItem(mageWeapon);
                weaponController.deleteByName(mageItem.getName());
                System.out.println("마법사 직업 전용 무기 착용: " + mageWeapon.getName());
                break;
            default:
                break;
        }
        hp = maxHp;
        mp = maxMp;
    }

    public void HiddenUpgrade() {       // -> 3층에서 50%확률로 히든직업을 얻을 수 있고, 스킬이 업그레이드 된다.
        Random rand = new Random();
        if (rand.nextBoolean()) { // -> 50% 확률: nextBoolean()은 true 또는 false를 랜덤으로 반환한다.

            // ->  히든직업으로 업그레이드되면 기존 착용하던 무기는 인벤토리로 들어간다.
            List<Item> currentWeapons = new ArrayList<>(weapons);
            for (Item weapon : currentWeapons) {
                unequipWeapon(weapon);
            }

            switch (this.job) {
                case "WARRIOR":
                    this.job = "DRAGON_WOO";
                    this.skill = "용의 콧물";
                    Item dragonWooWeapon = itemController.selectItemByName("드래곤 우 히든무기");
                    Item weapon = weaponController.select("WEAPON");
                    weapons.add(dragonWooWeapon);
                    weaponController.deleteAll();
                    weaponController.insertItem(dragonWooWeapon);
                    inventoryController.insertItem(weapon);
                    System.out.println("히든직업 드래곤 우로 업그레이드!");
                    System.out.println("히든직업 전용 무기 착용: " + dragonWooWeapon.getName());
                    break;
                case "ARCHER":
                    this.job = "CHAEU_CHOW";
                    this.skill = "그의 눈빛";
                    Item chaeuChowWeapon = itemController.selectItemByName("채우차우 히든무기");
                    Item weapon1 = weaponController.select("WEAPON");
                    weapons.add(chaeuChowWeapon);
                    weaponController.deleteAll();
                    weaponController.insertItem(chaeuChowWeapon);
                    inventoryController.insertItem(weapon1);
                    System.out.println("히든직업 채우차우로 업그레이드!");
                    System.out.println("히든직업 전용 무기 착용: " + chaeuChowWeapon.getName());
                    break;
                case "MAGE":
                    this.job = "LEE_SANGJUN";
                    this.skill = "배꼽 탈취";
                    Item leeSangjunWeapon = itemController.selectItemByName("이상준 히든무기");
                    Item weapon2 = weaponController.select("WEAPON");
                    weapons.add(leeSangjunWeapon);
                    weaponController.deleteAll();
                    weaponController.insertItem(leeSangjunWeapon);
                    inventoryController.insertItem(weapon2);
                    System.out.println("히든직업 개그맨 이상준으로 업그레이드!");
                    System.out.println("히든직업 전용 무기 착용: " + leeSangjunWeapon.getName());
                    break;
                default:
                    System.out.println("업그레이드 가능한 직업이 아닙니다.");
                    return;
            }
        } else {
            System.out.println("히든직업 업그레이드 실패. 기존 직업 유지.");
        }
    }

    // -> 무기 착용 해제 및 인벤토리로 되돌리기
    public void unequipWeapon(Item weapon) {
        if (weapons.remove(weapon)) {   // 착용 무기  제거
            inventory.add(weapon);      // 인벤토리로 이동
            weaponController.deleteByName(weapon.getName());
            inventoryController.insertItem(weapon);
            System.out.println(weapon.getName() + " 무기를 착용 해제하고 인벤토리로 이동했습니다.");
        } else {
            System.out.println("해당 무기를 착용하고 있지 않습니다.");
        }
    }

    // -> 방어구 착용 해제 및 인벤토리로 되돌리기
    public void unequipArmor(Item armor) {
        if (armors.remove(armor)) {
            inventory.add(armor);
            System.out.println(armor.getName() + " 방어구를 착용 해제하고 인벤토리로 이동했습니다.");
        } else {
            System.out.println("해당 방어구를 착용하고 있지 않습니다.");
        }
    }

    // -> 인벤토리에서 아이템 삭제(예: 판매, 소비 시)
    public void removeFromInventory(Item item) {
        if (inventory.remove(item)) {
            System.out.println(item.getName() + " 아이템을 인벤토리에서 삭제했습니다.");
        } else {
            System.out.println("해당 아이템이 인벤토리에 없습니다.");
        }
    }




    public void useSkill(Player player) {        //      -> 스킬을 콘솔에 출력(콘솔효과 정할 수 있음).
        if (skill != null) {
            if (mp >= cost) { // -> MP가 충분할 때만 스킬 사용 가능
                mp -= cost;   // -> MP 소모
                System.out.println(name + "의 스킬 발동: " + skill + " (MP -" + cost + ", 남은 MP: " + mp + ")");
            } else {
                System.out.println(name + "의 MP가 부족하여 스킬을 사용할 수 없습니다. (필요 MP: " + cost + ", 현재 MP: " + mp + ")");
            }
        } else {
            System.out.println("스킬 없음");
        }
        playerController.updatePlayer(player);
    }

    public void printStatus() {     // -> 플레이어의 현재 상태(닉네임, 레벨, hp, mp, 직업, 스킬)를 알 수 있음.
        System.out.println("닉네임: " + name);
        System.out.println("LEVEL: " + level);
        System.out.println("HP : " + hp + "/" + maxHp);
        System.out.println("MP : " + mp + "/" + maxMp);
        System.out.println("직업: " + job);
        if (skill != null) {
            System.out.println("스킬: " + skill);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    // -> item getter, setter

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Item> weapons) {
        this.weapons = weapons;
    }

    public List<Item> getArmors() {
        return armors;
    }

    public void setArmors(List<Item> armors) {
        this.armors = armors;
    }
    public List<Item> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Item> supplies) {
        this.supplies = supplies;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public void takeDamage(Monster monster, Player player) {
        if (player.getJob().equals("ADVENTURER")) {
            return;
        }

        int damage = monster.getDamage() - player.getArmors().get(0).getValue();

        player.setHp(player.getHp() - damage);
    }


    public boolean isHavePotion(Player player){
        List<Item> litem=player.getInventory();
        for(Item item:litem){
            if("SUPPLY".equals(item.getType())){
                litem.remove(item);
                return true;
            }
        }
        return false;
    }
}
