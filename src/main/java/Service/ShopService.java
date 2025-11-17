package Service;

import Controller.*;
import Repository.InventoryRepository;
import Repository.ItemRepository;
import Repository.ShopRepository;
import items.Item;
import items.Items;
import items.Shop;
import user.Player;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class ShopService {

    ItemController  itemController = new ItemController();
    PlayerController playerController = new PlayerController();
    InventoryController inventoryController = new InventoryController();
    ArmorController armorController = new ArmorController();
    WeaponController weaponController = new WeaponController();
    Scanner sc = new Scanner(System.in);

    public void buy(Player player, Shop shop) {

        Connection con = getConnection();

        try {

            int categoryNum = 0;
            int itemNum = 0;
            int money = player.getCost();   // db에서 꺼내오는걸로 수정
            int input = -1;
            String itemName = "";
            List<Item> itemForBuy = new LinkedList<>();
            List<Item> allItemList = itemController.selectAllItems();
            Set<Integer> hashSet = new HashSet<>();
            Item target = null;

            while (hashSet.size() < 4) {
                hashSet.add((int)(Math.random() * allItemList.size()) + 1);
            }

            hashSet.stream().forEach(index -> {
                itemForBuy.add(itemController.selectItemByIndex(index));
            });

            System.out.println(shop.getBuyOutput());

            while (input != 0) {

                if (itemForBuy.isEmpty()) {
                    System.out.println("**** 구매할 수 있는 물건 없음 ****");
                    break;
                }
                System.out.println("현재 보유 금액: " + player.getCost());

                for (int i = 0; i < itemForBuy.size(); i++) {
                    System.out.println("****" +
                            "번호: " + i + " / " +
                            "이름: " + itemForBuy.get(i).getName() + " / " +
                            "가격: " + itemForBuy.get(i).getCost() + " / " +
                            "능력치: " + itemForBuy.get(i).getValue()
                    );
                }
                System.out.println("****번호: -1 / 구매 종료");
                System.out.println("****");

                try {

                    System.out.print("구매할 물건의 이름을 입력: ");
                    itemName = sc.nextLine();

                    if (itemName.equals("-1")) return;

//                    target = itemController.selectItemByName(itemName);

                    for (Item item : itemForBuy) {
                        if (item.getName().equals(itemName)) {
                            target = item;
                        }
                    }

                    if (target == null) {
                        System.out.println("**** 잘못 입력함 ****");
                        return;
                    }

                    if (target.getCost() > money) {
                        System.out.println("**** 금액 부족 ****");
                        return;
                    } else {
                        System.out.println("**** 구매 완료 ****");
                        player.setCost(player.getCost() -  target.getCost());
                        playerController.updatePlayer(player);
                        inventoryController.insertItem(target);
                        if (itemForBuy.remove(target)) {
                            System.out.println("삭제완료");
                        }

                    }

                } catch(IndexOutOfBoundsException e) {
                    System.out.println("올바른 값을 입력하시오");
                } catch(InputMismatchException e) {
                    System.out.println("올바른 값을 입력하시오");
                }

                System.out.println("**** 계속 구매: 1 / 구매 종료: 0 ****");
                System.out.print("입력: ");
                input = sc.nextInt();
                sc.nextLine();
            }

        } finally {
            close(con);
        }
    }

    public void sell(Player player, Shop shop) {

        int input = -1;
        int itemInput = 0;
        int categoryNum = 0;
        int itemNum = 0;
        boolean isNoWeapon = true;

        while (input != 0) {

            List<Item> playerInventoryList = inventoryController.selectAll();
            Item nowWeapon = weaponController.select("WEAPON");
            Item nowArmor = weaponController.select("ARMOR");
            playerInventoryList.stream().forEach(item -> {
                System.out.println(item.toString());
            });
            int money = player.getCost();

            if (playerInventoryList.isEmpty()) {
                System.out.println("**** 판매할 물건 없음 ****");
                break;
            }

            System.out.println("현재 돈: " + money);

            for (int i = 0; i < playerInventoryList.size(); i++) {
                    System.out.println(
                            " / 이름: " + playerInventoryList.get(i).getName() +
                            " / 가격: " + playerInventoryList.get(i).getCost() +
                            " / 능력치: "  + playerInventoryList.get(i).getValue()
                    );
                    System.out.println("=========================");
            }
            System.out.println("번호: -1 / 판매 종료");

            System.out.print("판매할 아이템의 이름: ");
            final String itemName = sc.nextLine();

            if (itemName.equals("-1")) break;

            if (nowWeapon == null) {
                System.out.println("**** 판매 후 교체할 무기가 없습니다 ****");
            } else if (nowArmor == null) {
                System.out.println("**** 판매 후 교체할 방어구가 없습니다 ****");
            }

            // 사용자 돈 증가
            playerInventoryList.stream().forEach(item -> {
                if (item.getName().equals(itemName)) {
                    player.setCost(money + item.getCost());
                }
            });
            // 인벤토리에서 삭제
            inventoryController.deleteByName(itemName);
            // 사용자 상태 업데이트
            playerController.updatePlayer(player);

            System.out.println("**** 판매 완료 ****");
            System.out.println("**** 현재 보유 금액: " + player.getCost() + " ****");

            System.out.println("추가 판매: 1, 판매 종료: 0");
            System.out.print("입력: ");
            try {
                input = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("올바른 값을 입력하시오");
                sc.nextLine();
                continue;
            }

        }
    }
}
