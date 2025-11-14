package Service;

import Repository.WeaponRepository;
import items.Item;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.getConnection;

public class WeaponService {

    WeaponRepository weaponRepository = new WeaponRepository();

    public Item selectAll(String tableName) {

        Connection con = getConnection();

        Item weapon = weaponRepository.select(con, tableName);

        return weapon;
    }
}
