package net.gnomecraft.skysthelimit.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SkysTheLimitDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        FabricDataGenerator.Pack pack = dataGenerator.createPack();

        pack.addProvider(SkysTheLimitBlockLootTableProvider::new);
        SkysTheLimitBlockTagProvider blockTagProvider = pack.addProvider(SkysTheLimitBlockTagProvider::new);
        pack.addProvider((output, registries) -> new SkysTheLimitItemTagProvider(output, registries, blockTagProvider));
        pack.addProvider(SkysTheLimitRecipeProvider::new);
    }
}
