package View;

import monster.Monster;
import Controller.MonsterController;

public class MonsterView {

    private MonsterController controller = new MonsterController();

    public Monster start(int floor) {

        Monster m = controller.summonMonster(floor);

        if (m == null) {
            System.out.println(floor + "층 몬스터 없음! (DB 확인 필요)");
            return null;
        }


        System.out.println("\n==== " + floor + "층 몬스터 등장! ====");
        System.out.println(m);

        return m;
    }
}
