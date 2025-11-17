package Service;

import Repository.MonsterRepository;
import monster.Monster;

import java.util.List;

public class MonsterService {

    private MonsterRepository repo = new MonsterRepository();

    public Monster loadMonster(int floor) {
        return repo.findByFloor(floor);
    }

    public void dealDamage(Monster monster, int dmg) {
        monster.takeDamage(dmg);
        repo.updateHp(monster);
    }

    public List<String> getSkills(int id) {
        return repo.findSkills(id);
    }
}
