package View;

import Controller.ShopController;
import items.Shop;
import user.Player;

import java.util.Scanner;

public class ShopView {

    Shop shop = new Shop();
    ShopController shopController = new ShopController();
    Scanner sc = new Scanner(System.in);

    public void mainStream(Player player) {

        int input = -1;

        while (input != 0) {
            System.out.println(shop.getMainOutput());
            System.out.println("현재 보유 금액: " + player.getCost());
            System.out.print("입력: ");
            input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1:
                    shopController.buy(player, shop); break;
                case 2:
                    shopController.sell(player, shop); break;
//                case 3:
//                    shopController.equipment(player, shop); break;
//                case 4:
//                    shopController.inventory(player, shop); break;
                default:
                    System.out.println("**** 올바른 숫자를 입력하시오 ****"); break;
            }
        }

        shop.setBuy(false);
        shop.setSell(false);
    }

    public void startShop(Player player) {
        mainStream(player);
    }
}
