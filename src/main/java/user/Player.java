package user;

import java.util.Random;

public class Player {
    private String name;
    private int level;
    private int hp;
    private int maxHp;
    private Job job;
    private String skill;
    private int mp;
    private int maxMp;

    public Player(String name) {  // -> 1층에서는 기본적으로 모험가 직업을가짐.
        this.name = name;          //
        this.level = 1;
        this.hp = 100;
        this.maxHp = 100;
        this.mp = 10;
        this.maxMp = 50;
        this.job = Job.ADVENTURER;
        this.skill = null;
    }

    public void chooseJob(Job newJob) {     // -> 2층에서 직업정하기.
        this.job = newJob;
        switch (newJob) {
            case WARRIOR:
                maxHp = 130;
                maxMp = 20;
                skill = "대가리뽀사기";
                break;
            case ARCHER:
                maxHp = 110;
                maxMp = 30;
                skill = "주몽 원샷";
                break;
            case MAGE:
                maxHp = 90;
                maxMp = 50;
                skill = "아이스 에이지";
                break;
            default:
                break;
        }
        hp = maxHp;
        mp = maxMp;
    }

    public void HiddenUpgrade() {       // -> 3층에서 50%확률로 히든직업을 얻을 수 있고, 스킬이 업그레이드 된다.
        Random rand = new Random();
        if (rand.nextBoolean()) { // -> 50% 확률: nextBoolean()은 true 또는 false를 랜덤으로 반환한다.
            switch (this.job) {
                case WARRIOR:
                    this.job = Job.DRAGON_WOO;
                    this.skill = "용의 콧물";
                    System.out.println("히든직업 드래곤 우로 업그레이드!");
                    break;
                case ARCHER:
                    this.job = Job.CHAEU_CHOW;
                    this.skill = "그의 눈빛";
                    System.out.println("히든직업 채우차우로 업그레이드!");
                    break;
                case MAGE:
                    this.job = Job.LEE_SANGJUN;
                    this.skill = "배꼽 탈취";
                    System.out.println("히든직업 개그맨 이상준으로 업그레이드!");
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("히든직업 업그레이드 실패. 기존 직업 유지.");
        }
    }

    public void useSkill() {        //      -> 스킬을 콘솔에 출력(콘솔효과 정할 수 있음).
        if (skill != null) {
            System.out.println(name + "의 스킬 발동: " + skill);
        } else {
            System.out.println("스킬 없음");
        }
    }

    public void printStatus() {     // -> 플레이어의 현재 상태(닉네임, 레벨, hp, mp, 직업, 스킬)를 알 수 있음.
        System.out.println("닉네임: " + name);
        System.out.println("LEVEL: " + level);
        System.out.println("HP : " + hp + "/" + maxHp);
        System.out.println("MP : " + mp + "/" + maxMp);
        System.out.println("직업: " + job);
        if (skill != null) {
            System.out.println("스킬: " + skill);
        }
    }
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }
}
