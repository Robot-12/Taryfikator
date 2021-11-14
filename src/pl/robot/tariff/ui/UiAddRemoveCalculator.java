package pl.robot.tariff.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.robot.tariff.Main;
import pl.robot.tariff.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class UiAddRemoveCalculator {
    public static Inventory inv;
    public static String InventoryName = "0";
    public static int inv_rows = 4 * 9;

    public static void initialize() {
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI(Player p, String nameItem,String Typ) {
        InventoryName = Utils.chat("&c&l"+nameItem);
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, InventoryName);

        Utils.createItemByte(inv, 160, 14, 1, 10, "&c&l-1", "");
        Utils.createItemByte(inv, 160, 14, 1, 11, "&c&l-5", "");
        Utils.createItemByte(inv, 160, 14, 1, 12, "&c&l-10", "");
        Utils.createItemByte(inv, 160, 14, 1, 13, "&c&l-100", "");
        Utils.createItemMeta(inv, 340,1, 14,"&60",Typ);
        Utils.createItemByte(inv, 160, 5, 1, 15, "&a&l+1", "");
        Utils.createItemByte(inv, 160, 5, 1, 16, "&a&l+5", "");
        Utils.createItemByte(inv, 160, 5, 1, 17, "&a&l+10", "");
        Utils.createItemByte(inv, 160, 5, 1, 18, "&a&l+100", "");


        Utils.createItem(inv, 166, 1, inv_rows, "&cPowrót", "");


        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void AddRemove(int AR, Player p, ItemStack i, String nameinv)
    {
        int Valiu = Integer.parseInt(ChatColor.stripColor(i.getItemMeta().getDisplayName()));
        ItemMeta meta = i.getItemMeta();
        List<String> lore = new ArrayList();
        if(Valiu+AR>0)
        {
            Valiu += AR;
        }
        else
        {
            Valiu = 0;
        }
        if(i.getItemMeta().getLocalizedName().equals("MI"))
        {
            meta.setDisplayName(Utils.chat("&6"+Valiu));
            lore.add(Valiu+"m");
            lore.add((Valiu * Main.plugin.getConfig().getInt("Exchange.Valiu"))+"$");
        }
        else
        {
            meta.setDisplayName(Utils.chat("&6"+Valiu));
            lore.add((Valiu%Main.plugin.getConfig().getInt("Exchange.Valiu"))+"$");
            lore.add((Valiu/Main.plugin.getConfig().getInt("Exchange.Valiu"))+"m");
        }
        meta.setLore(lore);
        i.setItemMeta(meta);
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) throws NoSuchMethodException {
        String name = clicked.getItemMeta().getDisplayName();
        switch(ChatColor.stripColor(name))
        {
            case "-1":
                AddRemove(-1,p,inv.getItem(13),inv.getName());
                break;
            case "-5":
                AddRemove(-5,p,inv.getItem(13),inv.getName());
                break;
            case "-10":
                AddRemove(-10,p,inv.getItem(13),inv.getName());
                break;
            case "-100":
                AddRemove(-100,p,inv.getItem(13),inv.getName());
                break;
            case "+1":
                AddRemove(1,p,inv.getItem(13),inv.getName());
                break;
            case "+5":
                AddRemove(5,p,inv.getItem(13),inv.getName());
                break;
            case "+10":
                AddRemove(10,p,inv.getItem(13),inv.getName());
                break;
            case "+100":
                AddRemove(100,p,inv.getItem(13),inv.getName());
                break;
        case "Powrót":
            p.closeInventory();
            p.openInventory(CalculatorUI.GUI(p));
            break;
        default:
    }
}

}
