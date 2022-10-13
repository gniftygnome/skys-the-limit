package net.gnomecraft.skysthelimit.data;

import eu.midnightdust.motschen.rocks.RocksMain;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gnomecraft.skysthelimit.tag.SkysTheLimitItemTags;

public class SkysTheLimitItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public SkysTheLimitItemTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        this.getOrCreateTagBuilder(SkysTheLimitItemTags.COMMON_STONES)
                .add(RocksMain.CobblestoneSplitter)
                .add(RocksMain.AndesiteSplitter)
                .add(RocksMain.DioriteSplitter)
                .add(RocksMain.GraniteSplitter);
    }
}
