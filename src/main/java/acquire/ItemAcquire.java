package acquire;

import items.Item;
import items.ItemType;
import items.Items;
import user.Player;

public class ItemAcquire extends Item{

    public ItemAcquire(Player player,int stage){
        Item item;
        Item item2;
        switch (stage){
            case 1:
                //1단계 몬스터를 잡으면 기본방어구와 몽둥이를 준다.
                item=new Item("시작무기", 3, ItemType.WEAPON, 5);
                item2=new Item("시작방어구",3,ItemType.ARMOR, 5);
                player.addItem(item);
                player.addItem(item2);
                //돈도 줌
                player.setCost(player.getCost()+200);
                System.out.println("************************************");
                System.out.println();
                System.out.print("*\t"+item.getName()+", "+item2.getName()+", 200골드 획득!"+"\t*");
                System.out.println();

                break;
            case 2:
                //2단계 카드키를 줌
                player.addItem(new Item("카드키",0,ItemType.UNIQUE, 0));
                player.setCost(player.getCost()+200);
                //랜덤 아이템 2개
                item=getRandomItem();
                item2=getRandomItem();
                player.addItem(item);
                player.addItem(item2);
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
