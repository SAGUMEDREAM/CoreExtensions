package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.api.MethodStatusEffectCategory;
import com.KafuuChino0722.coreextensions.entity.StandardStatusEffect;
import com.KafuuChino0722.coreextensions.mixin.BrewingRecipeRegistryMixin;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;
import static net.minecraft.entity.effect.StatusEffects.SPEED;

public class PotionRegistry {
    public static void register() {
        Map<String, Map<String, Object>> potionsData = IOFileManager.read("itemPotion.yml");
        load(potionsData);
        Map<String, Map<String, Object>> potionsDataZ = IOFileManager.readZip("itemPotion.yml");
        load(potionsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> potionsData) {
        if (potionsData != null && potionsData.containsKey("items")) {
            Map<String, Object> potions = potionsData.get("items");

            for (Map.Entry<String, Object> entry : potions.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> potionData = (Map<String, Object>) entry.getValue();
                    String name = (String) potionData.getOrDefault("name", entry.getKey());

                    String namespace = (String) potionData.getOrDefault("namespace","minecraft");
                    String id = (String) potionData.getOrDefault("id","minecraft");

                    Map<String, Object> properties = (Map<String, Object>) potionData.getOrDefault("properties", entry.getValue());
                    Map<String, Object> settings = (Map<String, Object>) properties.getOrDefault("settings", entry.getValue());
                    Map<String, Object> recipeData = (Map<String, Object>) properties.getOrDefault("recipes", entry.getValue());

                    List<StatusEffectInstance> statusEffectInstanceList = new ArrayList<>();
                    StatusEffectInstance[] statusEffectInstances = null;

                    if(settings!=null) {
                        for (Map.Entry<String, Object> entry2 : settings.entrySet()) {
                            if (entry2.getValue() instanceof Map) {
                                Map<String, Object> buffData = (Map<String, Object>) entry2.getValue();
                                String Identifier = (String) buffData.getOrDefault("effectIdentifier", null);
                                int duration = (int) buffData.getOrDefault("duration", 300);
                                int amplifier = (int) buffData.getOrDefault("amplifier", 0);
                                boolean ambient = (boolean) buffData.getOrDefault("ambient", false);

                                StatusEffect StatusEffect = null;

                                if (Identifier != null) {
                                    StatusEffect = Registries.STATUS_EFFECT.get(new Identifier(Identifier));
                                } else {
                                    StatusEffect = SPEED;
                                }

                                StatusEffectInstance StatusEffectInstance = new StatusEffectInstance(StatusEffect, duration, amplifier, ambient, true);
                                statusEffectInstanceList.add(StatusEffectInstance);
                            }
                        }
                    }

                    statusEffectInstances = statusEffectInstanceList.toArray(new StatusEffectInstance[statusEffectInstanceList.size()]);

                    Potion ThePotion = new Potion(statusEffectInstances);

                    try {
                        Registry.register(Registries.POTION, new Identifier(namespace, id),
                                ThePotion
                        );
                    } catch (Exception e) {
                        if(net.minecraft.registry.Registries.POTION.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                            setRegistry.set(namespace, id, ThePotion);
                        }
                    }

                    provider.add("item.minecraft.potion.effect."+id, name);
                    provider.add("item.minecraft.splash_potion.effect."+id, name);
                    provider.add("item.minecraft.lingering_potion.effect."+id, name);
                    ReturnMessage.PotionYMLRegister(name, namespace, id);

                    String inputStr = (String) recipeData.getOrDefault("input", null);
                    String materialStr = (String) recipeData.getOrDefault("material", null);
                    String resultStr = (String) recipeData.getOrDefault("result", null);
                    Potion input = Registries.POTION.get(new Identifier(inputStr));
                    Item material = Registries.ITEM.get(new Identifier(materialStr));
                    Potion result = Registries.POTION.get(new Identifier(resultStr));

                    try {
                        BrewingRecipeRegistry.registerPotionRecipe(input, material, result);
                        ReturnMessage.RecipesYMLRegister(name,namespace,id);
                    } catch (Exception e) {
                        Info.error("There is a problem in the recipe of the pharmaceutical material!");
                    }

                }
            }
        }
    }
}
