package com.github.mmm1245.thermalfuntestaddon;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.github.mmm1245.thermalfun.api.Ability;
import com.github.mmm1245.thermalfun.api.CountUpgradeItem;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class ThermalFunTestAddon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        Ability ability = new Ability(new NamespacedKey(this, "test_ability"), "Test ability", 65, e -> {
            if(Ability.checkHasHeatAndDecrease(e.getPlayer(), 65))
                e.getPlayer().sendMessage("Used ability");
        }, null);
        ability.register();

        ItemGroup group = new ItemGroup(
                new NamespacedKey(this, "test_category"),
                new SlimefunItemStack("TEST_CATEGORY", Material.MAGMA_CREAM, "&cTest Category")
        );
        SlimefunItem testUpgrade = new CountUpgradeItem(
                ability,
                group,
                "TEST_UPGRADE",
                Material.SKULL_BANNER_PATTERN,
                "&cTest Upgrade",
                RecipeType.BARTER_DROP,new ItemStack[9],
                new CountUpgradeItem.EntityKillStat[]{new CountUpgradeItem.EntityKillStat(
                        new NamespacedKey(this, "stored_mob"), 5, EntityType.SLIME)
                }
        );
        testUpgrade.register(this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }
    @Nullable
    @Override
    public String getBugTrackerURL() {
        return null;
    }
}
