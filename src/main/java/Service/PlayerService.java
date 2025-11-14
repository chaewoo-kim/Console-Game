package Service;

import Repository.PlayerRepository;
import user.Player;

import java.sql.Connection;

public class PlayerService {

    PlayerRepository playerRepository = new PlayerRepository();

    public int insertPlayer(Connection con, Player player) {

        return playerRepository.insertPlayer(con, player);
    }

    public int updatePlayer(Connection con, Player player) {

        return playerRepository.updatePlayer(con, player);
    }

    public int deletePlayer(Connection con) {

        return playerRepository.deleteAll(con);
    }
}
