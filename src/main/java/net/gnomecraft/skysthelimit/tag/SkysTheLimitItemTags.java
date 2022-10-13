package net.gnomecraft.skysthelimit.tag;

import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SkysTheLimitItemTags {
    public static final TagKey<Item> COMMON_STONES = SkysTheLimitItemTags.of(Identifier.of("c", "stones"));

    public static void init() {
    }

    private static TagKey<Item> of(String path) {
        return SkysTheLimitItemTags.of(new Identifier(SkysTheLimit.MOD_ID, path));
    }

    private static TagKey<Item> of(Identifier id) {
        return TagKey.of(Registry.ITEM_KEY, id);
    }
}
