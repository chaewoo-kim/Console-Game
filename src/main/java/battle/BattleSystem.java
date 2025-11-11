package battle;

import monster.Monster;
import user.Player;

import java.util.Scanner;

public class BattleSystem {
    private Player player;
    private Monster monster;
    private int stage;

    public BattleSystem(Player player, Monster monster, int stage) {
        this.player = player;
        this.monster = monster;
        this.stage = stage;
    }

    public void startBattle() {
        //1. 만남
        System.out.println(monster.toString());

        //2~3번을 몬스터가 죽을 때까지 반복
        while(monster.isAlive()) {
            performMonsterAttack();
            if (player.getHp() <= 0) break;
            performPlayerTurn();
        }

        displayBattleResult();
    }

    private void performMonsterAttack() {
        //2. 몬스터 공격
        monster.printStatus();
        int n=monster.getSkills().length;
        String skillUsed = monster.getSkills()[(int)(Math.random()*n)];
        System.out.println(monster.getName() + "의 " + skillUsed + " 사용!");
        player.setHp(player.getHp() - monster.getDamage());
        player.printStatus();
    }

    private void performPlayerTurn() {
        //3. 사람 공격
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. 공격 , 2. 물약회복");
        //3-1. mp소모 공격
        int choice = scanner.nextInt();
        if(choice == 1) {
            player.useSkill();
            monster.takeDamage(1);
            player.printStatus();
            monster.printStatus();
        }else if(choice == 2) {
            //TODO:물약 소모
            player.printStatus();
            monster.printStatus();
        }
    }

    private void displayBattleResult() {
        if(monster.isAlive()) {
            System.out.println("패배했습니다...");
        } else {
            System.out.println("승리했습니다!");
        }
    }
}
