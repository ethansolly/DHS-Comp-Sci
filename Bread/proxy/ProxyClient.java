package com.lutalica.shilly.proxy;

import com.lutalica.shilly.Shilly;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by ethan.solly on 11/27/2017.
 */
@SideOnly(Side.CLIENT)
public class ProxyClient extends ProxyCommon {
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        registerItemRenderer(Shilly.Items.doughItem, 0,Shilly.MOD_ID + ":dough");
        registerItemRenderer(Shilly.Items.doughShaped, 0, Shilly.MOD_ID + ":doughShaped");
    }

    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

    public void registerItemRenderer(Item i, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(name, "inventory"));
    }
}
