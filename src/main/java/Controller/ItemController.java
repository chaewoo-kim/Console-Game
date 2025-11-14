package Controller;

import Service.ItemService;
import items.Item;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.*;

public class ItemController {

    ItemService itemService = new ItemService();

    public List<Item> selectAllItems() {

        List<Item> itemList = null;

        itemList = itemService.selectAllItems();

        return itemList;
    }

    public Item selectItemByName(String name) {

        Item item = null;

        item = itemService.selectItemByName(name);

        return item;
    }

    public Item selectItemByIndex(int index) {

        Item item = null;

        item = itemService.selectItemByIndex(index);

        return item;
    }

    public void insertItem(Item item) {

        Connection con = getConnection();

        int result = itemService.insertItem(con, item);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

    public void deleteAll(String tableName) {

        Connection con = getConnection();

        int result = itemService.deleteAll(con);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }
}
