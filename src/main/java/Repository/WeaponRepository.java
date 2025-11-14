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
}
