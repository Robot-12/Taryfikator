package pl.robot.tariff.uiAddRemove;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.robot.tariff.Main;
import pl.robot.tariff.uiList.ListUI;
import pl.robot.tariff.utils.Utils;

import java.util.*;

import static java.lang.Integer.parseInt;

public class AddRemoveUI  {

    public static Inventory inv;
    public static String inventory_name ="Test";
    public static String metaName ="Test";
    public static int inv_rows = 4*9;

    public static void inttialize(){
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI (Player p, String OffensesName, int slot, String nameItem){
        nameItem = ChatColor.stripColor(nameItem);
        String description = "";
        String descriptionMon = "";
        List<?> nameOfset = Main.plugin.getConfig().getList(OffensesName+".nameOffense");
        List<?> months = Main.plugin.getConfig().getList(OffensesName+".months");
        List<?> mandate = Main.plugin.getConfig().getList(OffensesName+".mandate");
        inventory_name = Main.plugin.getConfig().getString(OffensesName+".name");
        int map = 0;
        String monthsMeta = "0";
        String mandateMeta = "0";
        if(nameOfset.size()>0)
        {
            int x = 0;
            for (int i = 1;i <=nameOfset.size();i++)
            {
                x += 1;
                int slot_rowse =0;
                String name = String.valueOf(nameOfset.get(i-1));
                switch(x)
                {
                    case 1:
                        slot_rowse =3;
                        break;
                    case 2:
                        slot_rowse =5;
                        break;
                    case 3:
                        slot_rowse =7;
                        x = 0;
                        break;
                    default:
                        x = 0;
                        break;
                }

                if(slot+1 ==slot_rowse+(9*(Math.round((i-1)/3)*2)))
                {
                    String displayName = (String) nameOfset.get(i-1);
                    List<String> ary = new ArrayList<String>(Arrays.asList(displayName.split("<br>")));
                    inventory_name = Utils.chat("&3"+ary.get(0)+"...");
                    metaName = ary.get(0);
                    monthsMeta = String.valueOf(months.get(i-1));
                    mandateMeta = String.valueOf(mandate.get(i-1));
                    map = ((HashMap<String, Integer>)ListUI.list.get(metaName)).get(p.getName());
                    nameItem = ary.get(0);
                    if(ary.get(0) != "Posiadanie lekkich narkotyków"){
                        if(ary.get(0) != "Posiadanie ciężkich narkotyków")
                        {
                            if(map>0)
                            {
                                if((Integer) months.get(i-1)>0)
                                {
                                    descriptionMon = "&3Mieśionce: "+(Integer) months.get(i-1)*map;
                                }
                                if((Integer) mandate.get(i-1)>0)
                                {
                                    description = "&3Mandat: "+(Integer) mandate.get(i-1)*map;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        if(nameItem == "Posiadanie lekkich narkotyków"||nameItem == "Posiadanie ciężkich narkotyków")
        {
            map = ((HashMap<String, Integer>)ListUI.list.get(nameItem)).get(p.getName());
            if(map > 0)
            {
                if(map<=Main.plugin.getConfig().getInt(OffensesName+".above"))
                {
                    List<?> toMonths = Main.plugin.getConfig().getList(OffensesName+".toMonths");
                    List<?> toMandate = Main.plugin.getConfig().getList(OffensesName+".toMandate");
                    if(nameItem == "Posiadanie lekkich narkotyków")
                    {
                        if((Integer) toMonths.get(0)>0)
                        {
                            descriptionMon = "&3Mieśionce: "+(Integer) toMonths.get(0);
                            monthsMeta = String.valueOf(toMonths.get(0));
                        }
                        if((Integer) toMandate.get(0)>0)
                        {
                            p.sendMessage("Test4");
                            description = "&3Mandat: "+(Integer) toMandate.get(0);
                            mandateMeta = String.valueOf(toMandate.get(0));
                        }
                    }
                    else
                    {
                        if((Integer) toMonths.get(1)>0)
                        {
                            descriptionMon = "&3Mieśionce: "+(Integer) toMonths.get(1);
                            monthsMeta = String.valueOf(toMonths.get(1));
                        }
                        if((Integer) toMandate.get(1)>0)
                        {
                            description = "&3Mandat: "+(Integer) toMandate.get(1);
                            mandateMeta = String.valueOf(toMandate.get(1));
                        }
                    }
                }
                else
                {
                    List<?> toMonths = Main.plugin.getConfig().getList(OffensesName+".aboveMonths");
                    List<?> toMandate = Main.plugin.getConfig().getList(OffensesName+".aboveMandate");
                    List<?> additionalMonths = Main.plugin.getConfig().getList(OffensesName+".additionalMonths");
                    List<?> additionalMandate = Main.plugin.getConfig().getList(OffensesName+".additionalMandate");
                    int x = 0;
                    if(nameItem == "Posiadanie lekkich narkotyków")
                    {
                        if((Integer) toMonths.get(0)>0||(Integer) additionalMonths.get(0)>0)
                        {
                            for(int i = 2; i<=map/Main.plugin.getConfig().getInt(OffensesName+".above"); i++)
                            {
                                x+= (Integer) additionalMonths.get(0);
                            }
                            x+=(Integer) toMonths.get(0);
                            descriptionMon = "&3Mieśionce: "+x;
                            monthsMeta = String.valueOf(x);
                        }
                        if((Integer) toMandate.get(0)>0||(Integer) additionalMandate.get(0)>0)
                        {
                            x =0;
                            for(int i = 2; i<=map/Main.plugin.getConfig().getInt(OffensesName+".above"); i++)
                            {
                                x+= (Integer) additionalMandate.get(0);
                            }
                            x+=(Integer) toMandate.get(0);
                            description = "&3Mandat: "+x;
                            mandateMeta = String.valueOf(x);
                        }
                    }
                    else
                    {
                        if((Integer) toMonths.get(1)>0)
                        {
                            for(int i = 2; i<=map/Main.plugin.getConfig().getInt(OffensesName+".above"); i++)
                            {
                                x+= (Integer) additionalMonths.get(1);
                            }
                            x+=(Integer) toMonths.get(1);
                            descriptionMon = "&3Mieśionce: "+x;
                            monthsMeta = String.valueOf(x);
                        }
                        if((Integer) toMandate.get(1)>0)
                        {
                            x =0;
                            for(int i = 2; i<=map/Main.plugin.getConfig().getInt(OffensesName+".above"); i++)
                            {
                                x+= (Integer) additionalMandate.get(1);
                            }
                            x+=(Integer) toMandate.get(1);
                            description = "&3Mandat: "+x;
                            mandateMeta = String.valueOf(x);
                        }
                    }
                }
            }
            inventory_name = Utils.chat("&3"+nameItem);
        }
        Inventory toReturn = Bukkit.createInventory(null, inv_rows,inventory_name);
        Utils.createItemByteMeta(inv, 160,14,1,  10,"&c&l-1", monthsMeta+","+mandateMeta,"");
        Utils.createItemByteMeta(inv, 160,14,1,  11,"&c&l-5", monthsMeta+","+mandateMeta,"");
        Utils.createItemByteMeta(inv, 160,14,1,  12,"&c&l-10", monthsMeta+","+mandateMeta,"");
        Utils.createItemByteMeta(inv, 160,14,1,  13,"&c&l-100", monthsMeta+","+mandateMeta,"");
        Utils.createItemMeta(inv, 340,1, 14,"&6"+map, metaName,description,descriptionMon);
        Utils.createItemByteMeta(inv, 160,5,1,  15,"&a&l+1", monthsMeta+","+mandateMeta,"");
        Utils.createItemByteMeta(inv, 160,5,1,  16,"&a&l+5", monthsMeta+","+mandateMeta,"");
        Utils.createItemByteMeta(inv, 160,5,1,  17,"&a&l+10", monthsMeta+","+mandateMeta,"");
        Utils.createItemByteMeta(inv, 160,5,1,  18,"&a&l+100", monthsMeta+","+mandateMeta,"");


        Utils.createItemMeta(inv, 166,1,  inv_rows-8,"&cPowrót", OffensesName,"");
        Utils.createItemByteMeta(inv, 35,5,1,  inv_rows,"&aPotwierdz", OffensesName,"");


        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void AddRemove(int AR, Player p, ItemStack i, ItemMeta MM, ItemStack OffensesNameItem)
    {
        String OffensesName = OffensesNameItem.getItemMeta().getLocalizedName();
        int Valiu = Integer.parseInt(ChatColor.stripColor(i.getItemMeta().getDisplayName()));
        ItemMeta meta = i.getItemMeta();
        List<String> lore = new ArrayList();
        List<String> mm = new ArrayList<String>(Arrays.asList(MM.getLocalizedName().split(",")));
        String inventory_name = i.getItemMeta().getLocalizedName();
        if(AR <0)
        {
            if(Valiu+AR >= 0)
            {
                Valiu=Valiu+AR;
            }
            else
            {
                Valiu=0;
            }
        }
        else
        {
            Valiu=Valiu+AR;
        }
        if(inventory_name == "Posiadanie lekkich narkotyków"||inventory_name=="Posiadanie ciężkich narkotyków")
        {
            p.sendMessage("1");
            if(Valiu > 0)
                {
                    if(Valiu<=Main.plugin.getConfig().getInt(OffensesName+".above"))
                    {
                        p.sendMessage("2");
                        List<?> toMonths = Main.plugin.getConfig().getList(OffensesName+".toMonths");
                        List<?> toMandate = Main.plugin.getConfig().getList(OffensesName+".toMandate");
                        if(inventory_name == "Posiadanie lekkich narkotyków")
                        {
                            p.sendMessage("3");
                            if((Integer) toMonths.get(0)>0)
                            {
                                lore.add(Utils.chat("&3Mieśionce: "+ (Integer) toMonths.get(0)));
                            }
                            if((Integer) toMandate.get(0)>0)
                            {
                                p.sendMessage("4");
                                lore.add(Utils.chat("&3Mandat: "+ (Integer) toMandate.get(0)));
                            }
                        }
                        else
                        {
                            if((Integer) toMonths.get(1)>0)
                            {
                                lore.add(Utils.chat("&3Mieśionce: "+ (Integer) toMonths.get(1)));
                            }
                            if((Integer) toMandate.get(1)>0)
                            {
                                lore.add(Utils.chat("&3Mandat: "+ (Integer) toMandate.get(1)));
                            }
                        }
                    }
                    else
                    {
                        p.sendMessage("5");
                        List<?> toMonths = Main.plugin.getConfig().getList(OffensesName+".aboveMonths");
                        List<?> toMandate = Main.plugin.getConfig().getList(OffensesName+".aboveMandate");
                        List<?> additionalMonths = Main.plugin.getConfig().getList(OffensesName+".additionalMonths");
                        List<?> additionalMandate = Main.plugin.getConfig().getList(OffensesName+".additionalMandate");
                        int x = 0;
                        if(inventory_name == "Posiadanie lekkich narkotyków")
                        {
                            p.sendMessage("6");
                            if((Integer) toMonths.get(0)>0||(Integer) additionalMonths.get(0)>0)
                            {
                                for(int ii = 2; ii<=Valiu/Main.plugin.getConfig().getInt(OffensesName+".above"); ii++)
                                {
                                    x+= (Integer) additionalMonths.get(0);
                                }
                                x+=(Integer) toMonths.get(0);
                                lore.add(Utils.chat("&3Mieśionce: "+ x));
                            }
                            if((Integer) toMandate.get(0)>0||(Integer) additionalMandate.get(0)>0)
                            {
                                p.sendMessage("7");
                                x =0;
                                for(int ii = 2; ii<=Valiu/Main.plugin.getConfig().getInt(OffensesName+".above"); ii++)
                                {
                                    p.sendMessage("8"+ii);
                                    x+= (Integer) additionalMandate.get(0);
                                }
                                x+=(Integer) toMandate.get(0);
                                lore.add(Utils.chat("&3Mandat: "+ x));
                            }
                        }
                        else
                        {
                            if((Integer) toMonths.get(1)>0)
                            {
                                for(int ii = 2; ii<=Valiu/Main.plugin.getConfig().getInt(OffensesName+".above"); ii++)
                                {
                                    x+= (Integer) additionalMonths.get(1);
                                }
                                x+=(Integer) toMonths.get(1);
                                lore.add(Utils.chat("&3Mieśionce: "+ x));
                            }
                            if((Integer) toMandate.get(1)>0)
                            {
                                x =0;
                                for(int ii = 2; ii<=Valiu/Main.plugin.getConfig().getInt(OffensesName+".above"); ii++)
                                {
                                    x+= (Integer) additionalMandate.get(1);
                                }
                                x+=(Integer) toMandate.get(1);
                                lore.add(Utils.chat("&3Mandat: "+ x));
                            }
                        }
                    }
                }
        }
        else
        {
            if(Valiu>0)
            {
                if(parseInt(mm.get(0)) >0)
                {
                    lore.add(Utils.chat("&3Mieśionce: "+ parseInt(mm.get(0))*Valiu));
                }
                if(parseInt(mm.get(1))>0)
                {
                    lore.add(Utils.chat("&3Mandat: "+ parseInt(mm.get(1))*Valiu));
                }
            }
        }
            meta.setDisplayName(Utils.chat("&6"+Valiu));
            meta.setLore(lore);
            i.setItemMeta(meta);
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) throws NoSuchMethodException {
        Map<String, Integer> map = ListUI.list.get(inv.getItem(13).getItemMeta().getLocalizedName());
        Map<String, Integer> mapMonths = ListUI.listMonths.get(inv.getItem(13).getItemMeta().getLocalizedName());
        Map<String, Integer> mapMandate = ListUI.listMandate.get(inv.getItem(13).getItemMeta().getLocalizedName());
        int months = Integer.parseInt(new ArrayList<String>(Arrays.asList(inv.getItem(12).getItemMeta().getLocalizedName().split(","))).get(0));
        int mandate = Integer.parseInt(new ArrayList<String>(Arrays.asList(inv.getItem(12).getItemMeta().getLocalizedName().split(","))).get(1));
        String name = clicked.getItemMeta().getDisplayName();
        switch (ChatColor.stripColor(name))
        {
            case "-1":
                AddRemove(-1,p,inv.getItem(13),clicked.getItemMeta(),inv.getItem(35));
            break;
            case "-5":
                AddRemove(-5,p,inv.getItem(13),clicked.getItemMeta(),inv.getItem(35));
               break;
            case "-10":
                AddRemove(-10,p,inv.getItem(13),clicked.getItemMeta(),inv.getItem(35));
                break;
            case "-100":
                AddRemove(-100,p,inv.getItem(13),clicked.getItemMeta(),inv.getItem(35));
               break;
            case "+1":
                AddRemove(1,p,inv.getItem(13),clicked.getItemMeta(),inv.getItem(35));
              break;
            case "+5":
                AddRemove(5,p,inv.getItem(13),clicked.getItemMeta(),inv.getItem(35));
              break;
            case "+10":
                AddRemove(10,p,inv.getItem(13),clicked.getItemMeta(),inv.getItem(35));
               break;
            case "+100":
                AddRemove(100,p,inv.getItem(13),clicked.getItemMeta(),inv.getItem(35));
               break;
            case "Powrót":
                p.closeInventory();
                p.openInventory(ListUI.GUI(p,clicked.getItemMeta().getLocalizedName()));
                break;
            case "Potwierdz":
                if(inv.getItem(13).getItemMeta().getLocalizedName() == "Posiadanie lekkich narkotyków"||inv.getItem(13).getItemMeta().getLocalizedName() == "Posiadanie ciężkich narkotyków")
                {
                    map.put(p.getName(), Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName())));
                    String OffensesName = inv.getItem(35).getItemMeta().getLocalizedName();
                    p.sendMessage(OffensesName);
                    p.sendMessage(String.valueOf(Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName()))));
                    p.sendMessage(String.valueOf(Main.plugin.getConfig().getInt(OffensesName+".above")));
                    p.sendMessage("1");
                    String inventory_name = inv.getItem(13).getItemMeta().getLocalizedName();
                    if(Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName())) > 0)
                    {
                        if(Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName()))<=Main.plugin.getConfig().getInt(OffensesName+".above"))
                        {
                            p.sendMessage("2");
                            List<?> toMonths = Main.plugin.getConfig().getList(OffensesName+".toMonths");
                            List<?> toMandate = Main.plugin.getConfig().getList(OffensesName+".toMandate");
                            if(inventory_name == "Posiadanie lekkich narkotyków")
                            {
                                p.sendMessage("3");
                                if((Integer) toMonths.get(0)>0)
                                {
                                    mapMonths.put(p.getName(), (Integer) toMonths.get(0));
                                }
                                if((Integer) toMandate.get(0)>0)
                                {
                                    p.sendMessage("4");
                                    mapMandate.put(p.getName(), (Integer) toMandate.get(0));
                                }
                            }
                            else
                            {
                                if((Integer) toMonths.get(1)>0)
                                {
                                    mapMonths.put(p.getName(), (Integer) toMonths.get(1));
                                }
                                if((Integer) toMandate.get(1)>0)
                                {
                                    mapMandate.put(p.getName(), (Integer) toMandate.get(1));
                                }
                            }
                        }
                        else
                        {
                            p.sendMessage("5");
                            List<?> toMonths = Main.plugin.getConfig().getList(OffensesName+".aboveMonths");
                            List<?> toMandate = Main.plugin.getConfig().getList(OffensesName+".aboveMandate");
                            List<?> additionalMonths = Main.plugin.getConfig().getList(OffensesName+".additionalMonths");
                            List<?> additionalMandate = Main.plugin.getConfig().getList(OffensesName+".additionalMandate");
                            int x = 0;
                            if(inventory_name == "Posiadanie lekkich narkotyków")
                            {
                                p.sendMessage("6");
                                if((Integer) toMonths.get(0)>0||(Integer) additionalMonths.get(0)>0)
                                {
                                    for(int ii = 2; ii<=Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName()))/Main.plugin.getConfig().getInt(OffensesName+".above"); ii++)
                                    {
                                        x+= (Integer) additionalMonths.get(0);
                                    }
                                    x+=(Integer) toMonths.get(0);
                                    mapMonths.put(p.getName(), x);
                                }
                                if((Integer) toMandate.get(0)>0||(Integer) additionalMandate.get(0)>0)
                                {
                                    p.sendMessage("7");
                                    x =0;
                                    for(int ii = 2; ii<=Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName()))/Main.plugin.getConfig().getInt(OffensesName+".above"); ii++)
                                    {
                                        p.sendMessage("8"+ii);
                                        x+= (Integer) additionalMandate.get(0);
                                    }
                                    x+=(Integer) toMandate.get(0);
                                    mapMandate.put(p.getName(), x);
                                }
                            }
                            else
                            {
                                if((Integer) toMonths.get(1)>0)
                                {
                                    for(int ii = 2; ii<=Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName()))/Main.plugin.getConfig().getInt(OffensesName+".above"); ii++)
                                    {
                                        x+= (Integer) additionalMonths.get(1);
                                    }
                                    x+=(Integer) toMonths.get(1);
                                    mapMonths.put(p.getName(), x);
                                }
                                if((Integer) toMandate.get(1)>0)
                                {
                                    x =0;
                                    for(int ii = 2; ii<=Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName()))/Main.plugin.getConfig().getInt(OffensesName+".above"); ii++)
                                    {
                                        x+= (Integer) additionalMandate.get(1);
                                    }
                                    x+=(Integer) toMandate.get(1);
                                    mapMandate.put(p.getName(), x);
                                }
                            }
                        }
                    }
                }
                else
                {
                    map.put(p.getName(), Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName())));
                    mapMonths.put(p.getName(), Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName()))*months);
                    mapMandate.put(p.getName(), Integer.valueOf(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName()))*mandate);
                }
                p.closeInventory();
                p.openInventory(ListUI.GUI(p,clicked.getItemMeta().getLocalizedName()));
                break;
            default:
                p.sendMessage("Kliknełes coś co nie zostało doane jeszczsze");
        }
    }
}
