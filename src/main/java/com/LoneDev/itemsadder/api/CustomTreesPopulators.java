package com.LoneDev.itemsadder.api;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

public class CustomTreesPopulators {
    public static Block Log;
    public static Block Leaves;

    public void load(String namespace, Map<String, Object> DataAll) {

        if(DataAll.containsKey("trees_populators")) {
            try {
                Map<String, Object> trees = (Map<String, Object>) DataAll.get("trees_populators");

                if (trees != null) {
                    for (Map.Entry<String, Object> itemEntry : trees.entrySet()) {
                        String TreesPopulatorsId = itemEntry.getKey();
                        Map<String, Object> treeData = (Map<String, Object>) itemEntry.getValue();
                        if(treeData.containsKey("log")) {
                            Log = Registries.BLOCK.get(new Identifier((String) treeData.get("log")));
                        }
                        if(treeData.containsKey("leaves")) {
                            Leaves = Registries.BLOCK.get(new Identifier((String) treeData.get("leaves")));
                        }


                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
