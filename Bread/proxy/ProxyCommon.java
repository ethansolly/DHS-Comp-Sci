package com.lutalica.shilly.proxy;

import com.lutalica.shilly.vanilla.TileEntityCauldron;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by ethan.solly on 11/27/2017.
 */
public class ProxyCommon {
    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    public void preInit(FMLPreInitializationEvent e) {
        System.out.println("I like bread!");
        GameRegistry.registerTileEntity(TileEntityCauldron.class, "tile_entity_cauldron");
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    public void init(FMLInitializationEvent e) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    public void postInit(FMLPostInitializationEvent e) {

    }

    public void registerItemRenderer(Item i, int meta, String name) {

    }
}
