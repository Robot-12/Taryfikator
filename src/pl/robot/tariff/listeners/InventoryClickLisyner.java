package pl.robot.tariff.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.robot.tariff.Main;
import pl.robot.tariff.ui.TestUI;
import pl.robot.tariff.uiAddRemove.AddRemoveUI;
import pl.robot.tariff.uiList.ListUI;

public class InventoryClickLisyner implements Listener {

    private Main plugin;

    public InventoryClickLisyner(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) throws NoSuchMethodException {
        String title = e.getInventory().getTitle();
    if(title.equals(TestUI.inventory_name)){
        e.setCancelled(true);
        if(e.getCurrentItem() == null){
            return;
        }
        if(title.equals(TestUI.inventory_name)){
            TestUI.clicked((Player) e.getWhoClicked(), e.getSlot(),e.getCurrentItem(),e.getInventory());
        }
    }
        if(title.equals(ListUI.inventory_name)){
            e.setCancelled(true);
            if(e.getCurrentItem() == null){
                return;
            }
            if(title.equals(ListUI.inventory_name)){
                ListUI.clicked((Player) e.getWhoClicked(), e.getSlot(),e.getCurrentItem(),e.getInventory());
            }
        }
        if(title.equals(AddRemoveUI.inventory_name)){
            e.setCancelled(true);
            if(e.getCurrentItem() == null){
                return;
            }
            if(title.equals(AddRemoveUI.inventory_name)){
                AddRemoveUI.clicked((Player) e.getWhoClicked(), e.getSlot(),e.getCurrentItem(),e.getInventory());
            }
        }
    }
}
