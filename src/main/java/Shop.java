import java.util.Arrays;

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

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.buy();
    }
}
