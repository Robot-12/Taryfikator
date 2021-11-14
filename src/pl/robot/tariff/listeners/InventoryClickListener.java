package pl.robot.tariff.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.robot.tariff.Main;
import pl.robot.tariff.ui.*;

public class InventoryClickListener implements Listener {

    private Main plugin;

    public InventoryClickListener(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) throws NoSuchMethodException
    {

        String title = e.getInventory().getTitle();
        if(e.getCurrentItem().getItemMeta() != null)
        {
            if(TryProcessUI(e, title, MainUI.InventoryName))
            {
                MainUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
            else if(TryProcessUI(e, title, ListUI.InventoryName))
            {
                ListUI.clicked((Player) e.getWhoClicked(), e.getSlot(),e.getCurrentItem(),e.getInventory());
            }
            else if(TryProcessUI(e, title, AddRemoveUI.InventoryName))
            {
                AddRemoveUI.clicked((Player) e.getWhoClicked(), e.getSlot(),e.getCurrentItem(),e.getInventory());
            }
            else if(TryProcessUI(e, title, CalculatorUI.InventoryName))
            {
                CalculatorUI.clicked((Player) e.getWhoClicked(), e.getSlot(),e.getCurrentItem(),e.getInventory());
            }
            else if(TryProcessUI(e, title, UiAddRemoveCalculator.InventoryName))
            {
                UiAddRemoveCalculator.clicked((Player) e.getWhoClicked(), e.getSlot(),e.getCurrentItem(),e.getInventory());
            }
        }
    }

    Boolean TryProcessUI(InventoryClickEvent e, String targetTitle, String title)
    {
        if(title.equals(targetTitle))
        {
                e.setCancelled(true);

                return e.getCurrentItem() != null;
        }

        return false;
    }
}
