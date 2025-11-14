package Controller;

import Service.InventoryService;
import items.Item;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.*;

public class InventoryController {

    InventoryService inventoryService = new InventoryService();

    public List<Item> selectAll() {

        List<Item> inventoryList = inventoryService.selectAll();

        return inventoryList;
    }

    public void deleteByName(String itemName) {

        Connection con = getConnection();
        int result = 0;

        result = inventoryService.deleteByName(con, itemName);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

    public void insertItem(Item equipItem) {

        Connection con = getConnection();
        int result = 0;

        result = inventoryService.insertItem(con, equipItem);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }
}
