package pl.robot.tariff.AnvilUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.robot.tariff.Main;
import pl.robot.tariff.ui.TestUI;
import pl.robot.tariff.utils.Utils;

import java.util.List;

public class UiAnvil {

    public static Inventory inv;
    public static String inventory_name;

    public static void inttialize(){
        inv = Bukkit.createInventory(null, InventoryType.ANVIL,"Wpisz dane");
    }

    public static Inventory GUI (Player p, String OffensesName){
        Inventory toReturn = Bukkit.createInventory(null,InventoryType.ANVIL, "Wpisz dane");
//        Utils.createItemByte(inv, 160,10,1,  1, "1.Ruch pieszych", "","");
//        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv){
        if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&3Powrót"))){
            p.closeInventory();
            p.openInventory(TestUI.GUI(p));
        }
        String name = clicked.getItemMeta().getDisplayName();
        p.sendMessage(name);
        List<?> nameOfset = Main.plugin.getConfig().getList("CategoryList."+inv.getName());
        switch (ChatColor.stripColor(inv.getName()))
        {
            case "1.Ruch pieszych":
                nameOfset = Main.plugin.getConfig().getList("PedestrianTraffic.nameOffense");
                break;
            case "2.Ruch pojazdów":
                nameOfset = Main.plugin.getConfig().getList("VehicleMovement.nameOffense");
                break;
            default:
                p.sendMessage("EROR:BRak dancyh");
                break;
        }
        p.sendMessage(String.valueOf(nameOfset.size()));
        for(int i = 0;i <=nameOfset.size();i++)
        {
            if(nameOfset.get(i) == name)
            {
                p.sendMessage((String) nameOfset.get(i));
                break;
            }
        }
    }
}
