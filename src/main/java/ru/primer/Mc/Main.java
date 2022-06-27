package ru.primer.Mc;

import org.bukkit.plugin.java.JavaPlugin;
import ru.primer.Mc.event.BlockBreak;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
