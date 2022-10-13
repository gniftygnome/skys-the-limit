package net.gnomecraft.skysthelimit.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SkysTheLimitDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        dataGenerator.addProvider(SkysTheLimitBlockLootTableProvider::new);
        dataGenerator.addProvider(SkysTheLimitBlockTagProvider::new);
        dataGenerator.addProvider(SkysTheLimitItemTagProvider::new);
        dataGenerator.addProvider(SkysTheLimitRecipeProvider::new);
    }
}
