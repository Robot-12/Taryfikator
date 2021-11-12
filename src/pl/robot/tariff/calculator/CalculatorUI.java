package pl.robot.tariff.calculator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.robot.tariff.ui.TestUI;
import pl.robot.tariff.utils.Utils;

public class CalculatorUI {

    public static Inventory inv;
    public static String inventory_name = "Test";
    public static int inv_rows = 1*9;

    public static void inttialize(){
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI (Player p){
        Inventory toReturn = Bukkit.createInventory(null, inv_rows,inventory_name);
        Utils.createItem(inv, 166,1,  inv_rows,"&cPowrót", "","");
        Utils.createItemByte(inv, 160,10,1,  3,"Miesionce ➡ Mandat","");
        Utils.createItemByte(inv, 160,10,1,  7,"Mandat ➡ Miesionce","");
        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) throws NoSuchMethodException {
        if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cPowrót"))){
            p.closeInventory();
            p.openInventory(TestUI.GUI(p));
            return;
        }
    }
}
