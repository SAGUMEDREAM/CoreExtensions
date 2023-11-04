package com.KafuuChino0722.coreextensions.core.template;

public class Template {

    public static void Registry(TemplateType type) {
    }

    public static void Item(String namespace, String id, String TemplateIdentifier) {
        if(TemplateIdentifier!=null) {
            TemplateItem.register(namespace, id, TemplateIdentifier);
        }
    }
    public static void Block(String namespace, String id, String TemplateIdentifier) {
        if(TemplateIdentifier!=null) {
            TemplateBlock.register(namespace, id, TemplateIdentifier);
        }
    }
}
