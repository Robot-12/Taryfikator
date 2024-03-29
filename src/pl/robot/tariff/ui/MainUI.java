package pl.robot.tariff.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.robot.tariff.Main;
import pl.robot.tariff.utils.Utils;

import java.util.*;

public class MainUI {

    public static Inventory inv;
    public static String InventoryName;
    public static int inv_rows = 9*9;

    public static void inttialize(){
    InventoryName = Utils.chat("&c&lTaryfikator ");

    inv = Bukkit.createInventory(null, inv_rows);
}

public static Inventory GUI (Player p){

    Inventory toReturn = Bukkit.createInventory(null, inv_rows, InventoryName);
    String mandate = "0";
    String months = "0";
    Map<String, Integer> madateM = new HashMap<String, Integer>();
    Map<String, Integer> monthsM = new HashMap<String, Integer>();
    if(ListUI.list.size()>0)
    {
        for (Map.Entry<String, Map<String, Integer>> entry : ListUI.list.entrySet()) {
        }
        for (Map.Entry<String, Map<String, Integer>> entry : ListUI.listMandate.entrySet())
        {
            if(entry.getValue().get(p.getName())!=null)
            {
                mandate = String.valueOf(Integer.parseInt(mandate)+entry.getValue().get(p.getName()));
                madateM = entry.getValue();
            }
        }
        for (Map.Entry<String, Map<String, Integer>> entry : ListUI.listMonths.entrySet())
        {
            if(entry.getValue().get(p.getName())!=null)
            {
                months = String.valueOf(Integer.parseInt(months)+entry.getValue().get(p.getName()));
                monthsM = entry.getValue();
            }
        }
    }
    Utils.createItemByte(inv, 160,10,1,  3, "&31.Ruch pieszych", "","");
    Utils.createItemByte(inv, 160,10,1,  7, "&32.Ruch pojazdów", "","");
    Utils.createItemByte(inv, 160,10,1,  21, "&33.Lekkie wykroczenia", "","");
    Utils.createItemByte(inv, 160,10,1,  25, "&34.Ciężkie wykroczenia", "","");
    Utils.createItemByte(inv, 160,10,1,  39, "&35.Związane z MCPD", "","");
    Utils.createItemByte(inv, 160,10,1,  43, "&36.Nielegalne substancje", "","");
    Utils.createItemByte(inv, 160,10,1,  57, "&37.Broń", "","");
    Utils.createItemByte(inv, 160,10,1,  61, "&38.Napady", "","");
    Utils.createItemByte(inv, 160,10,1,  75, "&39.Maski", "","");
    Utils.createItemByte(inv, 160,10,1,  79, "&310.Inne", "","");

    Utils.createItem(inv, 426,1,  23,"Miesiące/Pieniądze", "");
    Utils.createItemStats(inv, 340,1,  41,months,mandate,monthsM,madateM,p,"");
    Utils.createItemMeta(inv, 386,1,  59,"Pokaż wynik", mandate+","+months,"");
    Utils.createItemByte(inv, 160,14,1,  77,"&cRestart", "");


    toReturn.setContents(inv.getContents());
    return toReturn;
}

public static void ListOffenses(String OffensesName,Player p)
{
    List<?> nameOfset = Main.plugin.getConfig().getList(OffensesName+".nameOffense");
    int sizelist = nameOfset.size();
    if(OffensesName=="IllegalSubstances") {
        sizelist+=2;
    }
    if(sizelist > 0)
    {
        for (int i = 1;i <sizelist;i++)
        {
            if(sizelist<=i*3)
            {
                    ListUI.inv_rows = ((i+i-1)*9);
                    ListUI.inttialize();
                    ListUI.InventoryName = Utils.chat("&c&l"+Main.plugin.getConfig().getString(OffensesName+".name"));
                    p.closeInventory();
                    p.openInventory(ListUI.GUI(p,OffensesName));
                    break;
            }
        }
    }
    else {
        p.sendMessage("EROR:Brak danych w config.yml");
    }

    return;
}

public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv)
{
    String name = clicked.getItemMeta().getDisplayName();
    switch (ChatColor.stripColor(name))
    {
        case "1.Ruch pieszych":
            ListOffenses("PedestrianTraffic",p);
            break;
        case "2.Ruch pojazdów":
            ListOffenses("VehicleMovement",p);
            break;
        case "3.Lekkie wykroczenia":
            ListOffenses("LightOffenses",p);
            break;
        case "4.Ciężkie wykroczenia":
            ListOffenses("SeriousMisconduct",p);
            break;
        case "5.Związane z MCPD":
            ListOffenses("RelatedToTheMCPD",p);
            break;
        case "6.Nielegalne substancje":
            ListOffenses("IllegalSubstances",p);
            break;
        case "7.Broń":
            ListOffenses("Weapon",p);
            break;
        case "8.Napady":
            ListOffenses("Seizures",p);
            break;
        case "9.Maski":
            ListOffenses("Masks",p);
            break;
        case "10.Inne":
            ListOffenses("Another",p);
            break;
        case "Miesiące/Pieniądze":
            CalculatorUI.initialize();
            p.closeInventory();
            p.openInventory(CalculatorUI.GUI(p));
            break;
        case "Restart":
            ItemMeta itemMeta = inv.getItem(40).getItemMeta();

            itemMeta.setDisplayName("Brak Wybranych przewinień");
            itemMeta.setLore(Collections.singletonList(" "));
            inv.getItem(40).setItemMeta(itemMeta);
            for (Map.Entry<String, Map<String, Integer>> entry : ListUI.list.entrySet()) {
                entry.getValue().remove(p.getName());
            }
            for (Map.Entry<String, Map<String, Integer>> entry : ListUI.listMandate.entrySet())
            {
                if(entry.getValue().get(p.getName())!=null)
                {
                    entry.getValue().remove(p.getName());
                }
            }
            for (Map.Entry<String, Map<String, Integer>> entry : ListUI.listMonths.entrySet())
            {
                if(entry.getValue().get(p.getName())!=null)
                {
                    entry.getValue().remove(p.getName());
                }
            }
            for (Map.Entry<String, Map<String, String>> entry : ListUI.listName.entrySet())
            {
                if(entry.getValue().get(p.getName())!=null)
                {
                    entry.getValue().remove(p.getName());
                }
            }
            break;
        case "Pokaż wynik":
            ArrayList<String> array = new ArrayList<String>(Arrays.asList(clicked.getItemMeta().getLocalizedName().split(",")));
            for(Entity en : p.getWorld().getEntities()) {
                if(en instanceof Player) {
                    Player player = (Player) en;
                    double distance = player.getLocation().distance(p.getLocation());
                    if(distance < 10) {
                            for (Map.Entry<String, Map<String, String>> entry : ListUI.listName.entrySet()) {
                                if(entry.getValue().get(p.getName()) != null)
                                {
                                    player.sendMessage(Utils.chat("&5"+entry.getValue().get(p.getName())));
                                }
                            }
                            if(Integer.parseInt(array.get(0))>0||Integer.parseInt(array.get(1))>0)
                            {
                                if(inv.getItem(40).getItemMeta().getLore().size()>1)
                                {
                                    player.sendMessage(Utils.chat("&5"+"Suma:"));
                                    if(Integer.parseInt(array.get(0))>0)
                                    {
                                        player.sendMessage(Utils.chat("&5"+inv.getItem(40).getItemMeta().getLore().get(0)));
                                    }
                                    if(Integer.parseInt(array.get(1))>0)
                                    {
                                        player.sendMessage(Utils.chat("&5"+inv.getItem(40).getItemMeta().getLore().get(1)));
                                    }
                                    player.sendMessage(Utils.chat("&5(("+p.getName()+"))"));
                                }
                            }
                    }
                }
            }
            break;
        default:
    }
}
}
