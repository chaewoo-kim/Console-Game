package Service;

import Repository.WeaponRepository;
import items.Item;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class WeaponService {

    WeaponRepository weaponRepository = new WeaponRepository();

    public Item selectAll(String tableName) {

        Connection con = getConnection();

        Item weapon = weaponRepository.select(con, tableName);

        close(con);

        return weapon;
    }

    public int insertItem(Connection con, Item invenItem) {

        int result = 0;

        result = weaponRepository.insertItem(con, invenItem);

        return result;
    }

    public int deleteByName(Connection con, String name) {

        int result = 0;

        result = weaponRepository.deleteByName(con, name);

        return result;
    }

    public int deleteAll(Connection con) {

        int result = 0;

        result = weaponRepository.deleteAll(con);

        return result;
    }
}
