package Service;

import Repository.ItemRepository;
import items.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.getConnection;

public class ItemService {

    ItemRepository itemRepository = new ItemRepository();

    public Item selectItemByName(String name) {

        Connection con = null;
        Item item = null;

        con = getConnection();

        item = itemRepository.selectItemByName(con, name);

        return item;
    }

    public Item selectItemByIndex(int index) {

        Connection con = null;
        Item item = null;

        con = getConnection();

        item = itemRepository.selectItemByIndex(con, index);

        return item;
    }

    public List<Item> selectAllItems() {

        Connection con = null;
        List<Item> itemList = null;

        con = getConnection();

        itemList = itemRepository.selectAllItems(con);

        return itemList;
    }

    public int insertItem(Connection con, Item item) {

        return itemRepository.insertItem(con, item);
    }

    public int deleteAll(Connection con) {

        return itemRepository.deleteAll(con);
    }
}
