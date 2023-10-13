package com.KafuuChino0722.coreextensions.proxy;

import com.KafuuChino0722.coreextensions.keyboard.RegistryKeyBinding;

public class ClientProxy extends CommonProxy{

    public void onInitializeClient() {
        RegistryKeyBinding.bootstrap();
    }
}
