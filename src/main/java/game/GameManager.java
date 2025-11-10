package game;

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

        while(stage <= 5) {
            System.out.println("\n현재 스테이지: " + stage);
            progressStage();
            stage++;
        }

        System.out.println("모든 스테이지 완료! 게임 종료.");
    }

    private void progressStage() {
        switch(stage) {
            case 1:
                System.out.println("1층: 모험가로 시작합니다.");
                break;
            case 2:
                handleJobSelection();
                break;
            case 3:
                handleHiddenUpgrade();
                break;
            default:
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
