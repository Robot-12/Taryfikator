package pl.robot.tariff.uiAddRemoveCalculator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.robot.tariff.Main;
import pl.robot.tariff.uiList.ListUI;
import pl.robot.tariff.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UiAddRemoveCalculator {
    public static Inventory inv;
    public static String inventory_name = "Test";
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
                    map = ((HashMap<String, Integer>) ListUI.list.get(metaName)).get(p.getName());
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

}
