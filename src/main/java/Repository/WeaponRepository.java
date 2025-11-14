package Repository;

import items.Item;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate.close;

public class WeaponRepository {

    Properties prop = new Properties();

    public Item select(Connection con, String tableName) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Item weaponOrArmor = null;
        String sql = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            if (tableName.equals("WEAPON")) {
                sql = sql = prop.getProperty("selectWeapon");
            } else if (tableName.equals("ARMOR")) {
                sql = sql = prop.getProperty("selectArmor");
            }

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, tableName);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                weaponOrArmor = new Item(
                        rs.getString("name"),
                        rs.getInt("cost"),
                        rs.getString("category"),
                        rs.getInt("value")
                );
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
        }

        return weaponOrArmor;
    }

    public int insertItem(Connection con, Item invenItem) {

        PreparedStatement pstmt = null;
        int result = 0;
        String sql = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            sql = sql = prop.getProperty("insertWeaponItem");

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, invenItem.getName());
            pstmt.setInt(2, invenItem.getCost());
            pstmt.setString(3, invenItem.getType());
            pstmt.setInt(4, invenItem.getValue());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int deleteByName(Connection con, String name) {

        PreparedStatement pstmt = null;
        int result = 0;
        String sql = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            sql = sql = prop.getProperty("deleteByNameWeapon");

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }
}
