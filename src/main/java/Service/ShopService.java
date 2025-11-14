package Service;

import Controller.ItemController;
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

    ShopRepository  shopRepository = new ShopRepository();
    ItemController  itemController = new ItemController();
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

    //                for (int i = 1; i <= allItemList.size(); i++) {
    //                    if (allItemList.get(i) == null) continue;
    //                    for (int j = 1; j <= allItemList.get(i).size(); j++) {
    //                        if (items.allItems.get(i).get(j).getName().equals(itemName)) {
    //                            if (items.allItems.get(i).get(j).getCost() > money) {
    //                                System.out.println("**** 금액 부족 ****");
    //                                return;
    //                            } else {
    //                                System.out.println("**** 구매 완료 ****");
    //                                player.setCost(money -  items.allItems.get(i).get(j).getCost());
    //                                player.getInventory().add(items.allItems.get(i).get(j));
    //                                itemForBuy.remove(items.allItems.get(i).get(j));
    //                                break;
    //                            }
    //                        }
    //                    }
    //                }

                    target = itemController.selectItemByName(itemName);
                    if (target.getCost() < money) {
                        System.out.println("**** 금액 부족 ****");
                        return;
                    } else {
                        System.out.println("**** 구매 완료 ****");
                        // player 디비 사용으로 변경
                        player.setCost(money -  target.getCost());
                        player.getInventory().add(target);
                        itemForBuy.remove(target);
                        break;
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

    /*public void sell(Player player, Shop shop) {

        List<List<Item>> playItemList = new ArrayList<>();  // player 디비 사용으로 변경
        playItemList.add(player.getItemList());
        playItemList.add(player.getWeapons());
        playItemList.add(player.getArmors());
        playItemList.add(player.getInventory());

        int money = player.getCost(); // player 디비 사용으로 변경
        int input = -1;
        String itemName = "";
        int itemInput = 0;
        int categoryNum = 0;
        int itemNum = 0;
        boolean isNoWeapon = true;

        while (input != 0) {

            if (playItemList.isEmpty()) {
                System.out.println("판매할 물건 없음");
                break;
            }

            System.out.println("현재 돈: " + money);

            int count = 0;
            for (int i = 0; i < playItemList.size(); i++) {
                if (playItemList.get(i).isEmpty()) continue;
                for (int j = 0; j < playItemList.get(i).size(); j++) {
                    System.out.println("번호: " + (count) +
                            " / 이름: " + playItemList.get(i).get(j).getName() +
                            " / 가격: " + playItemList.get(i).get(j).getCost() +
                            " / 능력치: "  + playItemList.get(i).get(j).getValue()
                    );
                    count++;
                    System.out.println("=========================");
                }
            }
            System.out.println("번호: -1 / 판매 종료");

            System.out.print("판매할 아이템의 이름: ");
            itemName = sc.nextLine();

            if (itemName.equals("-1")) break;

            OUTER_LOOP:
            for (int i = 0; i < playItemList.size(); i++) {
                if (playItemList.get(i).isEmpty()) continue;
                for (int j = 0; j < playItemList.get(i).size(); j++) {
                    if (playItemList.get(i).get(j).getName().equals(itemName)) {
                        if (playItemList.get(i).get(j).getName().contains("무기")) {
                            for (int k = 0; k < player.getInventory().size(); k++) {
                                if (player.getInventory().get(k).getName().contains("무기")) {
                                    isNoWeapon = false;
                                } else {
                                    isNoWeapon = true;
                                }
                            }
                        }

                        if (isNoWeapon) {
                            System.out.println("**** 판매 후 교체할 무기가 없습니다 ****");
                            break OUTER_LOOP;
                        }

                        switch (i) {
                            case 0:
                                player.setCost(player.getCost() + playItemList.get(i).get(j).getCost());
                                player.getItemList().remove(playItemList.get(i).get(j));
                                break;
                            case 1:
                                player.setCost(player.getCost() + playItemList.get(i).get(j).getCost());
                                player.getWeapons().remove(playItemList.get(i).get(j));
                                break;
                            case 2:
                                player.setCost(player.getCost() + playItemList.get(i).get(j).getCost());
                                player.getArmors().remove(playItemList.get(i).get(j));
                                break;
                            case 3:
                                player.setCost(player.getCost() + playItemList.get(i).get(j).getCost());
                                player.getSupplies().remove(playItemList.get(i).get(j));
                                break;
                            default:
                                break;
                        }
                        System.out.println("판매 완료");
                        System.out.println("현재 보유 금액: " + player.getCost());
                        break;
                    }
                }
            }

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
    }*/
}
