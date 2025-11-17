package Service;

import Repository.ArmorRepository;
import items.Item;

import java.sql.Connection;

public class ArmorService {

    ArmorRepository armorRepository = new ArmorRepository();

    public int insertItem(Connection con, Item invenItem) {

        int result = 0;

        result = armorRepository.insertItem(con, invenItem);

        return result;
    }

    public int deleteByName(Connection con, String name) {

        int result = 0;

        result = armorRepository.deleteByName(con, name);

        return result;
    }

    public int deleteAll(Connection con) {

        int result = 0;

        result = armorRepository.deleteAll(con);

        return result;
    }
}
