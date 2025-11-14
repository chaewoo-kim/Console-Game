package Service;

import Repository.InventoryRepository;
import items.Item;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.getConnection;

public class InventoryService {

    InventoryRepository inventoryRepository = new InventoryRepository();

    public List<Item> selectAll() {

        Connection con = getConnection();

        List<Item> inventoryList = inventoryRepository.selectAll(con);

        return inventoryList;
    }

    public int deleteByName(Connection con, String itemName) {

        int result = 0;

        result = inventoryRepository.deleteByName(con, itemName);

        return result;
    }
}
