package items;


import user.Player;

import javax.swing.*;
import java.util.*;

public class Shop {

    Items items = new Items();
    Scanner sc = new Scanner(System.in);

    String mainOutput = """
            **** 상점 도착. 구매 혹은 판매 선택 ****
            **** 구매: 1 / 판매: 2 / 장비창: 3 / 인벤토리: 4 / 나가기: 0 ****
            """;
    String buyOutput = """
            **** 구매 가능한 아이템 ****
            """;
    String sellOutput = """
            **** 판매 가능한 아이템 ****
            """;

    boolean isBuy = false;
    boolean isSell = false;

    public void mainStream(Player player) {

        int input = -1;

        while (input != 0) {
            System.out.println(mainOutput);
            System.out.println("현재 보유 금액: " + player.getCost());
            System.out.print("입력: ");
            input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1:
                    buy(player); break;
                case 2:
                    sell(player); break;
                case 3:
                    equipment(player); break;
                case 4:
                    inventory(player); break;
                default:
                    System.out.println("**** 올바른 숫자를 입력하시오 ****"); break;
            }
        }

        isBuy = false;
        isSell = false;

    }

    public void buy(Player player) {

        if (isBuy) {
            System.out.println("**** 상점 구매는 한 번만 이용 가능합니다 ****");
            return;
        } else {
            isBuy = true;
        }

        int categoryNum = 0;
        int itemNum = 0;
        int money = player.getCost();
        int input = -1;
        String itemName = "";
        List<Item> itemForBuy = new LinkedList<>();

        for (int i = 0; i < 4; i++) {
            categoryNum = (int) (Math.random() * 3 + 1);
            if (categoryNum == 3) {
                itemNum = (int) (Math.random() * 2 + 1);
            } else {
                itemNum = (int) (Math.random() * 7 + 1);
            }

            itemForBuy.add(items.allItems.get(categoryNum).get(itemNum));
        }

        System.out.println(buyOutput);

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
                        "가격: " + itemForBuy.get(i).getCost()
                );
            }
            System.out.println("****번호: -1 / 구매 종료");
            System.out.println("****");

//            try {
//
//                System.out.print("구매할 물건의 번호를 입력: ");
//                input = sc.nextInt();
//
//                if (input == -1) break;
//
//                if (itemForBuy.get(input).getCost() > money) {
//                    System.out.println("**** 금액 부족 ****");
//                    continue;
//                } else {
//                    System.out.println("**** 구매 완료 ****");
//                    player.setCost(money -  itemForBuy.get(input).getCost());
//                    player.getInventory().add(itemForBuy.get(input));
//                    itemForBuy.remove(input);
//                }
//            } catch (IndexOutOfBoundsException e) {
//                System.out.println("올바른 값을 입력하시오");
//                continue;
//            } catch (InputMismatchException e) {
//                System.out.println("올바른 값을 입력하시오");
//                continue;
//            }

            try {

                System.out.print("구매할 물건의 이름을 입력: ");
                itemName = sc.nextLine();

                if (itemName.equals("-1")) return;

                for (int i = 1; i <= items.allItems.size(); i++) {
                    if (items.allItems.get(i) == null) continue;
                    for (int j = 1; j <= items.allItems.get(i).size(); j++) {
                        if (items.allItems.get(i).get(j).getName().equals(itemName)) {
                            if (items.allItems.get(i).get(j).getCost() > money) {
                                System.out.println("**** 금액 부족 ****");
                                return;
                            } else {
                                System.out.println("**** 구매 완료 ****");
                                player.setCost(money -  items.allItems.get(i).get(j).getCost());
                                player.getInventory().add(items.allItems.get(i).get(j));
                                itemForBuy.remove(items.allItems.get(i).get(j));
                                break;
                            }
                        }
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
    }

    public void sell(Player player) {

        if (isSell) {
            System.out.println("**** 상점 판매는 한 번만 가능합니다 ****");
            return;
        } else {
            isSell = true;
        }

        List<List<Item>> playItemList = new ArrayList<>();
        playItemList.add(player.getItemList());
        playItemList.add(player.getWeapons());
        playItemList.add(player.getArmors());
        playItemList.add(player.getSupplies());
        playItemList.add(player.getInventory());

        int money = player.getCost();
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
                    System.out.println("아이템 번호: " + (count) +
                        " / 아이템 이름: " + playItemList.get(i).get(j).getName() +
                        " / 아이템 가격: " + playItemList.get(i).get(j).getCost()
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
    }

    public void equipment(Player player) {

        int input = 0;

        while (input != -1) {
            // player 객체의 weapon, armor 출력
            System.out.println("**** 착용 장비 ****");
            if (player.getWeapons().isEmpty()) System.out.println("**** 무기: 없음 ****");
            else System.out.println("**** 무기: " + player.getWeapons().get(0).getName() + " ****");
            if (player.getArmors().isEmpty()) System.out.println("**** 방어구: 없음 ****");
            else System.out.println("**** 방어구: " + player.getArmors().get(0).getName() + " ****");

            System.out.println("**** 나가기: -1 ****");
            System.out.print("입력: ");
            input = sc.nextInt();
            sc.nextLine();
        }

    }

    public void inventory(Player player) {

        // while문 안에서 사용자에게 -1 받지 않는 이상
        // player 객체의 inventory 전부 출력
        int input = 0;

        while (input != -1) {
            System.out.println("**** 인벤토리 ****");
            if (player.getInventory().isEmpty()) {
                System.out.println("**** 비어있음 ****");
            } else {
                for (int i = 0; i < player.getInventory().size(); i++) {
                    player.getInventory().stream().forEach(item -> {
                        System.out.println("**** " + item.getName() + " / " + item.getCost() + "원" + " ****");
                    });
                }
            }
            System.out.println("**** 나가기: -1 ****");
            System.out.print("입력: ");
            input = sc.nextInt();
            sc.nextLine();
        }

    }

    public void startShop(Player player) {
        mainStream(player);
    }
}
