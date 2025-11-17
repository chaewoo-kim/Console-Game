package Repository;

import monster.Monster;

import java.util.List;

public class MonsterRepository {

    public Monster findByFloor(int floor) {
        return Monster.selectMonsterByFloor(floor);
    }

    public void updateHp(Monster m) {
        Monster.updateMonsterHp(m.getName(), m.getHp());
    }

    public List<String> findSkills(int id) {
        return Monster.selectSkills(id);
    }
}
