package net.gnomecraft.skysthelimit.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.gnomecraft.skysthelimit.SkysTheLimit;

/*
 * Skys the Limit configuration file definition.
 *
 * WARNING:  In order to change or remove configuration variables, ALWAYS check  :WARNING
 * WARNING:  for string contents and not merely Java references.  Configuration  :WARNING
 * WARNING:  variables are referenced in resource condition strings by name!     :WARNING
 */
@SuppressWarnings("unused")
@Config(name = SkysTheLimit.MOD_ID)
public class SkysTheLimitConfig implements ConfigData {
    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.PrefixText
    //@ConfigEntry.Gui.Tooltip
    public Boolean fogCatcher = true;


    @ConfigEntry.Category("fishing")
    @ConfigEntry.Gui.PrefixText
    public Boolean oceanLoot = true;

    @ConfigEntry.Category("fishing")
    public Boolean rocksLoot = true;


    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Category("recipes")
    public Boolean barkToString = true;

    @ConfigEntry.Category("recipes")
    public Boolean craftableSaddle = true;

    @ConfigEntry.Category("recipes")
    public Boolean dirtToGrassBlock = true;

    @ConfigEntry.Category("recipes")
    public Boolean snowToWater = true;

    @ConfigEntry.Category("recipes")
    public Boolean stonesToCobblestone = true;

    @ConfigEntry.Category("recipes")
    public Boolean stonesToStoneBucket = true;

    @ConfigEntry.Category("recipes")
    public Boolean stringToRope = true;
}
