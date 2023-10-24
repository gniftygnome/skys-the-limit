package net.gnomecraft.skysthelimit;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.gnomecraft.skysthelimit.block.SkysTheLimitBlocks;
import net.gnomecraft.skysthelimit.composter.SkysTheLimitCompostMoreItems;
import net.gnomecraft.skysthelimit.config.SkysTheLimitConfig;
import net.gnomecraft.skysthelimit.fabricresourcecondition.SkysTheLimitResourceConditions;
import net.gnomecraft.skysthelimit.item.SkysTheLimitItems;
import net.gnomecraft.skysthelimit.loot.SkysTheLimitFishingLoot;
import net.gnomecraft.skysthelimit.tag.SkysTheLimitBlockTags;
import net.gnomecraft.skysthelimit.tag.SkysTheLimitItemTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkysTheLimit implements ModInitializer {
    public static final String MOD_ID = "skys-the-limit";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Register the mod's config
        AutoConfig.register(SkysTheLimitConfig.class, Toml4jConfigSerializer::new);

        SkysTheLimitResourceConditions.init();
        SkysTheLimitBlocks.init();
        SkysTheLimitItems.init();
        SkysTheLimitCompostMoreItems.init();
        SkysTheLimitBlockTags.init();
        SkysTheLimitItemTags.init();
        SkysTheLimitFishingLoot.init();

        LOGGER.info("The sky's the limit.");
    }

    public static SkysTheLimitConfig getConfig() {
        return AutoConfig.getConfigHolder(SkysTheLimitConfig.class).getConfig();
    }
}
