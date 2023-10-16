package com.github.gtgolden.gttest;

import com.github.gtgolden.gtgoldencore.materials.api.module.ColorModule;
import com.github.gtgolden.gtgoldencore.materials.api.module.ItemFormsModule;
import com.github.gtgolden.gtgoldencore.materials.api.module.ToolMaterialModule;
import com.github.gtgolden.gtgoldencore.materials.api.module.TranslationModule;
import com.github.gtgolden.gtgoldencore.materials.impl.MaterialRegistryEvent;
import com.github.gtgolden.gttest.block.*;
import com.github.gtgolden.gttest.item.Battery;
import com.github.gtgolden.gttest.item.RedstonePickaxe;
import com.github.gtgolden.gttest.item.TestMetaItem;
import com.github.gtgolden.gttest.item.TestPowerTool;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.tileentity.TileEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class GtTest {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @Entrypoint.Logger("GT-TEST")
    public static final Logger LOGGER = Null.get();

    public static BlockBase GENERATOR;
    public static BlockBase APPLE_SPAWNER;
    public static BlockBase COBBLE_GEN;
    public static BlockBase ITEM_MOVER;
    public static BlockBase STORAGE_BLOCK;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        LOGGER.info("Registering blocks");
        GENERATOR = new Generator(MOD_ID.id("generator"), Material.STONE);
        APPLE_SPAWNER = new AppleSpawner(MOD_ID.id("apple_spawner"), Material.STONE);
        COBBLE_GEN = new CobbleGenerator(MOD_ID.id("cobble_generator"), Material.STONE);
        ITEM_MOVER = new ItemMover(MOD_ID.id("item_mover"), Material.STONE);
        STORAGE_BLOCK = new StorageBlock(MOD_ID.id("storage_block"), Material.METAL);
    }

    @EventListener
    public void registerTileEntities(TileEntityRegisterEvent event) {
        LOGGER.info("Registering tile entities");
        event.register(GeneratorEntity.class, MOD_ID.id("generator").toString());
        event.register(AppleSpawnerEntity.class, MOD_ID.id("apple_spawner").toString());
        event.register(CobbleGeneratorEntity.class, MOD_ID.id("cobble_generator").toString());
        event.register(ItemMoverEntity.class, MOD_ID.id("item_mover").toString());
        event.register(StorageEntity.class, MOD_ID.id("storage_block").toString());
    }

    public static ItemBase REDSTONE_PICKAXE;
    public static TestMetaItem TEST_META_ITEM;
    public static ItemBase TEST_POWER_TOOL;
    public static ItemBase BATTERY;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        LOGGER.info("Registering items");
        REDSTONE_PICKAXE = new RedstonePickaxe(MOD_ID.id("redstone_pickaxe"));
        TEST_META_ITEM = new TestMetaItem(MOD_ID.id("test_meta_item"));
        TEST_POWER_TOOL = new TestPowerTool(MOD_ID.id("test_power_tool"));
        BATTERY = new Battery(MOD_ID.id("battery"));
    }

    @EventListener
    public void registerMaterials(MaterialRegistryEvent event) {
        LOGGER.info("Registering materials");
        event.registerModules("dirt", new ColorModule(new Color(0x332008)), new TranslationModule(MOD_ID.id("dirt")));
        event.registerModules(
                "redstone",
                new ToolMaterialModule("redstone", 3, 4, 14.0F, 0),
                new ItemFormsModule("pickaxe", new ItemInstance(REDSTONE_PICKAXE))
        );
        event.registerTranslationProvider("redstone", MOD_ID);
    }
}
