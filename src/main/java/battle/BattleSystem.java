package battle;

import Controller.PlayerController;
import acquire.ItemAcquire;
import monster.Monster;
import user.Player;

import java.util.Scanner;

public class BattleSystem {
    private Player player;
    private Monster monster;
    private int stage;
    private boolean playerStunned;
    private PlayerController playerController;

    public BattleSystem(Player player, Monster monster, int stage) {
        this.player = player;
        this.monster = monster;
        this.stage = stage;
        this.playerStunned = false;
        this.playerController = new PlayerController();
    }

    public void startBattle() {
        //1. 만남
        System.out.println(monster.toString());

        //2~3번을 몬스터가 죽을 때까지 반복
        while(monster.isAlive()) {
            performMonsterAttack();
            if (player.getHp() <= 0) break;
            if (!playerStunned) {
                performPlayerTurn();
            }else{
                System.out.println(player.getName()+"은 아직 잠에서 깨어나지 못 했습니다...");
                playerStunned = false;
            }
            System.out.println("====================================================");
        }
        displayBattleResult();
    }

    private void performMonsterAttack() {
        //2. 몬스터 공격
        monster.printStatus();
        player.printStatus();
        int n=monster.getSkills().length;
        String skillUsed = monster.getSkills()[(int)(Math.random()*n)];
        System.out.println(monster.getName() + "의 " + skillUsed + " 사용!");
        // 섹시 보이스 스킬인 경우 특별 처리
        if(skillUsed.equals("섹시 보이스")) {
            restBattle();
            return; // 데미지는 주지 않고 기절 효과만
        }
        player.takeDamage(monster, player);
        playerController.updatePlayer(player);
        player.printStatus();
        monster.printStatus();
    }

    private void performPlayerTurn() {
        //3. 사람 공격
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = false;
        do{
        player.printStatus();
        monster.printStatus();
        System.out.println("1. 공격 , 2. 물약회복");
        //3-1. mp소모 공격
        int choice = scanner.nextInt();
        if(choice == 1) {
            player.useSkill(player);
            playerController.updatePlayer(player);
            monster.takeDamage(1);
            player.printStatus();
            monster.printStatus();
            isContinue = true;
        }else if(choice == 2) {
            //TODO:물약 소모
            if (player.isHavePotion(player)) {
                //물약이 있다면
                System.out.println("물약을 소모합니다.");
                player.usePotion();
                playerController.updatePlayer(player);
                player.printStatus();
                monster.printStatus();
                isContinue = true;
            } else {
                System.out.println("물약이 없습니다.");

            }
        }if(isContinue)break;
        }while(true);

    }

    public void clearFloor() {  // -> 층을 클리어하면 레벨 1증가하고 현재상태를 갱신한다.
        System.out.println("층 클리어! 다음 층으로...");
        player.levelUp();
        playerController.updatePlayer(player);
    }

    private void displayBattleResult() {
        if(monster.isAlive()) {
            System.out.println("패배했습니다...");
        } else {
            System.out.println("승리했습니다!");
            new ItemAcquire(player,stage);
        }
    }

    private void restBattle(){
        System.out.println();
        System.out.println(player.getName()+"은 잠이 들어버렸다..!");
        playerStunned = true;
        playerController.updatePlayer(player);
        player.printStatus();
        monster.printStatus();
    }
}
