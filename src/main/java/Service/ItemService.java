package Service;

import Repository.ItemRepository;
import items.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class ItemService {

    ItemRepository itemRepository = new ItemRepository();

    public Item selectItemByName(String name) {

        Connection con = getConnection();
        Item item = null;

        item = itemRepository.selectItemByName(con, name);

        close(con);

        return item;
    }

    public Item selectItemByIndex(int index) {

        Connection con = getConnection();
        Item item = null;

        item = itemRepository.selectItemByIndex(con, index);

        close(con);

        return item;
    }

    public List<Item> selectAllItems() {

        Connection con = getConnection();
        List<Item> itemList = null;

        itemList = itemRepository.selectAllItems(con);

        close(con);

        return itemList;
    }

    public int insertItem(Connection con, Item item) {

        return itemRepository.insertItem(con, item);
    }

    public int deleteAll(Connection con) {

        return itemRepository.deleteAll(con);
    }
}
