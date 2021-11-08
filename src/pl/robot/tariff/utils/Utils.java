package pl.robot.tariff.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.robot.tariff.Main;
import pl.robot.tariff.uiList.ListUI;

import static java.lang.Integer.parseInt;

public class Utils {

    public static String chat(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ItemStack createItem(Inventory inv, int materialId, int amount, int invSlot, String displayName, String... loreString){

        ItemStack item;
        List<String> lore = new ArrayList();

        item = new ItemStack(Material.getMaterial(materialId), amount);

        ItemMeta meta = (ItemMeta) item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot-1, item);
        return item;
    }

    public static ItemStack createItemMeta(Inventory inv, int materialId, int amount, int invSlot, String displayName,String OffensesName, String... loreString){

        ItemStack item;
        List<String> lore = new ArrayList();

        item = new ItemStack(Material.getMaterial(materialId), amount);

        ItemMeta meta = (ItemMeta) item.getItemMeta();
        meta.setLocalizedName(OffensesName);
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot-1, item);
        return item;
    }

    public static ItemStack createItemByte(Inventory inv, int materialId,int byteId, int amount, int invSlot, String displayName, String... loreString){

        ItemStack item;
        List<String> lore = new ArrayList();

        item = new ItemStack(Material.getMaterial(materialId), amount, (short) byteId);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot-1, item);
        return item;
    }

    public static ItemStack createItemByteMeta(Inventory inv, int materialId,int byteId, int amount, int invSlot, String displayName,String OffensesName, String... loreString){

        ItemStack item;
        List<String> lore = new ArrayList();

        item = new ItemStack(Material.getMaterial(materialId), amount, (short) byteId);

        ItemMeta meta = item.getItemMeta();
        meta.setLocalizedName(OffensesName);
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot-1, item);
        return item;
    }

    public static ItemStack createItemByteArray(Inventory inv, int materialId, int byteId, int amount, int invSlot, String displayName, String months,String mandate,String OffensesName){

        ItemStack item;
        List<String> lore = new ArrayList();
        List<String> ary = new ArrayList<String>(Arrays.asList(displayName.split("<br>")));
        item = new ItemStack(Material.getMaterial(materialId), amount, (short) byteId);
        ItemMeta meta = item.getItemMeta();
        meta.setLocalizedName(OffensesName);
        meta.setDisplayName(Utils.chat("&3"+ary.get(0)));
        for(int z = 1; z <ary.size();z++)
        {
            lore.add(Utils.chat("&3"+ary.get(z)));
        }
        if(parseInt(mandate)  > 0 )
        {
            lore.add(Utils.chat("&6Mandat: "+mandate));
        }
        if (parseInt(months) > 0)
        {
            lore.add(Utils.chat("&6Miesiące: "+months));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot-1, item);
        return item;
    }
    public static ItemStack createItemStats(Inventory inv, int materialId, int amount, int invSlot, String monthsS, String mandateS, Map<String,Integer> monthsM, Map<String,Integer> mandateM, Player p, String... loreString){

        ItemStack item;
        List<String> lore = new ArrayList();

        item = new ItemStack(Material.getMaterial(materialId), amount);

        ItemMeta meta = (ItemMeta) item.getItemMeta();
        if(Integer.parseInt(mandateS)>0||Integer.parseInt(monthsS)>0)
        {
            meta.setDisplayName(Utils.chat("Pszewinienia: "));
            if(Integer.parseInt(mandateS)>0)
            {
                lore.add(Utils.chat("Mandat: "+mandateS));
            }
            if(Integer.parseInt(monthsS)>0)
            {
                lore.add(Utils.chat("Miesiące: "+monthsS));
            }
            for (Map.Entry<String, Map<String, Integer>> entry : ListUI.list.entrySet()) {
                if(monthsM.get(p.getName()) != 0||mandateM.get(p.getName()) != 0)
                {
                    if(entry.getValue().containsKey(p.getName()))
                    {
                        p.sendMessage(entry.getKey());
                        if(entry.getKey() == "Posiadanie lekkich narkotyków"||entry.getKey()=="Posiadanie ciężkich narkotyków")
                        {
                            if(entry.getValue().get(p.getName()) > 0)
                            {
                                if(entry.getValue().get(p.getName())<= Main.plugin.getConfig().getInt("IllegalSubstances.above"))
                                {
                                    p.sendMessage("2");
                                    List<?> toNameOfes = Main.plugin.getConfig().getList("IllegalSubstances.toNameOffense");
                                    List<?> toMonths = Main.plugin.getConfig().getList("IllegalSubstances.toMonths");
                                    List<?> toMandate = Main.plugin.getConfig().getList("IllegalSubstances.toMandate");
                                    String Madante = "";
                                    String Months = "";
                                    if(entry.getKey() == "Posiadanie lekkich narkotyków")
                                    {
                                        p.sendMessage("3");
                                        if((Integer) toMonths.get(0)>0)
                                        {
                                            Months = String.valueOf(toMonths.get(0));
                                        }
                                        if((Integer) toMandate.get(0)>0)
                                        {
                                            p.sendMessage("4");
                                            Madante = String.valueOf(toMandate.get(0));
                                        }
                                        lore.add(Utils.chat(toNameOfes.get(0)+"/"+Madante+"$ "+Months+"m"));
                                    }
                                    else
                                    {
                                        if((Integer) toMonths.get(1)>0)
                                        {
                                            Months = String.valueOf(toMonths.get(1));
                                        }
                                        if((Integer) toMandate.get(1)>0)
                                        {
                                            Madante = String.valueOf(toMandate.get(1));
                                        }
                                        lore.add(Utils.chat(toNameOfes.get(1)+"/"+Madante+"$ "+Months+"m"));
                                    }
                                }
                                else
                                {
                                    p.sendMessage("5");
                                    List<?> toMonths = Main.plugin.getConfig().getList("IllegalSubstances.aboveMonths");
                                    List<?> toMandate = Main.plugin.getConfig().getList("IllegalSubstances.aboveMandate");
                                    List<?> additionalMonths = Main.plugin.getConfig().getList("IllegalSubstances.additionalMonths");
                                    List<?> additionalMandate = Main.plugin.getConfig().getList("IllegalSubstances.additionalMandate");
                                    List<?> toNameOfes = Main.plugin.getConfig().getList("IllegalSubstances.aboveNameOffense");
                                    String Madante = "";
                                    String Months = "";
                                    int x = 0;
                                    if(entry.getKey() == "Posiadanie lekkich narkotyków")
                                    {
                                        p.sendMessage("6");
                                        if((Integer) toMonths.get(0)>0||(Integer) additionalMonths.get(0)>0)
                                        {
                                            for(int ii = 2; ii<=entry.getValue().get(p.getName())/Main.plugin.getConfig().getInt("IllegalSubstances.above"); ii++)
                                            {
                                                x+= (Integer) additionalMonths.get(0);
                                            }
                                            x+=(Integer) toMonths.get(0);
                                            Months = String.valueOf(x);
                                        }
                                        if((Integer) toMandate.get(0)>0||(Integer) additionalMandate.get(0)>0)
                                        {
                                            p.sendMessage("7");
                                            x =0;
                                            for(int ii = 2; ii<=entry.getValue().get(p.getName())/Main.plugin.getConfig().getInt("IllegalSubstances.above"); ii++)
                                            {
                                                p.sendMessage("8"+ii);
                                                x+= (Integer) additionalMandate.get(0);
                                            }
                                            x+=(Integer) toMandate.get(0);
                                            Months = String.valueOf(x);
                                        }
                                        lore.add(Utils.chat(toNameOfes.get(1)+"/"+Madante+"$ "+Months+"m"));
                                    }
                                    else
                                    {
                                        if((Integer) toMonths.get(1)>0)
                                        {
                                            for(int ii = 2; ii<=entry.getValue().get(p.getName())/Main.plugin.getConfig().getInt("IllegalSubstances.above"); ii++)
                                            {
                                                x+= (Integer) additionalMonths.get(1);
                                            }
                                            x+=(Integer) toMonths.get(1);
                                            Months = String.valueOf(x);
                                        }
                                        if((Integer) toMandate.get(1)>0)
                                        {
                                            x =0;
                                            for(int ii = 2; ii<=entry.getValue().get(p.getName())/Main.plugin.getConfig().getInt("IllegalSubstances.above"); ii++)
                                            {
                                                x+= (Integer) additionalMandate.get(1);
                                            }
                                            x+=(Integer) toMandate.get(1);
                                            Months = String.valueOf(x);
                                        }
                                        lore.add(Utils.chat(toNameOfes.get(1)+"/"+Madante+"$ "+Months+"m"));
                                    }
                                }
                            }
                        }
                        else
                        {
                            lore.add(Utils.chat(entry.getKey() + "/" +mandateM.get(p.getName()) * Integer.parseInt(String.valueOf(entry.getValue().get(p.getName())))+"$ "+ monthsM.get(p.getName()) * Integer.parseInt(String.valueOf(entry.getValue().get(p.getName())))+"m "));
                        }
                    }
                }
            }
        }
        else
        {
            meta.setDisplayName(Utils.chat("Brak Wybranych pszewinień"));
        }
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot-1, item);
        return item;
    }
}
