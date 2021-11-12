package pl.robot.tariff.uiList;

import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.PacketPlayOutOpenSignEditor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.robot.tariff.Main;
import pl.robot.tariff.ui.TestUI;
import pl.robot.tariff.uiAddRemove.AddRemoveUI;
import pl.robot.tariff.utils.Utils;

import java.util.*;

public class ListUI  {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 1*9;
    public static Map<String, Map<String, Integer>> list= new HashMap<String, Map<String, Integer>>();
    public static Map<String, Map<String, Integer>> listMonths= new HashMap<String, Map<String, Integer>>();
    public static Map<String, Map<String, Integer>> listMandate= new HashMap<String, Map<String, Integer>>();
    public static Map<String, Map<String, String>> listName= new HashMap<String, Map<String, String>>();

    //public static Map<String, Integer> innerMap = new HashMap<String, Integer>();

    public static void inttialize(){
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI (Player p,String OffensesName){
        Inventory toReturn = Bukkit.createInventory(null, inv_rows,inventory_name);
        List<String> nameOfset = (List<String>) Main.plugin.getConfig().getList(OffensesName+".nameOffense");
        List<String> months = (List<String>) Main.plugin.getConfig().getList(OffensesName+".months");
        List<String> mandate = (List<String>) Main.plugin.getConfig().getList(OffensesName+".mandate");
        if(OffensesName=="IllegalSubstances")
        {
            if(nameOfset.get(nameOfset.size()-1)!="Posiadanie ciężkich narkotyków")
            {
                nameOfset.add("Posiadanie lekkich narkotyków");
                nameOfset.add("Posiadanie ciężkich narkotyków");
                months.add("0");
                months.add("0");
                mandate.add("0");
                mandate.add("0");
            }
        }
        if(nameOfset.size()>0)
        {
            int x = 0;
            for (int i = 1;i <=nameOfset.size();i++)
            {
                x += 1;
                int slot_rowse =0;
                String name = String.valueOf(nameOfset.get(i-1));
                String month = String.valueOf(months.get(i-1));
                String mandated = String.valueOf(mandate.get(i-1));
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
                    Utils.createItemByteArray(inv, 160,10,1,  slot_rowse+(9*(Math.round((i-1)/3)*2)), name,month,mandated,OffensesName);
            }
        }
        Utils.createItem(inv, 166,1,  inv_rows,"&cPowrót", "","");


        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) throws NoSuchMethodException {
        if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cPowrót"))){
            p.closeInventory();
            p.openInventory(TestUI.GUI(p));
            return;
        }
        String name = clicked.getItemMeta().getDisplayName();
        List<?> nameOfset = Main.plugin.getConfig().getList("PedestrianTraffic.nameOffense");
        p.closeInventory();
        String itemMetaName =ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
        if(list.get(itemMetaName)==null||list.get(itemMetaName).get(p.getName())==null)
        {
            if(list.get(itemMetaName)==null){
                Map<String, Integer> newMap = new HashMap<String, Integer>();
                newMap.put(p.getName(), 0);
                list.put(itemMetaName,newMap);
            }
            else
            {
                Map<String, Integer> newMap = list.get(itemMetaName);
                newMap.put(p.getName(), 0);
                list.put(itemMetaName,newMap);
            }
        }
        if(listMonths.get(itemMetaName)==null||listMonths.get(itemMetaName).get(p.getName())==null)
        {
            if(listMonths.get(itemMetaName)==null){
                Map<String, Integer> newMap = new HashMap<String, Integer>();
                newMap.put(p.getName(), 0);
                listMonths.put(itemMetaName,newMap);
            }
            else
            {
                Map<String, Integer> newMap = listMonths.get(itemMetaName);
                newMap.put(p.getName(), 0);
                listMonths.put(itemMetaName,newMap);
            }
        }
        if(listMandate.get(itemMetaName)==null||listMandate.get(itemMetaName).get(p.getName())==null)
        {
            if(listMandate.get(itemMetaName)==null){
                Map<String, Integer> newMap = new HashMap<String, Integer>();
                newMap.put(p.getName(), 0);
                listMandate.put(itemMetaName,newMap);
            }
            else
            {
                Map<String, Integer> newMap = listMandate.get(itemMetaName);
                newMap.put(p.getName(), 0);
                listMandate.put(itemMetaName,newMap);
            }
        }
        if(listName.get(itemMetaName)==null||listName.get(itemMetaName).get(p.getName())==null)
        {
            if(listName.get(itemMetaName)==null){
                Map<String, String> newMap = new HashMap<String, String>();
                newMap.put(p.getName(), "");
                listName.put(itemMetaName,newMap);
            }
            else
            {
                Map<String, String> newMap = listName.get(itemMetaName);
                newMap.put(p.getName(), "");
                listName.put(itemMetaName,newMap);
            }
        }
        p.openInventory(AddRemoveUI.GUI(p,clicked.getItemMeta().getLocalizedName(), slot,clicked.getItemMeta().getDisplayName()));
//        Block b = p.getWorld().getBlockAt(p.getLocation());
//        b.setTypeId(63);
//        if (b.getState() instanceof Sign) {
//            Sign s = (Sign) b.getState();
//            s.setLine(1, "Nom du joueur");
//
//           PacketPlayOutOpenSignEditor packet = new PacketPlayOutOpenSignEditor(new BlockPosition(s.getX(),s.getY(),s.getZ()));
//            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
//
//        }
        for(int i = 0;i <=nameOfset.size();i++)
        {
            String name0 = (String) nameOfset.get(i);
            if(name0 == ChatColor.stripColor(name))
            {
                p.closeInventory();
                break;
            }
        }
    }

}
