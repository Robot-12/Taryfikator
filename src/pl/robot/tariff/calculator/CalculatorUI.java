package pl.robot.tariff.calculator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.robot.tariff.Main;
import pl.robot.tariff.utils.Utils;

import java.util.List;

public class CalculatorUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 1*9;

    public static void inttialize(){
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI (Player p, String OffensesName){
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

}
