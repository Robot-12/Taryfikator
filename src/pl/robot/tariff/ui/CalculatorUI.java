package pl.robot.tariff.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.robot.tariff.utils.Utils;

public class CalculatorUI {

    public static Inventory inv;
    public static String InventoryName;
    public static int InventoryRows = 1*9;

    public static void initialize(){
        InventoryName = Utils.chat("&c&lMiesiące/Pieniądze");
        inv = Bukkit.createInventory(null, InventoryRows);
    }

    public static Inventory GUI (Player p){
        Inventory toReturn = Bukkit.createInventory(null, InventoryRows, InventoryName);
        Utils.createItem(inv, 166,1, InventoryRows,"&cPowrót", "","");
        Utils.createItemByteMeta(inv, 160,10,1,  3,"Miesiące ➡ Mandat","MI");
        Utils.createItemByteMeta(inv, 160,10,1,  7,"Mandat ➡ Miesiące","MA");
        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) throws NoSuchMethodException {
        if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cPowrót"))){
            p.closeInventory();
            p.openInventory(MainUI.GUI(p));
            return;
        }
        p.closeInventory();
        p.openInventory(UiAddRemoveCalculator.GUI(p,clicked.getItemMeta().getDisplayName(),clicked.getItemMeta().getLocalizedName()));
    }
}
