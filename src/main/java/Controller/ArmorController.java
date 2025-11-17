package Controller;

import Service.ArmorService;
import items.Item;

import java.sql.Connection;

import static common.JDBCTemplate.*;

public class ArmorController {

    ArmorService armorService = new ArmorService();

    public void insertItem(Item invenItem) {

        Connection con = getConnection();

        int result = 0;

        result = armorService.insertItem(con, invenItem);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

    public void deleteByName(String name) {

        Connection con = getConnection();

        int result = 0;

        result = armorService.deleteByName(con, name);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

    public void deleteAll() {

        Connection con = getConnection();

        int result = 0;

        result = armorService.deleteAll(con);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }
}
