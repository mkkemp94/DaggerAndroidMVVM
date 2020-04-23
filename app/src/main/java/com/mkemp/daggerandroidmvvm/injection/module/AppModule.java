package com.mkemp.daggerandroidmvvm.injection.module;


import android.content.Context;

import com.mkemp.daggerandroidmvvm.App;
import com.mkemp.daggerandroidmvvm.data.greeting.common.CommonGreetingRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {
    
    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }
    
    @Singleton
    @Provides
    CommonGreetingRepository provideCommonHelloService() {
        return new CommonGreetingRepository();
    }
}