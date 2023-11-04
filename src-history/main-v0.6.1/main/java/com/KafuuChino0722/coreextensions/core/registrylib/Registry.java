package com.KafuuChino0722.coreextensions.core.registrylib;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

import java.util.function.Supplier;


public class Registry {
    /**
     * Register items item.
     *
     * @param namespace         the namespace of the item
     * @param id    the id of your item
     * @param item      the item settings
     * @param itemGroup the item group
     * @return the item will be created and returned
     */
    public static Item registerItems(String namespace, String id, Item item, RegistryKey<ItemGroup> itemGroup){
        Item createditem = net.minecraft.registry.Registry.register(Registries.ITEM,new Identifier(namespace,id),item);
        addToItemGroup(itemGroup,createditem);
        return createditem;
    }
    /**
     * Register items item.
     *
     * @param namespace      the name of the item
     * @param id    the mod id of your mod
     * @param block      the block settings
     * @return the block and the block item  will be created and returned
     */
    public static Block registerBlocks(String namespace, String id, Block block, RegistryKey<ItemGroup> itemGroup){
        registerBlockItem(namespace,id,block,itemGroup);
        return net.minecraft.registry.Registry.register(Registries.BLOCK,new Identifier(namespace,id),block);
    }

    /**
     *
     * @param namespace the name of the item
     * @param id the Mod id of your mod
     * @param block the reference of the block this item is for
     * @param itemGroup the item group that the block item will be shown
     * @return the block item without creating the block (for crops)
     */
    public static Item registerBlockItem(String namespace, String id, Block block, RegistryKey<ItemGroup> itemGroup) {
        Item blockItem =  net.minecraft.registry.Registry.register(Registries.ITEM,new Identifier(namespace,id),
                new BlockItem(block,new FabricItemSettings()));
        addToItemGroup(itemGroup,blockItem);
        return blockItem;
    }

    /**
     *
     * @param namespace the name of the group make sure the name is same the name you want to be displayed in the game
     * @param id the mod id of your mod
     * @param itemStack the item that you want to use as the icon as an item stack e.g. new ItemStack(Items.APPLE);
     * @return the item group
     */
    public static RegistryKey<ItemGroup> registerItemGroup(String namespace, String id ,Supplier<ItemStack> itemStack){
        String displayName = formatString(id);
        RegistryKey<ItemGroup> customItemGroup = RegistryKey.of(RegistryKeys.ITEM_GROUP,new Identifier(namespace,id));
        net.minecraft.registry.Registry.register(Registries.ITEM_GROUP, customItemGroup, FabricItemGroup.builder()
                .icon(itemStack).displayName(Text.literal(displayName)).build());

        return customItemGroup;
    }
    //Adds Group to the items created

    /**
     * Add an item to an item group
     * @param group reference of the item Group
     * @param item reference of the item
     */
    public static void addToItemGroup(RegistryKey<ItemGroup> group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    /**
     * Register blocks without block item block.
     *
     * @param namespace   the name
     * @param id the mod id
     * @param block  the block
     * @return the block
     */
    public static Block registerBlocksWithoutBlockItem(String namespace, String id, Block block){
        //register the block without block items, so you have to register it manually using registerItems
        return net.minecraft.registry.Registry.register(Registries.BLOCK,new Identifier(namespace,id),block);
    }

    /**
     * Register enchantments enchantment.
     *
     * @param namespace        the name of the enchantment
     * @param enchantment the enchantment settings
     * @param id      the mod id of your mod
     * @return the enchantment
     */
    public static Enchantment registerEnchantments(String namespace, Enchantment enchantment, String id){
        return net.minecraft.registry.Registry.register(Registries.ENCHANTMENT, new Identifier(namespace, id),enchantment);

    }

    /**
     * Register fluids .
     * @param namespace
     * @param id
     * @param flowableFluid
     * @return
     */
    private static FlowableFluid registerFluids(String namespace, String id,FlowableFluid flowableFluid) {
        return net.minecraft.registry.Registry.register(Registries.FLUID, new Identifier(namespace, id), flowableFluid);
    }
    public static void registerRegistry(Logger logger){
        logger.info("registered all the registry");
    }

    /**
     * Registery entity type.
     *
     * @param namespace   the name
     * @param id the mod id
     * @param entity the entity
     * @return The Entity
     */
    public static EntityType registerEntity(String namespace,String id, EntityType entity){
        return net.minecraft.registry.Registry.register(Registries.ENTITY_TYPE, new Identifier(namespace,id),entity);
    }
    //register status effects

    /**
     * Register status effects status effect.
     *
     * @param namespace         the name of the status effect / potion effect
     * @param id       the mod id of your mod
     * @param statusEffect the status effect settings
     * @return the status effect
     */
    public static StatusEffect registerStatusEffects(String namespace,String id, StatusEffect statusEffect){
        return net.minecraft.registry.Registry.register(Registries.STATUS_EFFECT, new Identifier(namespace, id), statusEffect);
    }
    //register entities with spawn egg

    public static <T extends Entity> EntityType<T> buildEntity(EntityType.EntityFactory<T> entity, Class<T> entityClass,
                                                               float width, float height, SpawnGroup group, String namespace) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            String id = entityClass.getSimpleName().toLowerCase();
            return EntityRegistryBuilder.<T>createBuilder(new Identifier(namespace, id)).entity(entity)
                    .category(group).dimensions(EntityDimensions.changing(width, height)).build();
        }
        return null;
    }
    //register spawn egg without entity

    /**
     * Register spawn egg without entity.
     * @param item
     * @param namespace
     * @return
     * @param <I>
     */
    public static <I extends Item> I registerEgg(I item, Identifier name) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            return net.minecraft.registry.Registry.register(Registries.ITEM, name, item);
        }
        return null;
    }

    private static String formatString(String input) {
        String[] words = input.split("_");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                result.append(formattedWord).append(" ");
            }
        }

        return result.toString().trim();
    }

}