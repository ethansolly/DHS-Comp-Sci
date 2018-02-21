package com.lutalica.shilly.items;

import com.lutalica.shilly.Shilly;
import com.lutalica.shilly.utils.Utility;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class DoughItem extends ItemFood {

    private String name = Shilly.MOD_ID + ":dough";


    public DoughItem() {
        super(5, 6, false);
        this.setAlwaysEdible();
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
        Utility.modifyNBT(stack, nbt -> {
            if (!nbt.getBoolean("bust")) {
                add(thicknessString(nbt.getInteger("thicc")), list);
                add(sweetnessString(nbt.getInteger("sweet")), list);
                add(breadFlavorString(nbt.getBoolean("breadflavored")), list);
                add(stickyString(nbt.getBoolean("sticky")), list);
            }
            else
                add("Bust!", list);
        });
    }

    private void add(String o, List<String> list) {
        if (o != null)
            list.add(o);
    }

    private String thicknessString(int thicc) {
        switch (thicc) {
            case -3 : return "\u00A79" + "Porous";
            case -2 : return "\u00A7b" + "Cloudy";
            case -1 : return "Light";
            case 0 : return "Consistent";
            case 1: return "Thick";
            case 2: return "\u00A7e" + "Thicc";
            case 3: return "\u00A76" + "Extra" + "\u00A7l" + " Thicc";
            default:
                if (thicc < -3)
                    return "\u00A75" + "Gravity-Defying";
                if (thicc > 3)
                    return "\u00A76" + "Intensely" + "\u00A7l" + " Thicc";
        }
        return null;
    }

    private String sweetnessString(int sweet) {
        switch (sweet) {
            case 0 : return "Mild";
            case 1: return "Sweet";
            case 2: return "\u00A7d" + "Sugary";
            case 3: return "\u00A7d" + "Saccharine";
            default:
                if (sweet < 0)
                    return "\u00A7a" + "Bitter";
                if (sweet > 3)
                    return "\u00A75" + "Sickeningly Sweet";
        }
        return null;
    }

    private String breadFlavorString(boolean breadflavored) {
        return breadflavored ? "\u00A70B\u00A71r\u00A72e\u00A73a\u00A74d\u00A75-\u00A76Flavored" : null;
    }

    private String stickyString(boolean sticky) {
        return sticky ? "\u00A76Sticky" : null;
    }

    public static void mix(ItemStack thisStack, List<ItemStack> newIngredients) {
        mix(thisStack, newIngredients.toArray(new ItemStack[newIngredients.size()]));
    }

    public static void mix(ItemStack thisStack, ItemStack[] newIngredients) {
        if (thisStack.getItem() == Shilly.Items.doughItem) {
            int thicc = 0;
            int sweet = 0;
            boolean breadflavored = false;
            boolean sticky = false;
            boolean bust = false;

            int egg = 0;
            int flour = 0;
            int sugar = 0;
            int milk = 0;
            int tears = 0;

            //Get base ingredients
            for (ItemStack i : newIngredients) {
                Item ingredient = i.getItem();
                if (ingredient == Items.EGG)
                    egg += i.stackSize;
                if (ingredient == Items.WHEAT)
                    flour += i.stackSize;
                if (ingredient == Items.SUGAR)
                    sugar += i.stackSize;
                if (ingredient == Items.MILK_BUCKET)
                    milk += i.stackSize;
                if (ingredient == Items.GHAST_TEAR)
                    tears += i.stackSize;
                if (ingredient == Items.APPLE)
                    sweet += 2 * i.stackSize;
                if (ingredient == Items.MELON)
                    sweet += i.stackSize;
                if (ingredient == Items.PUMPKIN_PIE) {
                    sweet += 4 * i.stackSize;
                    breadflavored = true;
                }
                if (ingredient == Items.GOLDEN_APPLE)
                    sweet += 8 * i.stackSize;
                if (ingredient == Items.CAKE) {
                    sweet += 8 * i.stackSize;
                    breadflavored = true;
                }
                if (ingredient == Items.BEETROOT)
                    sweet += i.stackSize;
                if (ingredient == Items.CHORUS_FRUIT)
                    sweet += 2 * i.stackSize;
                if (ingredient == Items.COOKIE) {
                    sweet += i.stackSize;
                    breadflavored = true;
                }
                if (ingredient == Items.REEDS)
                    sweet += 2 * i.stackSize;
                if (ingredient == Items.BREAD)
                    breadflavored = true;
                if (ingredient == Items.STICK)
                    sticky = true;

            }

            //Now the fun begins
            if (flour != 0) thicc = (egg - tears) / flour;

            //Remove flour due to water
            if (flour >= 5)
                flour -= 5;
            else {
                //You failed
                flour = 0;
                bust = true;
            }

            //Remove sugar due to milk
            if (sugar >= milk)
                sugar -= milk;
            else {
                //You failed
                sugar = 0;
                bust = true;
            }


            sweet += sugar;
            if (sweet != 0)
                sweet = (int) (Math.log(sweet) / Math.log(4));


            System.out.println(thicc);
            System.out.println(sweet);
            System.out.println(breadflavored);
            System.out.println(bust);

            //Enter NBT Data
            final int finalThicc = thicc;
            final int finalSweet = sweet;
            final boolean finalBreadflavored = breadflavored;
            final boolean finalBust = bust;
            final boolean finalSticky = sticky;
            Utility.modifyNBT(thisStack, nbt -> {
                nbt.setInteger("thicc", finalThicc);
                nbt.setInteger("sweet", finalSweet);
                nbt.setBoolean("breadflavored", finalBreadflavored);
                nbt.setBoolean("bust", finalBust);
                nbt.setBoolean("sticky", finalSticky);
                nbt.setBoolean("mixed", true);
            });
        }
    }

}
