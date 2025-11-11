package items;


import user.Player;

import java.util.*;

public class Shop {

    Items items = new Items();
    Scanner sc = new Scanner(System.in);

    String mainOutput = """
            **** 상점 도착. 구매 혹은 판매 선택 ****
            **** 구매: 1 / 판매: 2 / 나가기: 0 ****
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
            System.out.print("입력: ");
            input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1:
                    buy(player); break;
                case 2:
                    sell(player); break;
                default:
                    System.out.println("**** 올바른 숫자를 입력하시오 ****"); break;
            }
        }

    }

    public Player buy(Player player) {

        if (isBuy) {
            System.out.println("**** 상점 구매는 한 번만 이용 가능합니다 ****");
            return player;
        } else {
            isBuy = true;
        }

        List<Item> itemForBuy = new LinkedList<>();

        int categoryNum = 0;
        int itemNum = 0;
        int money = 10; // Player.money로 변경
        int input = -1;

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

            for (int i = 0; i < itemForBuy.size(); i++) {
                System.out.println("****" +
                        "번호: " + i + " / " +
                        "이름: " + itemForBuy.get(i).getName() + " / " +
                        "가격: " + itemForBuy.get(i).getCost()
                );
            }
            System.out.println("****");

            System.out.print("구매할 물건의 번호를 입력: ");
            input = sc.nextInt();

            if (itemForBuy.get(input).getCost() > money) {
                System.out.println("**** 금액 부족 ****");
                break;
            } else {
                System.out.println("**** 구매 완료 ****");
                money -= itemForBuy.get(input).getCost();
//                player.inventory.add(itemForBuy.get(input)); -> player 인벤토리에 추가
                itemForBuy.remove(input);
            }

            System.out.println("**** 계속 구매: 1 / 구매 종료: 0 ****");
            System.out.print("입력: ");
            input = sc.nextInt();
        }

        return player;
    }

    public Player sell(Player player) {

        if (isSell) {
            System.out.println("**** 상점 판매는 한 번만 가능합니다 ****");
            return player;
        } else {
            isSell = true;
        }

        List<List<Item>> playItemList = new ArrayList<>();
        playItemList.add(player.getItemList());
        playItemList.add(player.getWeapons());
        playItemList.add(player.getArmors());
        playItemList.add(player.getSupplies());

        int money = player.getCost();
        int input = -1;
        String itemName = "";
        int itemInput = 0;
        int categoryNum = 0;
        int itemNum = 0;

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
                    System.out.println("아이템 번호: " + (count));
                    System.out.println("아이템 이름: " + playItemList.get(i).get(j).getName());
                    System.out.println("아이템 가격: " + playItemList.get(i).get(j).getCost());
                    count++;
                    System.out.println("=========================");
                }
            }

            System.out.print("판매할 아이템의 이름: ");
            itemName = sc.nextLine();

            for (int i = 0; i < playItemList.size(); i++) {
                if (playItemList.get(i).isEmpty()) continue;
                for (int j = 0; j < playItemList.get(i).size(); j++) {
                    if (playItemList.get(i).get(j).getName().equals(itemName)) {
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
            input = sc.nextInt();
            sc.nextLine();

        }

        return player;
    }

    public static void main(String[] args) {
        Shop shop = new Shop();
        Player player = new Player("chaewookim");
        Items items = new Items();

        System.out.println("시작 전");
        System.out.println(player.getItemList());
        System.out.println(player.getWeapons());
        System.out.println(player.getArmors());
        System.out.println(player.getSupplies());
        System.out.println("***************");

        player.addItem(items.weapon.get(1));
        player.addItem(items.weapon.get(2));
        player.addItem(items.weapon.get(3));
        player.addItem(items.armor.get(2));
        player.addItem(items.armor.get(3));
        player.addItem(items.supplies.get(1));
        player.addItem(items.supplies.get(2));

        shop.mainStream(player);
        System.out.println("시작 후 **************");
        System.out.println(player.getItemList());
        System.out.println(player.getWeapons());
        System.out.println(player.getArmors());
        System.out.println(player.getSupplies());
    }
}
