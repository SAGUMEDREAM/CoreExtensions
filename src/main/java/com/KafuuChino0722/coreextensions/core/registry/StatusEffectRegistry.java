package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.api.MethodStatusEffectCategory;
import com.KafuuChino0722.coreextensions.entity.StandardStatusEffect;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class StatusEffectRegistry {
    public static void register() {
        Map<String, Map<String, Object>> blocksData = IOFileManager.read("statusEffect.yml");
        load(blocksData);
        Map<String, Map<String, Object>> blocksDataZ = IOFileManager.readZip("statusEffect.yml");
        load(blocksDataZ);
    }

    public static void load(Map<String, Map<String, Object>> statusData) {
        if (statusData != null && statusData.containsKey("effects")) {
            Map<String, Object> status = statusData.get("effects");

            for (Map.Entry<String, Object> entry : status.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> statuData = (Map<String, Object>) entry.getValue();
                    String name = (String) statuData.getOrDefault("name", entry.getKey());

                    String namespace = (String) statuData.getOrDefault("namespace","minecraft");
                    String id = (String) statuData.getOrDefault("id","minecraft");

                    Map<String, Object> properties = (Map<String, Object>) statuData.getOrDefault("properties", entry.getValue());

                    int color = (int) properties.getOrDefault("color",0xFFFFFF);

                    String categoryStr = (String) properties.getOrDefault("category","BENEFICIAL");
                    StatusEffectCategory statusEffectCategory = MethodStatusEffectCategory.getCategory(categoryStr);

                    StatusEffect statusEffect = new StandardStatusEffect(statusEffectCategory, color) {

                        @Override
                        public boolean canApplyUpdateEffect(int duration, int amplifier) {
                            return true;
                        }

                        @Override
                        public void applyUpdateEffect(LivingEntity entity, int amplifier) {

                        }
                    };

                    try {
                        Registry.register(Registries.STATUS_EFFECT,new Identifier(namespace,id),statusEffect);
                    } catch (Exception e) {
                        setRegistry.set(namespace, id, statusEffect);
                    }

                    provider.add("effect."+namespace+"."+id, name);
                }
            }
        }
    }
}
