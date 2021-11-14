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
import pl.robot.tariff.ui.ListUI;

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

    public static ItemStack createItemMeta(Inventory inv, int materialId, int amount, int invSlot, String displayName,String MetaName, String... loreString){

        ItemStack item;
        List<String> lore = new ArrayList();

        item = new ItemStack(Material.getMaterial(materialId), amount);

        ItemMeta meta = (ItemMeta) item.getItemMeta();
        meta.setLocalizedName(MetaName);
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
            meta.setDisplayName(Utils.chat("Przewinienia: "));
            if(Integer.parseInt(mandateS)>0)
            {
                lore.add(Utils.chat("Mandat: "+mandateS));
            }
            if(Integer.parseInt(monthsS)>0)
            {
                lore.add(Utils.chat("Miesiące: "+monthsS));
            }
            for (Map.Entry<String, Map<String, String>> entry : ListUI.listName.entrySet()) {
                if(monthsM.get(p.getName()) != 0||mandateM.get(p.getName()) != 0)
                {
                    if(entry.getValue().get(p.getName()) != null)
                    {
                        lore.add(entry.getValue().get(p.getName()));
                    }
                }
            }
        }
        else
        {
            meta.setDisplayName(Utils.chat("Brak Wybranych Przewinień"));
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
