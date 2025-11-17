package monster;

import common.JDBCTemplate;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Monster {

    protected String name;
    protected int hp;
    protected int maxHp;
    protected int damage;
    protected String[] skills;
    protected int floor;

    private static Properties prop = new Properties();

    static {
        try {
            FileInputStream fis =
                    new FileInputStream("src/main/java/config/jdbc-config.properties");

            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Monster() {}

    public Monster(String name, int hp, int damage, String[] skills, int floor) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.damage = damage;
        this.skills = skills;
        this.floor = floor;
    }


    public static Monster selectMonsterByFloor(int floor) {

        Monster m = null;
        String sql = prop.getProperty("selectMonsterByFloor");

        try (Connection conn = JDBCTemplate.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, floor);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                m = new Monster(
                        rs.getString("name"),
                        rs.getInt("hp"),
                        rs.getInt("damage"),
                        rs.getString("skills").split(","),
                        rs.getInt("floor")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    public static int updateMonsterHp(String name, int newHp) {

        String sql = prop.getProperty("updateMonsterHp");
        int result = 0;

        try (Connection conn = JDBCTemplate.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newHp);
            pstmt.setString(2, name);

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> selectSkills(int monsterId) {
        List<String> list = new ArrayList<>();

        String sql = prop.getProperty("selectMonsterSkills");

        try (Connection conn = JDBCTemplate.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, monsterId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("skill_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (hp < 0) hp = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void printStatus() {
        System.out.println(name + " HP: " + hp + "/" + maxHp);
    }

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
