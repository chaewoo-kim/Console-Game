package Controller;

import Service.WeaponService;
import items.Item;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.*;

public class WeaponController {

    WeaponService weaponService = new WeaponService();

    public Item select(String tableName) {

        Item weapon = weaponService.selectAll(tableName);

        return weapon;
    }

    public void insertItem(Item invenItem) {

        Connection con = null;

        int result = 0;

        result = weaponService.insertItem(con, invenItem);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

    public void deleteByName(String name) {

        Connection con = null;

        int result = 0;

        result = weaponService.deleteByName(con, name);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }
}
