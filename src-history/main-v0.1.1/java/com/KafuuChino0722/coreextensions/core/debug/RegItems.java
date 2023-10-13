package com.KafuuChino0722.coreextensions.core.debug;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.Main.pack;

public class RegItems {
    public static final String FILE = Reference.File;

    public static Item registerItem(String namespace, String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name), item);
    }

    public static ToolItem registerAxe(String namespace, String name, float attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new AxeItem(material, (float) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static ToolItem registerPickaxe(String namespace, String name, int attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new PickaxeItem(material, attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static ToolItem registerShovel(String namespace, String name, double attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new ShovelItem(material, (float) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static ToolItem registerHoe(String namespace, String name, double attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new HoeItem(material, (int) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static ToolItem registerSword(String namespace, String name, double attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new SwordItem(material, (int) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }
    private static Item registerBall(String namespace, String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name), item);
    }




    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE);

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/item.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("items")) {
                                Map<String, Object> items = itemsData.get("items");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                                        Map<String, Object> Data = (Map<String, Object>) entry.getValue();
                                        String name = (String) itemData.get("name");
                                        String lang_us = (String) itemData.get("name");
                                        String namespace = (String) itemData.get("namespace");
                                        String id = (String) itemData.get("id");
                                        int maxCount = (int) itemData.get("maxCount");
                                        String types = (String) itemData.get("types");

                                        if(Objects.equals(types, "item") || Objects.equals(types, "ITEM")) {
                                            Item item = new Item(new Item.Settings().maxCount(maxCount));
                                            registerItem(namespace, id, item);
                                            ReturnMessage.ItemYMLRegister(name,namespace,id);
                                        } else if(Objects.equals(types, "sword") || Objects.equals(types, "SWORD")) { //Sword
                                            double attackDamage = (double) itemData.get("attackDamage");
                                            double attackSpeed = (double) itemData.get("attackSpeed");
                                            int durability = (int) itemData.get("durability"); // Parse durability attribute
                                            ToolMaterial material = ToolMaterials.IRON; // Default material

                                            String repairMaterialId = (String) itemData.get("repairMaterial");
                                            Item repairMaterialItem = Registries.ITEM.get(new Identifier(repairMaterialId));
                                            if (repairMaterialItem instanceof ToolItem) {
                                                material = ((ToolItem) repairMaterialItem).getMaterial();
                                            }
                                            Main.LOGGER.info("Item " + name + "<->" + namespace + ":" + id + " registered!");
                                            ToolItem sword = registerSword(namespace, id, (double) attackDamage, (float) attackSpeed, durability, (ToolMaterials) material);
                                            ReturnMessage.ItemYMLRegister(name,namespace,id);

                                        } else if(Objects.equals(types, "pickaxe") || Objects.equals(types, "PICKAXE")) { //Pickaxe
                                            double attackDamage = (double) itemData.get("attackDamage");
                                            double attackSpeed = (double) itemData.get("attackSpeed");
                                            int durability = (int) itemData.get("durability"); // Parse durability attribute
                                            ToolMaterial material = ToolMaterials.IRON; // Default material

                                            String repairMaterialId = (String) itemData.get("repairMaterial");
                                            Item repairMaterialItem = Registries.ITEM.get(new Identifier(repairMaterialId));
                                            if (repairMaterialItem instanceof ToolItem) {
                                                material = ((ToolItem) repairMaterialItem).getMaterial();
                                            }
                                            ToolItem pickaxe = registerPickaxe(namespace, id, (int) attackDamage, (float) attackSpeed, durability, (ToolMaterials) material);
                                            ReturnMessage.ItemYMLRegister(name,namespace,id);
                                        } else if(Objects.equals(types, "axe") || Objects.equals(types, "AXE")) { //Axe
                                            double attackDamage = (double) itemData.get("attackDamage");
                                            double attackSpeed = (double) itemData.get("attackSpeed");
                                            int durability = (int) itemData.get("durability"); // Parse durability attribute
                                            ToolMaterial material = ToolMaterials.IRON; // Default material

                                            String repairMaterialId = (String) Data.get("repairMaterial");
                                            Item repairMaterialItem = Registries.ITEM.get(new Identifier(repairMaterialId));
                                            if (repairMaterialItem instanceof ToolItem) {
                                                material = ((ToolItem) repairMaterialItem).getMaterial();
                                            }
                                            ToolItem axe = registerAxe(namespace, id, (float) attackDamage, (float) attackSpeed, durability, (ToolMaterials) material);
                                            ReturnMessage.ItemYMLRegister(name,namespace,id);
                                        } else if(Objects.equals(types, "shovel") || Objects.equals(types, "SHOVEL")) { //Shovel
                                            double attackDamage = (double) itemData.get("attackDamage");
                                            double attackSpeed = (double) itemData.get("attackSpeed");
                                            int durability = (int) itemData.get("durability"); // Parse durability attribute
                                            ToolMaterial material = ToolMaterials.IRON; // Default material

                                            String repairMaterialId = (String) itemData.get("repairMaterial");
                                            Item repairMaterialItem = Registries.ITEM.get(new Identifier(repairMaterialId));
                                            if (repairMaterialItem instanceof ToolItem) {
                                                material = ((ToolItem) repairMaterialItem).getMaterial();
                                            }
                                            ToolItem shovel = registerShovel(namespace, id, (double) attackDamage, (float) attackSpeed, durability, (ToolMaterials) material);
                                            ReturnMessage.ItemYMLRegister(name,namespace,id);
                                        } else if(Objects.equals(types, "hoe") || Objects.equals(types, "HOE")) { //Hoes
                                            double attackDamage = (double) itemData.get("attackDamage");
                                            double attackSpeed = (double) itemData.get("attackSpeed");
                                            int durability = (int) itemData.get("durability"); // Parse durability attribute


                                            ToolMaterial material = ToolMaterials.IRON; // Default material

                                            String repairMaterialId = (String) itemData.get("repairMaterial");
                                            Item repairMaterialItem = Registries.ITEM.get(new Identifier(repairMaterialId));
                                            if (repairMaterialItem instanceof ToolItem) {
                                                material = ((ToolItem) repairMaterialItem).getMaterial();
                                            }

                                            ToolItem hoe = registerHoe(namespace, id, (double) attackDamage, (float) attackSpeed, durability, (ToolMaterials) material);
                                            ReturnMessage.ItemYMLRegister(name,namespace,id);
                                        } else if(Objects.equals(types, "ball")) {
                                            Item item = new SnowballItem(new Item.Settings().maxCount(maxCount));
                                            registerBall(namespace, id, item);
                                            ReturnMessage.ItemYMLRegister(name,namespace,id);
                                        } else {
                                            ReturnMessage.ItemYMLTYPEERROR(name,namespace,id);
                                        }

                                        RRPCallback.BEFORE_VANILLA.register(b -> {
                                            pack.clearResources();
                                            pack.addLang(new Identifier(namespace, "en_us"), LanguageProvider.create()
                                                    .add("item."+namespace+"."+id, lang_us));
                                            pack.addLang(new Identifier(namespace, "zh_cn"), LanguageProvider.create()
                                                    .add("item."+namespace+"."+id, name));
                                            b.add(pack);
                                        });

                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
