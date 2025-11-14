package Repository;

import items.Item;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate.close;

public class ArmorRepository {

    Properties prop = new Properties();

    public int insertItem(Connection con, Item invenItem) {

        PreparedStatement pstmt = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            String sql = prop.getProperty("insertArmor");
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, invenItem.getName());
            pstmt.setInt(2, invenItem.getCost());
            pstmt.setString(3, invenItem.getType());
            pstmt.setInt(4, invenItem.getValue());

            result =  pstmt.executeUpdate();

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

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            String sql = prop.getProperty("deleteByNameArmor");
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);

            result =  pstmt.executeUpdate();

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
