package game;

import Controller.PlayerController;
import View.ShopView;
import battle.BattleSystem;
import items.Shop;
import monster.*;
import story.StoryText;
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
    private PlayerController playerController;
    private StoryText storyText;

    public GameManager() {
        scanner = new Scanner(System.in);
        stage = 1;
        playerController = new PlayerController();
    }

    public void startGame() {
        System.out.println("게임을 시작합니다!");
        System.out.print("플레이어 이름을 입력하세요: ");
        String playerName = scanner.nextLine();
        player = new Player(playerName);
        playerController.insertPlayer(player);
        player.printStatus();
        shopView = new ShopView();
        storyText = new StoryText();

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
                try {
                    System.out.println(storyText.getBackground());
                    Thread.sleep(2000);
                    System.out.println(storyText.getBeforeFirstFloor());
                    Thread.sleep(2000);
                    System.out.println(storyText.getNowFirstFloor());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Monster m1 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m1, stage);
                battleSystem.startBattle();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 2:
                try {
                    System.out.println(storyText.getBeforeSecondFloor());
                    Thread.sleep(2000);
                    System.out.println(storyText.getNowSecondFloor());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Monster m2 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m2, stage);
                battleSystem.startBattle();
                if (player.getHp() <= 0) break;
                handleJobSelection();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 3:
                try {
                    System.out.println(storyText.getAfterSecondFloor());
                    Thread.sleep(2000);
                    System.out.println(storyText.getBeforeThirdFloor());
                    Thread.sleep(2000);
                    System.out.println(storyText.getNowThirdFloor());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Monster m3 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m3, stage);
                battleSystem.startBattle();
                if (player.getHp() <= 0) break;
                handleHiddenUpgrade();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 4:
                try {
                    System.out.println(storyText.getAfterThirdFloor());
                    Thread.sleep(2000);
                    System.out.println(storyText.getBeforeFourthFloor());
                    Thread.sleep(2000);
                    System.out.println(storyText.getNowFourthFloor());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Monster m4 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m4, stage);
                battleSystem.startBattle();
                if  (player.getHp() <= 0) break;
                shopView.startShop(player);
                player.levelUp();
                break;
            default:
                try {
                    System.out.println(storyText.getAfterFourthFloor());
                    Thread.sleep(2000);
                    System.out.println(storyText.getNowFifthFloor());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Monster m5 = Monster.selectMonsterByFloor(stage);
                battleSystem = new BattleSystem(player, m5, stage);
                battleSystem.startBattle();
                if  (player.getHp() <= 0) break;
                System.out.println(storyText.getAfterFifthFloor());
                System.out.println(storyText.getFinalText());
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
                player.chooseJob("WARRIOR");
                break;
            case 2:
                player.chooseJob("ARCHER");
                break;
            case 3:
                player.chooseJob("MAGE");
                break;
            default:
                System.out.println("잘못된 선택입니다. 기본 전사로 설정합니다.");
                player.chooseJob("WARRIOR");
        }
    }

    private void handleHiddenUpgrade() {
        System.out.println("3층: 히든 직업 업그레이드 시도!");
        player.HiddenUpgrade();
    }
}
