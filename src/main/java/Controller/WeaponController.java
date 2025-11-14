package Controller;

import Service.WeaponService;
import items.Item;

import java.util.List;

public class WeaponController {

    WeaponService weaponService = new WeaponService();

    public Item select(String tableName) {

        Item weapon = weaponService.selectAll(tableName);

        return weapon;
    }
}
