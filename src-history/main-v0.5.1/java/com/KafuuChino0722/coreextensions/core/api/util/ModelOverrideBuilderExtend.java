package com.KafuuChino0722.coreextensions.core.api.util;

import com.google.gson.annotations.SerializedName;
import it.unimi.dsi.fastutil.objects.Object2FloatLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelOverride;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.solid.brrp.v1.mixin.TextureMapAccessor;
import pers.solid.brrp.v1.model.ModelOverrideBuilder;

import java.util.*;

public class ModelOverrideBuilderExtend extends ModelOverrideBuilder {
    @SerializedName("model")
    public final Identifier modelId;
    @SerializedName("predicate")
    public Object2FloatMap<Identifier> conditions;

    public ModelOverrideBuilderExtend(Identifier modelId) {
        super(modelId);
        this.modelId = modelId;
    }

    public ModelOverrideBuilderExtend(String namespace, String value) {
        this(new Identifier(namespace, value));
    }

    public ModelOverrideBuilderExtend(String modelId) {
        this(new Identifier(modelId));
    }

    @Contract(pure = true)
    public static ModelOverrideBuilderExtend of(Identifier modelId, Object2FloatMap<Identifier> conditions) {
        final ModelOverrideBuilderExtend builder = new ModelOverrideBuilderExtend(modelId);
        builder.conditions = conditions;
        return builder;
    }

    @Contract(pure = true)
    public static ModelOverrideBuilderExtend of(Identifier modelId, Identifier type, float threshold) {
        return new ModelOverrideBuilderExtend(modelId).addCondition(type, threshold);
    }

    @Contract(value = "_ -> this", mutates = "this")
    public ModelOverrideBuilderExtend setConditions(Object2FloatMap<Identifier> conditions) {
        this.conditions = conditions;
        return this;
    }

    @Contract(value = "_, _ -> this", mutates = "this")
    public ModelOverrideBuilderExtend addCondition(@NotNull Identifier type, float threshold) {
        if (conditions == null) {
            conditions = new Object2FloatLinkedOpenHashMap<>();
        }
        conditions.put(type, threshold);
        return this;
    }

    @Contract(value = "_, _, _ -> this", mutates = "this")
    public ModelOverrideBuilderExtend addCondition(String namespace, String value, float threshold) {
        return addCondition(new Identifier(namespace, value), threshold);
    }

    @Contract(value = "_, _ -> this", mutates = "this")
    public ModelOverrideBuilderExtend addCondition(String identifier, float threshold) {
        return addCondition(new Identifier(identifier), threshold);
    }

    /**
     * Convert the object to vanilla {@link ModelOverride} object. This method is client-only.
     *
     * @return The vanilla {@link ModelOverride} object.
     */
    @Environment(EnvType.CLIENT)
    @Contract(pure = true)
    public ModelOverride asModelOverride() {
        return new ModelOverride(modelId, conditions.object2FloatEntrySet().stream().map(entry -> new ModelOverride.Condition(entry.getKey(), entry.getFloatValue())).toList());
    }

    @Override
    public ModelOverrideBuilder clone() {
        ModelOverrideBuilder clone = (ModelOverrideBuilder) super.clone();
        clone.conditions = new Object2FloatOpenHashMap<>(this.conditions);
        return clone;
    }
}
