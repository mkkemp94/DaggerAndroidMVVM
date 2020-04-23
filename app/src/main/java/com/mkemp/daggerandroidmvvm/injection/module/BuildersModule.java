package com.mkemp.daggerandroidmvvm.injection.module;


import com.mkemp.daggerandroidmvvm.ui.lobby.LobbyActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {
    
    @ContributesAndroidInjector(modules = LobbyModule.class)
    abstract LobbyActivity bindLobbyActivity();
    
    // Add bindings for other sub-components here
}