package monster;



public class Monster {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int damage;
    protected String[] skills;
    protected int floor; // 몇 층 보스인지

    public Monster() {
    }

    public Monster(String name, int hp, int damage, String[] skills, int floor) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.damage = damage;
        this.skills = skills;
        this.floor = floor;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void printStatus() {
        System.out.println(name + " HP: " + hp + "/" + maxHp);
    }

    // Getter 메서드들
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getDamage() { return damage; }
    public String[] getSkills() { return skills; }
    public int getFloor() { return floor; }

    @Override
    public String toString() {
        return name + "가 나타났다!";
    }
}
