package com.lutalica.shilly.items;

import com.lutalica.shilly.Shilly;
import com.lutalica.shilly.utils.Utility;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import java.util.function.Consumer;

/**
 * Created by ethan.solly on 11/27/2017.
 */
public class DoughShaped extends Item implements ItemMeshDefinition{

    private static DoughShaped INSTANCE = Shilly.Items.doughShaped;
    private String name = Shilly.MOD_ID + ":doughShaped";

    public DoughShaped() {
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public static void setMold(ItemStack stack, Item i) {
        if (stack.getItem() == INSTANCE) {
            Utility.modifyNBT(stack, nbt -> {
               nbt.setInteger("item", Item.getIdFromItem(i));
            });
        }
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        final Item[] items = new Item[1];
        if (stack.getItem() == INSTANCE)
            Utility.modifyNBT(stack, nbt -> items[0] = Item.getItemById(nbt.getInteger("item")));
        Item item = items[0];
        return new ModelResourceLocation("shilly:doughShape_"+item.getUnlocalizedName());
    }
}
