package Controller;

import Service.ShopService;
import items.Item;
import items.Shop;
import user.Player;

import java.util.*;

public class ShopController {

    ShopService shopService;
    Scanner sc = new Scanner(System.in);
    InventoryController inventoryController;
    WeaponController weaponController;
    ArmorController armorController;

    public ShopController() {

        this.shopService = new ShopService();
        this.inventoryController = new InventoryController();
        this.weaponController = new WeaponController();
        this.armorController = new ArmorController();
    }

    public void buy(Player player, Shop shop) {

        if (shop.isBuy()) {
            System.out.println("**** 상점 구매는 한 번만 이용 가능합니다 ****");
            return;
        } else {
            shop.setBuy(true);
        }

        shopService.buy(player, shop);
    }

    public void sell(Player player, Shop shop) {

        if (shop.isSell()) {
            System.out.println("**** 상점 판매는 한 번만 가능합니다 ****");
            return;
        } else {
            shop.setSell(true);
        }

        shopService.sell(player, shop);
    }

   public void equipment(Player player, Shop shop) {

       int input = 0;
       Item weapon = weaponController.select("WEAPON");
       Item armor = weaponController.select("ARMOR");
       while (input != -1) {
           // player 객체의 weapon, armor 출력
           System.out.println("************ 착용 장비 ************");
           if (player.getWeapons().isEmpty()) System.out.println("**** 무기: 없음 ****");
           else System.out.println("**** 무기: " + weapon.getName() + " / 능력치: " + weapon.getValue() + " ****");
           if (player.getArmors().isEmpty()) System.out.println("**** 방어구: 없음 ****");
           else System.out.println("**** 방어구: " + armor.getName() + "/ 능력치: " + armor.getValue() + " ****");

           System.out.println("*********** 나가기: -1 ***********");
           System.out.print("입력: ");
           input = sc.nextInt();
           sc.nextLine();
       }
   }

   public void inventory(Player player, Shop shop) {

       int input = 0;
       List<Item> inventoryList = inventoryController.selectAll();

       String equipInput = "";
       while (input != -1) {
           System.out.println("******** 인벤토리 ********");
           if (inventoryList.isEmpty()) {
               System.out.println("******** 비어있음 ********");
           } else {
               for (int i = 0; i < inventoryList.size(); i++) {
//                   if (inventoryList.get(i).getName().equals(player.getWeapons().get(0).getName()) || inventoryList.get(i).getName().equals(player.getArmors().get(0).getName())) {
//                       continue;
//                   }
                   System.out.println("******** " + inventoryList.get(i).getName() + " / " + inventoryList.get(i).getCost() + "원 / " + inventoryList.get(i).getValue() + " ********");
               }
               System.out.println("******** 장비를 변경하시겠습니까? ********");
               System.out.print("Y/N: ");
               equipInput = sc.nextLine();
               if (equipInput.equals("Y")) {
                   changeEquipment(player);
                   System.out.println("******** 장비를 변경했습니다 ********");
               } else {
                   System.out.println("******** 장비를 변경하지 않습니다 ********");
                   break;
               }
           }
           System.out.println("******** 나가기: -1 ********");
           input = sc.nextInt();
           sc.nextLine();
       }

   }

   private void changeEquipment(Player player) {

       String input = null;
       Item equipItem = null;
       Item invenItem = null;
       List<Item> inventoryList = inventoryController.selectAll();

       System.out.println("******** 착용 가능한 장비 ********");
       inventoryList.stream().forEach(item -> {
           System.out.println("********** " + item.getName() + " **********");
       });
       System.out.print("착용할 장비의 이름을 입력: ");
       input = sc.nextLine();

       for (int i = 0; i < inventoryList.size(); i++) {
           if (inventoryList.get(i).getName().equals(input)) {
               invenItem = inventoryList.get(i);
           }
       }

       if (invenItem == null) {
           System.out.println("**** 잘못된 이름입니다. 장비를 변경하지 않습니다 ****");
           return;
       }

       inventoryController.deleteByName(invenItem.getName());

       if (input.contains("무기")) {
           equipItem = weaponController.select("WEAPON");
           weaponController.deleteByName(equipItem.getName());
           inventoryController.insertItem(equipItem);   // 인벤에 안 들어가고 계속 장비에 쌓이는거 해결해야함
           weaponController.insertItem(invenItem);
       } else if(input.contains("방어구")) {
           equipItem = weaponController.select("ARMOR");
           armorController.deleteByName(equipItem.getName());
           inventoryController.insertItem(equipItem);
           armorController.insertItem(invenItem);
       } else {
           System.out.println("**** 무기와 방어구를 제외한 아이템은 장착할 수 없습니다 ****");
       }
   }
}