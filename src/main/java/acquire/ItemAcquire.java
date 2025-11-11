package acquire;

import items.Item;
import items.ItemType;
import items.Items;
import user.Player;

public class ItemAcquire extends Item{

    public ItemAcquire(Player player,int stage){

        switch (stage){
            case 1:
                //1단계 몬스터를 잡으면 기본방어구와 몽둥이를 준다.
                player.addItem(new Item("시작무기", 3, ItemType.WEAPON));
                player.addItem(new Item("시작방어구",3,ItemType.ARMOR));
                //돈도 줌
                player.setCost(player.getCost()+200);
                break;
            case 2:
                //2단계 카드키를 줌
                player.addItem(new Item("카드키",0,ItemType.UNIQUE));
                player.setCost(player.getCost()+200);
                //랜덤 아이템 2개
                player.addItem(getRandomItem());
                player.addItem(getRandomItem());
                break;
            case 3:
                //3단계에서는
                player.setCost(player.getCost()+200);
                player.addItem(getRandomItem());
                player.addItem(getRandomItem());
                break;
            case 4:
                player.setCost(player.getCost()+200);
                player.addItem(getRandomItem());
                player.addItem(getRandomItem());
                break;
            case 5:
                player.setCost(player.getCost()+200);
                player.addItem(getRandomItem());
                player.addItem(getRandomItem());
                break;

        }
        //TODO:인벤토리 필드 필요
    }

}
