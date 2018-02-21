    package com.lutalica.shilly;

    import com.lutalica.shilly.items.DoughItem;
    import com.lutalica.shilly.items.DoughShaped;
    import com.lutalica.shilly.proxy.ProxyCommon;
    import com.lutalica.shilly.utils.Utility;
    import com.lutalica.shilly.vanilla.TileEntityCauldron;
    import net.minecraft.block.Block;
    import net.minecraft.block.BlockCauldron;
    import net.minecraft.block.properties.IProperty;
    import net.minecraft.block.properties.PropertyInteger;
    import net.minecraft.block.state.IBlockState;
    import net.minecraft.entity.Entity;
    import net.minecraft.entity.item.EntityItem;
    import net.minecraft.init.Items;
    import net.minecraft.item.Item;
    import net.minecraft.item.ItemBlockSpecial;
    import net.minecraft.item.ItemFood;
    import net.minecraft.item.ItemStack;
    import net.minecraft.util.math.BlockPos;
    import net.minecraft.world.IBlockAccess;
    import net.minecraft.world.World;
    import net.minecraftforge.event.RegistryEvent;
    import net.minecraftforge.event.entity.player.PlayerInteractEvent;
    import net.minecraftforge.event.world.BlockEvent;
    import net.minecraftforge.fml.common.Mod;
    import net.minecraftforge.fml.common.SidedProxy;
    import net.minecraftforge.fml.common.event.FMLInitializationEvent;
    import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
    import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
    import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
    import net.minecraftforge.fml.common.gameevent.TickEvent;
    import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
    import net.minecraftforge.fml.common.registry.GameRegistry;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Iterator;

    @Mod(
        modid = Shilly.MOD_ID,
        name = Shilly.MOD_NAME,
        version = Shilly.VERSION
    )
    public class Shilly {

    public static final String MOD_ID = "shilly";
    public static final String MOD_NAME = "Shilly";
    public static final String VERSION = "0.0";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static Shilly INSTANCE;

    @SidedProxy(clientSide = "com.lutalica.shilly.proxy.ProxyClient", serverSide = "com.lutalica.shilly.proxy.ProxyServer")
    public static ProxyCommon proxy;


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }


    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }


    /**
     * Forge will automatically look up and bind blocks to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks {
      /*
          public static final MySpecialBlock mySpecialBlock = null; // placeholder for special block below
      */
    }




    /**
     * Forge will automatically look up and bind items to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Items {
      /*
          public static final ItemBlock mySpecialBlock = null; // itemblock for the block above
          public static final MySpecialItem mySpecialItem = null; // placeholder for special item below
      */

      public static DoughItem doughItem = null;
      public static DoughShaped doughShaped = null;


    }

    /**
     * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
     */
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
        /**
         * Listen for the register event for creating custom items
         */
        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
           /*
             event.getRegistry().register(new ItemBlock(Blocks.myBlock).setRegistryName(MOD_ID, "myBlock"));
             event.getRegistry().register(new MySpecialItem().setRegistryName(MOD_ID, "mySpecialItem"));
            */

            event.getRegistry().register((Items.doughItem = new DoughItem()));
            event.getRegistry().register((Items.doughShaped = new DoughShaped()));
        }


        /**
         * Listen for the register event for creating custom blocks
         */
        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> event) {
           /*
             event.getRegistry().register(new MySpecialBlock().setRegistryName(MOD_ID, "mySpecialBlock"));
            */

        }



    }
    /* EXAMPLE ITEM AND BLOCK - you probably want these in separate files
    public static class MySpecialItem extends Item {

    }

    public static class MySpecialBlock extends Block {

    }
    */
    }
