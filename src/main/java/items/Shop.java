package items;


import user.Player;

import javax.swing.*;
import java.util.*;

public class Shop {

    Items items = new Items();
    Scanner sc = new Scanner(System.in);

    String mainOutput = """
            ************     상점 도착. 구매 혹은 판매 선택     ************
            **** 구매: 1 / 판매: 2 / 장비창: 3 / 인벤토리: 4 / 나가기: 0 ****
            *********************************************************
            """;
    String buyOutput = """
            *****************************************************
            ******************* 구매 가능한 아이템 ******************
            *****************************************************
            """;
    String sellOutput = """
            *****************************************************
            ******************* 판매 가능한 아이템 ******************
            *****************************************************
            """;

    boolean isBuy = false;
    boolean isSell = false;

    public void mainStream(Player player) {

        int input = -1;

        while (input != 0) {
            System.out.println(mainOutput);
            System.out.println("현재 보유 금액: " + player.getCost());
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
                    System.out.println("*********** 올바른 숫자를 입력하시오 ***********"); break;
            }
        }

        isBuy = false;
        isSell = false;

    }

    public void buy(Player player) {

        if (isBuy) {
            System.out.println("""
                    **************** 상점 구매는 한 번만 가능합니다 ****************
                    """);
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
                System.out.println("********* 구매할 수 있는 물건 없음 *********");
                break;
            }
            System.out.println("현재 보유 금액: " + player.getCost());

            for (int i = 0; i < itemForBuy.size(); i++) {
                System.out.println("**********************" +
                        "번호: " + i + " *****" + "\n" +
                        "이름: " + itemForBuy.get(i).getName() + " *****" + "\n" +
                        "가격: " + itemForBuy.get(i).getCost() + " *****" + "\n" +
                        "능력치: " + itemForBuy.get(i).getValue() + " *****" + "\n" +
                        "**********************"
                );
            }
            System.out.println("******** 번호: -1 / 구매 종료 *******");

            try {

                System.out.print("구매할 물건의 이름을 입력: ");
                itemName = sc.nextLine();

                if (itemName.equals("-1")) return;

                for (int i = 1; i <= items.allItems.size(); i++) {
                    if (items.allItems.get(i) == null) continue;
                    for (int j = 1; j <= items.allItems.get(i).size(); j++) {
                        if (items.allItems.get(i).get(j).getName().equals(itemName)) {
                            if (items.allItems.get(i).get(j).getCost() > money) {
                                System.out.println("********* 금액 부족 *********");
                                return;
                            } else {
                                System.out.println("********* 구매 완료 *********");
                                player.setCost(money -  items.allItems.get(i).get(j).getCost());
                                player.getInventory().add(items.allItems.get(i).get(j));
                                itemForBuy.remove(items.allItems.get(i).get(j));
                                break;
                            }
                        }
                    }
                }
            } catch(IndexOutOfBoundsException e) {
                System.out.println("******** 올바른 값을 입력하시오 ********");
            } catch(InputMismatchException e) {
                System.out.println("******** 올바른 값을 입력하시오 ********");
            }

            System.out.println("******** 계속 구매: 1 / 구매 종료: 0 ********");
            input = sc.nextInt();
            sc.nextLine();
        }
    }

    public void sell(Player player) {

        if (isSell) {
            System.out.println("******** 상점 판매는 한 번만 가능합니다 ********");
            return;
        } else {
            isSell = true;
        }

        List<List<Item>> playItemList = new ArrayList<>();
        playItemList.add(player.getItemList());
        playItemList.add(player.getWeapons());
        playItemList.add(player.getArmors());
//        playItemList.add(player.getSupplies());
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
                System.out.println("******** 판매할 물건 없음 ********");
                break;
            }

            System.out.println("현재 돈: " + money);

            int count = 0;
            for (int i = 0; i < playItemList.size(); i++) {
                if (playItemList.get(i).isEmpty()) continue;
                for (int j = 0; j < playItemList.get(i).size(); j++) {
                    System.out.println(
                            "이름: " + playItemList.get(i).get(j).getName() + "\n" +
                        "가격: " + playItemList.get(i).get(j).getCost() + "\n" +
                        "능력치: "  + playItemList.get(i).get(j).getValue()
                    );
                    count++;
                    System.out.println("*******************************");
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
                            System.out.println("******** 판매 후 교체할 무기가 없습니다 ********");
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
                        System.out.println("********* 판매 완료 *********");
                        System.out.println("현재 보유 금액: " + player.getCost());
                        break;
                    }
                }
            }

            System.out.println("****** 추가 판매: 1, 판매 종료: 0 *****");
            try {
                input = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("******** 올바른 값을 입력하시오 ********");
                sc.nextLine();
                continue;
            }

        }
    }

    public void equipment(Player player) {

        int input = 0;

        while (input != -1) {
            // player 객체의 weapon, armor 출력
            System.out.println("********* 착용 장비 *********");
            if (player.getWeapons().isEmpty()) System.out.println("********* 무기: 없음 *********");
            else System.out.println("********* 무기: " + player.getWeapons().get(0).getName() + " / 능력치: " + player.getWeapons().get(0).getValue() + " *********");
            if (player.getArmors().isEmpty()) System.out.println("**** 방어구: 없음 ****");
            else System.out.println("*********" + player.getArmors().get(0).getName() + "/ 능력치: " + player.getArmors().get(0).getValue() + " *********");

            System.out.println("************* 나가기: -1 *************");
            input = sc.nextInt();
            sc.nextLine();
        }

    }

    public void inventory(Player player) {

        // while문 안에서 사용자에게 -1 받지 않는 이상
        // player 객체의 inventory 전부 출력
        int input = 0;
        String equipInput = "";
        while (input != -1) {
            System.out.println("************* 인벤토리 *************");
            if (player.getInventory().isEmpty()) {
                System.out.println("************* 비어있음 *************");
            } else {
                for (int i = 0; i < player.getInventory().size(); i++) {
                    System.out.println("**** " + player.getInventory().get(i).getName() + " / " + player.getInventory().get(i).getCost() + "원 / " + player.getInventory().get(i).getValue() + " ****");
                }
                System.out.println("*********** 장비를 변경하시겠습니까? ***********");
                System.out.print("Y/N: ");
                equipInput = sc.nextLine();
                if (equipInput.equals("Y")) {
                    changeEquipment(player);
                    System.out.println("*********** 장비를 변경했습니다 ***********");
                } else {
                    System.out.println("*********** 장비를 변경하지 않습니다 ***********");
                    break;
                }
            }
            System.out.println("************* 나가기: -1 *************");
            input = sc.nextInt();
            sc.nextLine();
        }

    }

    private void changeEquipment(Player player) {

        String input = "";
        Item equipItem = null;
        Item invenItem = null;

        System.out.println("*********** 착용 가능한 장비 ***********");
        player.getInventory().stream().forEach(item -> {
            System.out.println("************* " + item.getName() + " *************");
        });
        System.out.print("착용할 장비의 이름을 입력: ");
        input = sc.nextLine();

        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).getName().equals(input)) {
                invenItem = player.getInventory().get(i);
            }
        }

        player.getInventory().remove(invenItem);


        if (input.contains("무기")) {
            player.getWeapons().add(invenItem);
            equipItem = player.getWeapons().get(0);
            player.getInventory().add(equipItem);
            player.getWeapons().remove(equipItem);
        } else if(input.contains("방어구")) {
            player.getArmors().add(invenItem);
            equipItem = player.getArmors().get(0);
            player.getInventory().add(equipItem);
            player.getArmors().remove(equipItem);
        } else {
            System.out.println("***** 무기와 방어구를 제외한 아이템은 장착할 수 없습니다 *****");
        }
    }

    public void startShop(Player player) {
        mainStream(player);
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public String getMainOutput() {
        return mainOutput;
    }

    public void setMainOutput(String mainOutput) {
        this.mainOutput = mainOutput;
    }

    public String getBuyOutput() {
        return buyOutput;
    }

    public void setBuyOutput(String buyOutput) {
        this.buyOutput = buyOutput;
    }

    public String getSellOutput() {
        return sellOutput;
    }

    public void setSellOutput(String sellOutput) {
        this.sellOutput = sellOutput;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }

}
