package Controller;

import Service.ShopService;
import items.Item;
import items.Shop;
import user.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class ShopController {

    ShopService shopService;

    public ShopController() {
        this.shopService = new ShopService();
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

//        shopService.sell(player, shop);
    }

//    public void equipment(Player player) {
//
//        int input = 0;
//
//        while (input != -1) {
//            // player 객체의 weapon, armor 출력
//            System.out.println("**** 착용 장비 ****");
//            if (player.getWeapons().isEmpty()) System.out.println("**** 무기: 없음 ****");
//            else System.out.println("**** 무기: " + player.getWeapons().get(0).getName() + " / 능력치: " + player.getWeapons().get(0).getValue() + " ****");
//            if (player.getArmors().isEmpty()) System.out.println("**** 방어구: 없음 ****");
//            else System.out.println("**** 방어구: " + player.getArmors().get(0).getName() + "/ 능력치: " + player.getArmors().get(0).getValue() + " ****");
//
//            System.out.println("**** 나가기: -1 ****");
//            System.out.print("입력: ");
//            input = sc.nextInt();
//            sc.nextLine();
//        }
//
//    }
//
//    public void inventory(Player player) {
//
//        // while문 안에서 사용자에게 -1 받지 않는 이상
//        // player 객체의 inventory 전부 출력
//        int input = 0;
//        String equipInput = "";
//        while (input != -1) {
//            System.out.println("**** 인벤토리 ****");
//            if (player.getInventory().isEmpty()) {
//                System.out.println("**** 비어있음 ****");
//            } else {
//                for (int i = 0; i < player.getInventory().size(); i++) {
//                    System.out.println("**** " + player.getInventory().get(i).getName() + " / " + player.getInventory().get(i).getCost() + "원 / " + player.getInventory().get(i).getValue() + " ****");
//                }
//                System.out.println("**** 장비를 변경하시겠습니까? ****");
//                System.out.print("Y/N: ");
//                equipInput = sc.nextLine();
//                if (equipInput.equals("Y")) {
//                    changeEquipment(player);
//                    System.out.println("**** 장비를 변경했습니다 ****");
//                } else {
//                    System.out.println("**** 장비를 변경하지 않습니다 ****");
//                    break;
//                }
//            }
//            System.out.println("**** 나가기: -1 ****");
//            System.out.print("입력: ");
//            input = sc.nextInt();
//            sc.nextLine();
//        }
//
//    }
//
//    private void changeEquipment(Player player) {
//
//        String input = "";
//        Item equipItem = null;
//        Item invenItem = null;
//
//        System.out.println("**** 착용 가능한 장비 ****");
//        player.getInventory().stream().forEach(item -> {
//            System.out.println("**** " + item.getName() + " ****");
//        });
//        System.out.print("착용할 장비의 이름을 입력: ");
//        input = sc.nextLine();
//
//        for (int i = 0; i < player.getInventory().size(); i++) {
//            if (player.getInventory().get(i).getName().equals(input)) {
//                invenItem = player.getInventory().get(i);
//            }
//        }
//
//        player.getInventory().remove(invenItem);
//
//
//        if (input.contains("무기")) {
//            player.getWeapons().add(invenItem);
//            equipItem = player.getWeapons().get(0);
//            player.getInventory().add(equipItem);
//            player.getWeapons().remove(equipItem);
//        } else if(input.contains("방어구")) {
//            player.getArmors().add(invenItem);
//            equipItem = player.getArmors().get(0);
//            player.getInventory().add(equipItem);
//            player.getArmors().remove(equipItem);
//        } else {
//            System.out.println("**** 무기와 방어구를 제외한 아이템은 장착할 수 없습니다 ****");
//        }
//    }
}
