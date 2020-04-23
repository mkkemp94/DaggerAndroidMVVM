package com.mkemp.daggerandroidmvvm.lobby;

import com.mkemp.daggerandroidmvvm.common.domain.interacters.LoadCommonGreetingUseCase;
import com.mkemp.daggerandroidmvvm.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;

/**
 * Define LobbyActivity-specific dependencies here.
 */
@Module
public class LobbyModule {
    
    @Provides
    LobbyGreetingRepository provideLobbyGreetingRepository() {
        return new LobbyGreetingRepository();
    }
    
    @Provides
    LobbyViewModelFactory provideLobbyViewModelFactory(LoadCommonGreetingUseCase loadCommonGreetingUseCase,
                                                       LoadLobbyGreetingUseCase loadLobbyGreetingUseCase,
                                                       SchedulersFacade schedulersFacade) {
        return new LobbyViewModelFactory(loadCommonGreetingUseCase, loadLobbyGreetingUseCase, schedulersFacade);
    }
}