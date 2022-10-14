package net.gnomecraft.skysthelimit.composter;

import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.gnomecraft.skysthelimit.config.SkysTheLimitConfig;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Items;

public class SkysTheLimitCompostMoreItems {
    public static void init() {
        SkysTheLimitConfig config = SkysTheLimit.getConfig();

        // Allow composting bamboo (slowly).
        if (config.compostBamboo) {
            ComposterBlock.registerCompostableItem(0.05f, Items.BAMBOO);
        }

        // Fish make great compost...
        if (config.compostFish) {
            ComposterBlock.registerCompostableItem(0.50f, Items.TROPICAL_FISH);
            ComposterBlock.registerCompostableItem(0.65f, Items.COD);
            ComposterBlock.registerCompostableItem(0.65f, Items.COOKED_COD);
            ComposterBlock.registerCompostableItem(0.85f, Items.SALMON);
            ComposterBlock.registerCompostableItem(0.85f, Items.COOKED_SALMON);
        }

        // Leather is compostable given time.
        if (config.compostLeather) {
            ComposterBlock.registerCompostableItem(0.05f, Items.LEATHER);
            ComposterBlock.registerCompostableItem(0.35f, Items.LEATHER_HORSE_ARMOR);
            ComposterBlock.registerCompostableItem(0.25f, Items.LEATHER_HELMET);
            ComposterBlock.registerCompostableItem(0.40f, Items.LEATHER_CHESTPLATE);
            ComposterBlock.registerCompostableItem(0.35f, Items.LEATHER_LEGGINGS);
            ComposterBlock.registerCompostableItem(0.20f, Items.LEATHER_BOOTS);
            ComposterBlock.registerCompostableItem(0.15f, Items.SADDLE);
        }

        // Silly Mojang, potatoes are potatoes.
        if (config.compostPotatoes) {
            ComposterBlock.registerCompostableItem(0.65f, Items.POISONOUS_POTATO);
        }
    }
}
