package pl.robot.tariff;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.robot.tariff.listeners.InventoryClickLisyner;
import pl.robot.tariff.ui.TestUI;
import pl.robot.tariff.uiAddRemove.AddRemoveUI;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements CommandExecutor {
    public static Logger console;
    public static Main plugin;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        super.onEnable();
        new InventoryClickLisyner(this);
        TestUI.inttialize();
        AddRemoveUI.inttialize();
        config.options().copyDefaults(true);
        saveConfig();
        plugin = this;
    }

    @Override
    public void onDisable() {
        super.onDisable();

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            console.info("Komenda jest dostepna tylko z poziomu klienta gry.");
            return true;
        }

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("test"))
        {
            if(args.length > 0){
                return false;
            }
        }
        player.openInventory(TestUI.GUI(player));
        return true;
    }

}
