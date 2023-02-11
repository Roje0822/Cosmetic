package com.roje.cosmetic;

import com.roje.cosmetic.command.CosMeticcmd;
import org.bukkit.plugin.java.JavaPlugin;

public class CosMeticMain extends JavaPlugin {

    private static CosMeticMain plugin;

    @Override
    public void onEnable() {

        init();
    }

    private void init() {

        plugin = this;

        // Command
        getCommand("코스튬").setExecutor(new CosMeticcmd());
    }


    public static JavaPlugin getPlugin(){
        return plugin;
    }
}
