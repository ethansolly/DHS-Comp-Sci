package com.lutalica.shilly.vanilla;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;

/**
 * Created by ethan.solly on 11/20/2017.
 */
public class TileEntityCauldron extends TileEntity {


    private ArrayList<ItemStack> inventory;
    
    public TileEntityCauldron() {
        inventory = new ArrayList<>();
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSize()
    {
        return inventory.size();
    }

    public void insert(ItemStack itemStack) {
        boolean set = false;
        for (int i = 0; i < inventory.size() && !set; i++) {
            ItemStack currentStack = inventory.get(i);
            if (currentStack.getItem() == itemStack.getItem()) {
                currentStack.stackSize += itemStack.stackSize;
                set = true;
            }
        }
        if (!set)
            inventory.add(itemStack);
    }

    public ItemStack get(int index) {
        return inventory.get(index);
    }

    public ArrayList<ItemStack> getInventory() {
        return inventory;
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventory)
        {
            if (itemstack.stackSize != 0)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return "container.cauldron";
    }


    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        super.invalidate();
        this.updateContainingBlockInfo();
    }


    public String toString() {
        return inventory.toString();
    }
}
