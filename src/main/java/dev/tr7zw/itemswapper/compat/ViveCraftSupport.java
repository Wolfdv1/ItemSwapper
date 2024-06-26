package dev.tr7zw.itemswapper.compat;

import dev.tr7zw.itemswapper.config.Config;
import dev.tr7zw.itemswapper.config.ConfigManager;
import dev.tr7zw.itemswapper.ItemSwapperBase;

public class ViveCraftSupport {

    private static final ViveCraftSupport INSTANCE = new ViveCraftSupport();
    private final ConfigManager configManager = ConfigManager.getInstance();
    private boolean isAvailable = false;

    private ViveCraftSupport() {
    }

    public void init() {
        Config config = configManager.getConfig();
        isAvailable = true;

        if (!config.vivecraftCompat) {
            ItemSwapperBase.LOGGER.info("ViveCraft support is explicitly disabled!");
            return;
        }

        ItemSwapperBase.LOGGER.info("ViveCraft support enabled!");
    }

    public static ViveCraftSupport getInstance() {
        return INSTANCE;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean isActive() {
        return isAvailable() && ConfigManager.getInstance().getConfig().vivecraftCompat;
    }

}
