package com.KafuuChino0722.coreextensions.util;

import com.google.common.collect.Lists;
import com.google.gson.*;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.fabric.api.recipe.v1.ingredient.FabricIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class IngredientPubilc implements Predicate<ItemStack>, FabricIngredient {
    public static final IngredientPubilc EMPTY = new IngredientPubilc(Stream.empty());
    private final Entry[] entries;
    @Nullable
    private ItemStack[] matchingStacks;
    @Nullable
    private IntList ids;

    private IngredientPubilc(Stream<? extends Entry> entries) {
        this.entries = (Entry[])entries.toArray((i) -> {
            return new Entry[i];
        });
    }

    public ItemStack[] getMatchingStacks() {
        if (this.matchingStacks == null) {
            this.matchingStacks = (ItemStack[])Arrays.stream(this.entries).flatMap((entry) -> {
                return entry.getStacks().stream();
            }).distinct().toArray((i) -> {
                return new ItemStack[i];
            });
        }

        return this.matchingStacks;
    }

    public boolean test(@Nullable ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        } else if (this.isEmpty()) {
            return itemStack.isEmpty();
        } else {
            ItemStack[] var2 = this.getMatchingStacks();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                ItemStack itemStack2 = var2[var4];
                if (itemStack2.isOf(itemStack.getItem())) {
                    return true;
                }
            }

            return false;
        }
    }

    public IntList getMatchingItemIds() {
        if (this.ids == null) {
            ItemStack[] itemStacks = this.getMatchingStacks();
            this.ids = new IntArrayList(itemStacks.length);
            ItemStack[] var2 = itemStacks;
            int var3 = itemStacks.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                ItemStack itemStack = var2[var4];
                this.ids.add(RecipeMatcher.getItemId(itemStack));
            }

            this.ids.sort(IntComparators.NATURAL_COMPARATOR);
        }

        return this.ids;
    }

    public void write(PacketByteBuf buf) {
        buf.writeCollection(Arrays.asList(this.getMatchingStacks()), PacketByteBuf::writeItemStack);
    }

    public JsonElement toJson() {
        if (this.entries.length == 1) {
            return this.entries[0].toJson();
        } else {
            JsonArray jsonArray = new JsonArray();
            Entry[] var2 = this.entries;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Entry entry = var2[var4];
                jsonArray.add(entry.toJson());
            }

            return jsonArray;
        }
    }

    public boolean isEmpty() {
        return this.entries.length == 0;
    }

    public static IngredientPubilc ofEntries(Stream<? extends Entry> entries) {
        IngredientPubilc ingredient = new IngredientPubilc(entries);
        return ingredient.isEmpty() ? EMPTY : ingredient;
    }

    public static IngredientPubilc empty() {
        return EMPTY;
    }

    public static IngredientPubilc ofItems(ItemConvertible... items) {
        return ofStacks(Arrays.stream(items).map(ItemStack::new));
    }

    public static IngredientPubilc ofStacks(ItemStack... stacks) {
        return ofStacks(Arrays.stream(stacks));
    }

    public static IngredientPubilc ofStacks(Stream<ItemStack> stacks) {
        return ofEntries(stacks.filter((stack) -> {
            return !stack.isEmpty();
        }).map(StackEntry::new));
    }

    public static IngredientPubilc fromTag(TagKey<Item> tag) {
        return ofEntries(Stream.of(new TagEntry(tag)));
    }

    public static IngredientPubilc fromPacket(PacketByteBuf buf) {
        return ofEntries(buf.readList(PacketByteBuf::readItemStack).stream().map(StackEntry::new));
    }

    public static IngredientPubilc fromJson(@Nullable JsonElement json) {
        return fromJson(json, true);
    }

    public static IngredientPubilc fromJson(@Nullable JsonElement json, boolean allowAir) {
        if (json != null && !json.isJsonNull()) {
            if (json.isJsonObject()) {
                return ofEntries(Stream.of(entryFromJson(json.getAsJsonObject())));
            } else if (json.isJsonArray()) {
                JsonArray jsonArray = json.getAsJsonArray();
                if (jsonArray.size() == 0 && !allowAir) {
                    throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
                } else {
                    return ofEntries(StreamSupport.stream(jsonArray.spliterator(), false).map((jsonElement) -> {
                        return entryFromJson(JsonHelper.asObject(jsonElement, "item"));
                    }));
                }
            } else {
                throw new JsonSyntaxException("Expected item to be object or array of objects");
            }
        } else {
            throw new JsonSyntaxException("Item cannot be null");
        }
    }

    public static Entry entryFromJson(JsonObject json) {
        if (json.has("item") && json.has("tag")) {
            throw new JsonParseException("An ingredient entry is either a tag or an item, not both");
        } else if (json.has("item")) {
            Item item = ShapedRecipe.getItem(json);
            return new StackEntry(new ItemStack(item));
        } else if (json.has("tag")) {
            Identifier identifier = new Identifier(JsonHelper.getString(json, "tag"));
            TagKey<Item> tagKey = TagKey.of(RegistryKeys.ITEM, identifier);
            return new TagEntry(tagKey);
        } else {
            throw new JsonParseException("An ingredient entry needs either a tag or an item");
        }
    }

    public interface Entry {
        Collection<ItemStack> getStacks();

        JsonObject toJson();
    }

    public static class TagEntry implements Entry {
        private final TagKey<Item> tag;

        TagEntry(TagKey<Item> tag) {
            this.tag = tag;
        }

        public Collection<ItemStack> getStacks() {
            List<ItemStack> list = Lists.newArrayList();
            Iterator var2 = Registries.ITEM.iterateEntries(this.tag).iterator();

            while(var2.hasNext()) {
                RegistryEntry<Item> registryEntry = (RegistryEntry)var2.next();
                list.add(new ItemStack(registryEntry));
            }

            return list;
        }

        public JsonObject toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("tag", this.tag.id().toString());
            return jsonObject;
        }
    }

    static class StackEntry implements Entry {
        private final ItemStack stack;

        StackEntry(ItemStack stack) {
            this.stack = stack;
        }

        public Collection<ItemStack> getStacks() {
            return Collections.singleton(this.stack);
        }

        public JsonObject toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Registries.ITEM.getId(this.stack.getItem()).toString());
            return jsonObject;
        }
    }
}
