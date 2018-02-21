package com.lutalica.shilly;

import com.lutalica.shilly.items.DoughItem;
import com.lutalica.shilly.items.DoughShaped;
import com.lutalica.shilly.vanilla.TileEntityCauldron;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Mod.EventBusSubscriber
public class EventHandler {
    private static HashMap<BlockPos, TileEntityCauldron> cauldrons = new HashMap<>();

    @SubscribeEvent
    public static void onPlaceCauldron(BlockEvent.PlaceEvent event) {
        World world = event.getWorld();
        if (!world.isRemote) {
            IBlockState state = event.getPlacedBlock();
            if (state.getBlock() instanceof net.minecraft.block.BlockCauldron) {
                TileEntityCauldron te = new TileEntityCauldron();
                BlockPos pos = event.getPos();
                cauldrons.put(pos, te);
            }
        }
    }

    @SubscribeEvent
    public static void onItemOnGround(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if (!world.isRemote) {
            Iterator<Entity> list = world.loadedEntityList.iterator();
            while (list.hasNext()) {
                Entity entity = list.next();
                if (!entity.isDead && entity instanceof EntityItem && entity.onGround) {
                    BlockPos pos = entity.getPosition();

                    ///Cauldron stuff
                    if (world.getBlockState(pos).getBlock() instanceof BlockCauldron) {
                        EntityItem entityItem = (EntityItem) entity;
                        ItemStack itemStack = entityItem.getEntityItem();
                        world.removeEntity(entityItem);
                        TileEntityCauldron te = cauldrons.computeIfAbsent(pos, k -> new TileEntityCauldron());
                        te.insert(itemStack);
                        System.out.println(te);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        if (!world.isRemote) {
            BlockPos pos = event.getPos();
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() instanceof BlockCauldron) {
                TileEntityCauldron te = cauldrons.computeIfAbsent(pos, k -> new TileEntityCauldron());
                ArrayList<ItemStack> items = te.getInventory();
                boolean complete = false;

                for (ItemStack i : items) {
                    //If there is any wheat in the cauldron upon right clicking, we are ready
                    if (i.getItem() == net.minecraft.init.Items.WHEAT)
                        complete = true;
                }

                if (complete) {
                    //If full cauldron
                    if (state.getBlock().getMetaFromState(state) == 3) {

                        //Create dough
                        ItemStack doughStack = new ItemStack(Shilly.Items.doughItem);
                        DoughItem.mix(doughStack, items);
                        EntityItem itemEntity = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), doughStack);
                        world.spawnEntity(itemEntity);

                        //Empty cauldron
                        ((BlockCauldron) state.getBlock()).setWaterLevel(world, pos, state, 0);
                        items.clear();
                    }
                }
            }

            else if (state.getBlock() instanceof BlockSlab) {
                ItemStack hand = event.getItemStack();
                System.out.println("0");
                if (hand != null && hand.getItem() instanceof DoughItem) {
                    System.out.println("1");
                    List<Entity> entitiesOnSlab = world.getEntitiesWithinAABBExcludingEntity(event.getEntityPlayer(), new AxisAlignedBB(event.getPos()));
                    for (Entity entity : entitiesOnSlab) {
                        System.out.println("2");
                        if (entity instanceof EntityItem) {
                            System.out.println("3");
                            Item item = ((EntityItem) entity).getEntityItem().getItem();
                            world.removeEntity(entity);

                            ItemStack doughShapedStack = new ItemStack(Shilly.Items.doughShaped);
                            DoughShaped.setMold(doughShapedStack, item);
                            EntityItem itemEntity = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), doughShapedStack);
                            world.spawnEntity(itemEntity);

                            break;
                        }
                    }
                }
            }
        }
    }
}
