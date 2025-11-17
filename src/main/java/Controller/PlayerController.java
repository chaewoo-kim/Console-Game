package Controller;

import Service.PlayerService;
import user.Player;

import java.sql.Connection;

import static common.JDBCTemplate.*;

public class PlayerController {

    PlayerService playerService = new PlayerService();

    public void insertPlayer(Player player) {

        Connection con = getConnection();

        int result = playerService.insertPlayer(con, player);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

    public void updatePlayer(Player player) {

        Connection con = getConnection();

        int result = playerService.updatePlayer(con, player);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }


    public void deleteAll(String player) {

        Connection con = getConnection();

        int result = playerService.deletePlayer(con);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }
}
