package com.LoneDev.itemsadder.api;

import java.util.Map;

import static com.LoneDev.itemsadder.Main.IaLanguageProvider;

public class CustomLanguages {
    public void load(String namespace, Map<String, Object> DataAll) {
        if(DataAll.containsKey("minecraft_lang_overwrite")) {
            try {
                Map<String, Object> lang = (Map<String, Object>) DataAll.get("minecraft_lang_overwrite");

                if (lang != null) {
                    for (Map.Entry<String, Object> langEntry : lang.entrySet()) {
                        Map<String, Object> langKey = (Map<String, Object>) langEntry.getValue();
                        if(langKey.containsKey("entries")) {
                            Map<String, Object> entriesKey = (Map<String, Object>) langKey.get("entries");
                            for (Map.Entry<String, Object> entry : entriesKey.entrySet()) {
                                IaLanguageProvider.add(entry.getKey(), (String) entry.getValue());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
