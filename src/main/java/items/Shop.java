package items;

import user.Player;

import java.util.*;

public class Shop {

    Items items = new Items();

    String mainOutput = """
            상점 도착. 구매 혹은 판매 선택. 구매: 1, 판매: 2
            """;
    String buyOutput = """
            구매 가능한 아이템: 
            """;
    String sellOutput = """
            판매 가능한 아이템: 
            """;

    public void buy() {

        Item[] itemForBuy = new Item[4];

        int categoryNum = 0;
        int itemNum = 0;

        for (int i = 0; i < 4; i++) {
            categoryNum = (int) (Math.random() * 3 + 1);
            if (categoryNum == 3) {
                itemNum = (int) (Math.random() * 2 + 1);
            } else {
                itemNum = (int) (Math.random() * 7 + 1);
            }

            itemForBuy[i] = items.allItems.get(categoryNum).get(itemNum);
        }

        // 디버그용 출력문
        Arrays.stream(itemForBuy).forEach(item -> {
            System.out.println(item);
        });
    }

    public void sell(Player player) {

        List<Item> playItemList = new ArrayList<>(
                Arrays.asList(
                        items.weapon.get(1),
                        items.armor.get(3),
                        items.supplies.get(1)
                )
        );
        Scanner sc = new Scanner(System.in);

        int money = 5; // 이거 나중에 player.money로 바꿔야 함
        int input = -1;
        String itemName = "";
        int itemCost = 0;
        int itemNum = 0;

        while (input != 0) {

            if (playItemList.isEmpty()) {
                System.out.println("판매할 물건 없음");
                break;
            }

            System.out.println("현재 돈: " + money);

            for (int i = 0; i < playItemList.size(); i++) {
                System.out.println("아이템 번호: " + i);
                System.out.println("아이템 이름: " + playItemList.get(i).getName());
                System.out.println("아이템 가격: " + playItemList.get(i).getCost());
            }

            System.out.print("판매할 아이템의 번호: ");
            itemNum = sc.nextInt();
            itemName = playItemList.get(itemNum).getName();

            for (Map.Entry<Integer, Item> entry : items.weapon.entrySet()) {
                if (entry.getValue().getName().equals(itemName)) {
                    itemCost = entry.getValue().getCost();
                    break;
                }
            }

            for (Map.Entry<Integer, Item> entry : items.armor.entrySet()) {
                if (entry.getValue().getName().equals(itemName)) {
                    itemCost = entry.getValue().getCost();
                    break;
                }
            }

            for (Map.Entry<Integer, Item> entry : items.supplies.entrySet()) {
                if (entry.getValue().getName().equals(itemName)) {
                    itemCost = entry.getValue().getCost();
                    break;
                }
            }

            playItemList.remove(itemNum);
            money += itemCost;
            System.out.println("판매 완료");
            System.out.println("추가 판매: 1, 판매 종료: 0");
            System.out.print("입력: ");
            input = sc.nextInt();

        }

        // Player 객체에 돈 업데이트 해야함

    }

    public static void main(String[] args) {
        Shop shop = new Shop();
//        shop.buy();
        shop.sell(new Player("chaewookim"));
    }
}
