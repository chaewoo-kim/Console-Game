package Service;

import Repository.InventoryRepository;
import items.Item;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class InventoryService {

    InventoryRepository inventoryRepository = new InventoryRepository();

    public List<Item> selectAll() {

        Connection con = getConnection();

        List<Item> inventoryList = inventoryRepository.selectAll(con);

        close(con);

        return inventoryList;
    }

    public int deleteByName(Connection con, String itemName) {

        int result = 0;

        result = inventoryRepository.deleteByName(con, itemName);

        return result;
    }

    public int insertItem(Connection con, Item equipItem) {

        int result = 0;

        result = inventoryRepository.insertItem(con, equipItem);

        return result;
    }

    public Item selectByName(String name) {

        Connection con = getConnection();

        Item item = inventoryRepository.selectByName(con, name);

        return item;
    }
}
