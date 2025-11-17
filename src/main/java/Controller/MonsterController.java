package Controller;

import Service.MonsterService;
import monster.Monster;

import java.util.List;

public class MonsterController {

    private MonsterService service = new MonsterService();

    public Monster summonMonster(int floor) {
        return service.loadMonster(floor);
    }

    public void applyDamage(Monster monster, int dmg) {
        service.dealDamage(monster, dmg);
    }

    public List<String> getMonsterSkills(int id) {
        return service.getSkills(id);
    }
}
