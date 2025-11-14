package Repository;

import items.Item;
import items.Items;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate.close;

public class ItemRepository {

    public Item selectItemByName(Connection con, String name) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Properties prop = new Properties();
        Item item = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/MenuMapper.xml"));
            String sql = prop.getProperty("selectItemByName");
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                item = new Item(
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

        return item;

    }

    public Item selectItemByIndex(Connection con, int index) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Properties prop = new Properties();
        Item item = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/MenuMapper.xml"));
            String sql = prop.getProperty("selectItemByIndex");
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, index);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                item = new Item(
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

        return item;
    }

    public List<Item> selectAllItems(Connection con) {

        Statement stmt = null;
        ResultSet rs = null;
        Properties prop = new Properties();
        List<Item> itemList = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/MenuMapper.xml"));
            String sql = prop.getProperty("selectAllItems");
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                itemList.add(new Item(
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

        return itemList;
    }

    public int insertItem(Connection con, Item item) {

        PreparedStatement pstmt = null;
        Statement maxStmt = null;
        ResultSet maxRs = null;
        Properties prop = new Properties();
        int result = 0;

        try {
            if (selectItemByName(con, item.getName()) != null) {
                return -1;
            }

            prop.loadFromXML(new FileInputStream("src/main/java/mapper/MenuMapper.xml"));
            String sql = prop.getProperty("insertItem");
            pstmt = con.prepareStatement(sql);
            String maxSql = prop.getProperty("selectMaxId");
            maxStmt = con.createStatement();

            maxRs = maxStmt.executeQuery(maxSql);

            int id = maxRs.next() ? maxRs.getInt("MAX(id)") : -1;

            pstmt.setInt(1, id+1);
            pstmt.setString(2, item.getName());
            pstmt.setInt(3, item.getCost());
            pstmt.setString(4, item.getType());
            pstmt.setInt(5, item.getValue());

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
