package Controller;

import Service.InventoryService;
import items.Item;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

public class InventoryController {

    InventoryService inventoryService = new InventoryService();

    public List<Item> selectAll() {

        List<Item> inventoryList = inventoryService.selectAll();

        return inventoryList;
    }

    public void deleteByName(String itemName) {

        Connection con = null;
        int result = 0;

        result = inventoryService.deleteByName(con, itemName);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }
    }
}
