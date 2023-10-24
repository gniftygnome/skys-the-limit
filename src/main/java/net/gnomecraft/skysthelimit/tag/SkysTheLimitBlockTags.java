package net.gnomecraft.skysthelimit.tag;

import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class SkysTheLimitBlockTags {
    public static final TagKey<Block> COMMON_STONE = SkysTheLimitBlockTags.of(Identifier.of("c", "stone"));

    public static void init() {
    }

    private static TagKey<Block> of(String path) {
        return SkysTheLimitBlockTags.of(new Identifier(SkysTheLimit.MOD_ID, path));
    }

    private static TagKey<Block> of(Identifier id) {
        return TagKey.of(Registries.BLOCK.getKey(), id);
    }
}
