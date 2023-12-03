package com.KafuuChino0722.coreextensions.gametest.beta;

import com.mojang.serialization.Codec;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureManager;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

public class Feature_Gametest {

    //public static FeatureFlag COREEXTENSIONS;
    public static Codec<FeatureSet> CODEC;
    public static FeatureManager FEATURE_MANAGER;
    public static FeatureSet VANILLA_FEATURES;
    public static FeatureSet DEFAULT_ENABLED_FEATURES;

    public static void test() {
        FeatureManager.Builder builder = new FeatureManager.Builder("main");
        FeatureFlag CEX = builder.addFlag(new Identifier("coreextensions","addon"));
        FEATURE_MANAGER = builder.build();
        CODEC = FEATURE_MANAGER.getCodec();
        DEFAULT_ENABLED_FEATURES = VANILLA_FEATURES = FeatureSet.of(CEX);
    }
}
