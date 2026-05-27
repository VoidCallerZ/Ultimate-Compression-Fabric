package com.voidcallerz.uc;

import net.fabricmc.api.ClientModInitializer;

public class UltimateCompressionClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        UCClientSetup.registerClientSetup();
    }
}