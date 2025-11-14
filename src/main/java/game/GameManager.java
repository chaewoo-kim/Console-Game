package game;

import Controller.PlayerController;
import View.ShopView;
import battle.BattleSystem;
import items.Shop;
import monster.*;
import user.Job;
import user.Player;
import java.util.Scanner;

/*
*  게임의 흐름과 전반적인 게임 진행을 관리한다.
*  단계별 진행, 입력 처리, 이벤트 호출 등을 조율한다.
* */

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
                battleSystem = new BattleSystem(player, new Floor1Boss(), stage);
                battleSystem.startBattle();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 2:
                battleSystem = new BattleSystem(player, new Floor2Boss(), stage);
                battleSystem.startBattle();
                if (player.getHp() <= 0) break;
                handleJobSelection();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 3:
                battleSystem = new BattleSystem(player, new Floor3Boss(), stage);
                battleSystem.startBattle();
                if (player.getHp() <= 0) break;
                handleHiddenUpgrade();
                shopView.startShop(player);
                player.levelUp();
                break;
            case 4:
                battleSystem = new BattleSystem(player, new Floor4Boss(), stage);
                battleSystem.startBattle();
                if  (player.getHp() <= 0) break;
                shopView.startShop(player);
                player.levelUp();
                break;
            default:
                battleSystem = new BattleSystem(player, new Floor5Boss(), stage);
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
