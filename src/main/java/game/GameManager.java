package game;

import View.ShopView;
import battle.BattleSystem;
import items.Shop;
import monster.*;
import user.Job;
import user.Player;
import java.util.Scanner;



public class GameManager {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }


    private Player player;
    private int stage;
    private Scanner scanner;
    private BattleSystem battleSystem;
    private ShopView shopView;

    public GameManager() {
        scanner = new Scanner(System.in);
        stage = 1;
    }

    public void startGame() {
        System.out.println("게임을 시작합니다!");
        System.out.print("플레이어 이름을 입력하세요: ");
        String playerName = scanner.nextLine();
        player = new Player(playerName);
        player.printStatus();
        shopView = new ShopView();

        while(stage <= 5) {
            System.out.println("\n현재 스테이지: " + stage);
            progressStage();
            if (player.getHp() <= 0) {
                System.out.println("게임 종료");
                break;
            }
            stage++;
            if (stage == 6) {
                System.out.println("모든 스테이지 완료! 게임 종료.");
                break;
            }
        }
    }

    private void progressStage() {
        switch(stage) {
            case 1:
                System.out.println("1층: 모험가로 시작합니다.");
                Monster m1 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m1, stage);
                battleSystem.startBattle();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 2:
                Monster m2 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m2, stage);
                battleSystem.startBattle();
                if (player.getHp() <= 0) break;
                handleJobSelection();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 3:
                Monster m3 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m3, stage);
                battleSystem.startBattle();
                if (player.getHp() <= 0) break;
                handleHiddenUpgrade();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 4:
                Monster m4 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m4, stage);
                battleSystem.startBattle();
                if  (player.getHp() <= 0) break;
                shopView.startShop(player);
                player.levelUp();
                break;
            default:
                Monster m5 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m5, stage);
                battleSystem.startBattle();
                if  (player.getHp() <= 0) break;
                shopView.startShop(player);
                player.levelUp();
                System.out.println(stage + "층: 스테이지 진행 중...");
                break;
        }
        player.printStatus();

    }

    private void handleJobSelection() {
        System.out.println("2층: 직업을 선택하세요.");
        System.out.println("1. 전사  2. 궁수  3. 마법사");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                player.chooseJob(Job.WARRIOR);
                break;
            case 2:
                player.chooseJob(Job.ARCHER);
                break;
            case 3:
                player.chooseJob(Job.MAGE);
                break;
            default:
                System.out.println("잘못된 선택입니다. 기본 전사로 설정합니다.");
                player.chooseJob(Job.WARRIOR);
        }
    }

    private void handleHiddenUpgrade() {
        System.out.println("3층: 히든 직업 업그레이드 시도!");
        player.HiddenUpgrade();
    }
}
