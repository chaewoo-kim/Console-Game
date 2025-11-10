package items;

import user.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

//    public void sell(Player player) {
//
//        Item[] playerItems = {items.armor.get(1), items.weapon.get(2), items.supplies.get(2)}; // 나중에 player.items로 바꿔야함
//        Scanner sc = new Scanner(System.in);
//
//        int money = 5; // 이거 나중에 player.money로 바꿔야 함
//        String itemName = "";
//
//        Arrays.stream(playerItems).forEach(item -> {
//            System.out.println("이름: " + item.getName() + ", 판매 가격: " + item.getCost());
//        });
//
//        System.out.print("판매할 아이템의 이름: ");
//        itemName = sc.nextLine();
//
//        // 받은 아이템 이름 비교해서 돈 빼기
//        // 돈 없으면 못 쓰게
//        // 알림 문구 출력
//
//    }

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.buy();
    }
}
