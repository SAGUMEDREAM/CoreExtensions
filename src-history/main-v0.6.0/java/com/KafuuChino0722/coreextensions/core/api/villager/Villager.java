package com.KafuuChino0722.coreextensions.core.api.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.Map;

public class Villager {
    private static Item getID(String item) {
        return Registries.ITEM.get(new Identifier(item));
    }
    private static Block getBlock(String block) {
        return Registries.BLOCK.get(new Identifier(block));
    }

    public static VillagerProfession registerProfession(String namespace, String id, RegistryKey<PointOfInterestType> type){
        return Registry.register(Registries.VILLAGER_PROFESSION,new Identifier(namespace,id),
                VillagerProfessionBuilder.create().id(new Identifier(namespace,id)).workstation(type)
                        .workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build());
    }

    public static PointOfInterestType registerPOI(String namespace, String id, Block block){
        return PointOfInterestHelper.register(new Identifier(namespace,id),
                1,1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static void registerVillager(String namespace,String id,String block) {//注册职业
        String IdPoi = id+"_poi";
        String IdMaster = id+"_master";
        try {
            registerPOI(namespace, IdPoi, getBlock(block));
            registerProfession(namespace, IdMaster,
                    RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(namespace,IdPoi)));
        } catch (Exception e) {

        }
    }

    public static void modifyTrades(//添加交易列表到默认列表
                                      String namespace, String id,
                                      Map<String, Object> villagerData)
    {
        Map<String, Object> properties = (Map<String, Object>) villagerData.get("properties");
        Map<String, Object> KeyList = (Map<String, Object>) properties.get("key");
        for (Map.Entry<String, Object> entry : KeyList.entrySet()) {
            if (entry.getValue() instanceof Map) {
                Map<String, Object> Key = (Map<String, Object>) entry.getValue();
                int level = (int) Key.getOrDefault("level",0);
                int maxUse = (int) Key.getOrDefault("maxUse",6);
                TradeOfferHelper.registerVillagerOffers(
                        Registries.VILLAGER_PROFESSION.get(new Identifier(namespace,id)),
                        level,
                        factories -> {
                            String inputItemA = Key.containsKey("inputA") ? (String) Key.get("inputA") : "minecraft:air";
                            int countInputA = Key.containsKey("countInputA") ? (int) Key.get("countInputA") : 1;
                            String inputItemB = Key.containsKey("inputB") ? (String) Key.get("inputB") : "minecraft:air";
                            int countInputB = Key.containsKey("countInputB") ? (int) Key.get("countInputB") : 1;
                            String resultItem = Key.containsKey("result") ? (String) Key.get("result") : "minecraft:air";
                            int countResult = Key.containsKey("countResult") ? (int) Key.get("countResult") : 1;
                            if(inputItemB.equals("minecraft:air")) {
                                factories.add((entity, random) -> new TradeOffer(
                                        new ItemStack(getID(inputItemA), countInputA),
                                        new ItemStack(getID(resultItem), countResult),
                                        maxUse, 2, 0.02f
                                ));
                            } else {
                                factories.add((entity, random) -> new TradeOffer(
                                        new ItemStack(getID(inputItemA), countInputA),
                                        new ItemStack(getID(inputItemB), countInputB),
                                        new ItemStack(getID(resultItem), countResult),
                                        maxUse, 2, 0.02f
                                ));
                            }
                        }
                );
            }
        }
    }

    public static void registerTrades(//注册交易列表
            String namespace, String id,
            Map<String, Object> villagerData)
    {
        Map<String, Object> properties = (Map<String, Object>) villagerData.get("properties");
        Map<String, Object> KeyList = (Map<String, Object>) properties.get("key");
        for (Map.Entry<String, Object> entry : KeyList.entrySet()) {
            if (entry.getValue() instanceof Map) {
                Map<String, Object> Key = (Map<String, Object>) entry.getValue();
                int level = (int) Key.getOrDefault("level",0);
                int maxUse = (int) Key.getOrDefault("maxUse",6);
                TradeOfferHelper.registerVillagerOffers(
                        Registries.VILLAGER_PROFESSION.get(new Identifier(namespace,id+"_master")),
                        level,
                        factories -> {
                            String inputItemA = Key.containsKey("inputA") ? (String) Key.get("inputA") : "minecraft:air";
                            int countInputA = Key.containsKey("countInputA") ? (int) Key.get("countInputA") : 1;
                            String inputItemB = Key.containsKey("inputB") ? (String) Key.get("inputB") : "minecraft:air";
                            int countInputB = Key.containsKey("countInputB") ? (int) Key.get("countInputB") : 1;
                            String resultItem = Key.containsKey("result") ? (String) Key.get("result") : "minecraft:air";
                            int countResult = Key.containsKey("countResult") ? (int) Key.get("countResult") : 1;
                            if(inputItemB.equals("minecraft:air")) {
                                factories.add((entity, random) -> new TradeOffer(
                                        new ItemStack(getID(inputItemA), countInputA),
                                        new ItemStack(getID(resultItem), countResult),
                                        maxUse, 2, 0.02f
                                ));
                            } else {
                                factories.add((entity, random) -> new TradeOffer(
                                        new ItemStack(getID(inputItemA), countInputA),
                                        new ItemStack(getID(inputItemB), countInputB),
                                        new ItemStack(getID(resultItem), countResult),
                                        maxUse, 2, 0.02f
                                ));
                            }
                        }
                );
            }
        }
    }
}
