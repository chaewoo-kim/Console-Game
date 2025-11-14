package Repository;

import user.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import static common.JDBCTemplate.close;

public class PlayerRepository {

    Properties prop = new Properties();

    public int insertPlayer(Connection con, Player player) {

        PreparedStatement pstmt = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            String sql = prop.getProperty("insertPlayer");
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, player.getName());
            pstmt.setInt(2, player.getLevel());
            pstmt.setInt(3, player.getHp());
            pstmt.setInt(4, player.getMaxHp());
            pstmt.setInt(5, player.getMp());
            pstmt.setInt(6, player.getMaxMp());
            pstmt.setString(7, player.getJob());
            pstmt.setString(8, player.getSkill());
            pstmt.setInt(9, player.getCost());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int updatePlayer(Connection con, Player player) {

        PreparedStatement pstmt = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            String sql = prop.getProperty("updatePlayer");
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, player.getName());
            pstmt.setInt(2, player.getLevel());
            pstmt.setInt(3, player.getHp());
            pstmt.setInt(4, player.getMaxHp());
            pstmt.setInt(5, player.getMp());
            pstmt.setInt(6, player.getMaxMp());
            pstmt.setString(7, player.getJob());
            pstmt.setString(8, player.getSkill());
            pstmt.setInt(9, player.getCost());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }  finally {
            close(pstmt);
        }

        return result;
    }

    public int deleteAll(Connection con) {

        Statement stmt = null;
        int result = 0;

        try {

            prop.loadFromXML(new FileInputStream("src/main/java/mapper/ConsoleGameMapper.xml"));
            String sql = prop.getProperty("deletePlayer");
            stmt = con.createStatement();

            result = stmt.executeUpdate(sql);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
        }

        return result;
    }
}
