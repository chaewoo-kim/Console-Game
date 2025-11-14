package Repository;

import items.Item;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate.close;

public class InventoryRepository {

    Properties prop = new Properties();

    public List<Item> selectAll(Connection con) {

        Statement stmt = null;
        ResultSet rs = null;
        List<Item> inventoryList = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            String sql = prop.getProperty("selectAllInventory");
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                inventoryList.add(new Item(
                        rs.getString("name"),
                        rs.getInt("cost"),
                        rs.getString("category"),
                        rs.getInt("value")
                ));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(stmt);
        }

        return inventoryList;
    }

    public int deleteByName(Connection con, String itemName) {

        PreparedStatement pstmt = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            String sql = prop.getProperty("deleteByName");
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, itemName);

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

    public int insertItem(Connection con, Item equipItem) {

        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet selectRs = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            String sql = prop.getProperty("insertInvenItem");
            String selectSql = prop.getProperty("selectAllInventory");

            stmt = con.createStatement();
            selectRs = stmt.executeQuery(selectSql);

            while (selectRs.next()) {
                if (selectRs.getString("name").equals(equipItem.getName())) {
                    System.out.println("**** 같은 물건은 가질 수 없습니다 ****");
                    return -1;
                }
            }

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, equipItem.getName());
            pstmt.setInt(2, equipItem.getCost());
            pstmt.setString(3, equipItem.getType());
            pstmt.setInt(4, equipItem.getValue());

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
