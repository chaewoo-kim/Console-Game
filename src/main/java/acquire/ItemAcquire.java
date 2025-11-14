package acquire;

import Controller.InventoryController;
import Controller.ItemController;
import Controller.PlayerController;
import Repository.ItemRepository;
import items.Item;
import items.ItemType;
import items.Items;
import user.Player;

public class ItemAcquire extends Item{

    ItemController itemController;
    PlayerController playerController;
    InventoryController inventoryController;

    public ItemAcquire(Player player,int stage){
        Item item;
        Item item2;
        this.itemController = new ItemController();
        this.playerController = new PlayerController();
        this.inventoryController = new InventoryController();
        switch (stage){
            case 1:
                //1단계 몬스터를 잡으면 기본방어구와 몽둥이를 준다.
                item=itemController.selectItemByName("매콤한 주먹");
                item2=itemController.selectItemByName("담백한 상의");
                player.addItem(item);
                player.addItem(item2);
                inventoryController.insertItem(item);
                inventoryController.insertItem(item2);
                //돈도 줌
                player.setCost(player.getCost()+200);
                playerController.updatePlayer(player);
                System.out.println("************************************");
                System.out.println();
                System.out.print("*\t"+item.getName()+", "+item2.getName()+", 200골드 획득!"+"\t*");
                System.out.println();

                break;
            case 2:
                //2단계 카드키를 줌
                player.addItem(itemController.selectItemByName("카드키"));
                player.setCost(player.getCost()+200);
                //랜덤 아이템 2개
                item=getRandomItem();
                item2=getRandomItem();
                player.addItem(item);
                player.addItem(item2);
                inventoryController.insertItem(item);
                inventoryController.insertItem(item2);
                playerController.updatePlayer(player);
                System.out.println("************************************");
                System.out.println();
                System.out.print("*\t"+item.getName()+", "+item2.getName()+", 수상한 카드키, 200골드 획득!"+"\t*");
                System.out.println();
                System.out.println("************************************");
                break;
            case 3:
                //3단계에서는
                player.setCost(player.getCost()+200);
                item=getRandomItem();
                item2=getRandomItem();
                player.addItem(item);
                player.addItem(item2);
                inventoryController.insertItem(item);
                inventoryController.insertItem(item2);
                playerController.updatePlayer(player);
                System.out.println("************************************");
                System.out.println();
                System.out.print("*\t"+item.getName()+", "+item2.getName()+", 200골드 획득!"+"\t*");
                System.out.println();
                System.out.println("************************************");
                break;
            case 4:
                player.setCost(player.getCost()+200);
                item=getRandomItem();
                item2=getRandomItem();
                player.addItem(item);
                player.addItem(item2);
                inventoryController.insertItem(item);
                inventoryController.insertItem(item2);
                playerController.updatePlayer(player);
                System.out.println("************************************");
                System.out.println();
                System.out.print("*\t"+item.getName()+", "+item2.getName()+", 200골드 획득!"+"\t*");
                System.out.println();
                System.out.println("************************************");
                break;
            case 5:
                player.setCost(player.getCost()+200);
                item=getRandomItem();
                item2=getRandomItem();
                player.addItem(item);
                player.addItem(item2);
                inventoryController.insertItem(item);
                inventoryController.insertItem(item2);
                playerController.updatePlayer(player);
                System.out.println("************************************");
                System.out.println();
                System.out.print("*\t"+item.getName()+", "+item2.getName()+", 200골드 획득!"+"\t*");
                System.out.println();
                System.out.println("************************************");
                break;

        }
        //TODO:인벤토리 필드 필요
    }

}
