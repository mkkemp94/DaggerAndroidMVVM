package com.mkemp.daggerandroidmvvm.injection.module;

import com.mkemp.daggerandroidmvvm.data.greeting.common.LoadCommonGreetingUseCase;
import com.mkemp.daggerandroidmvvm.data.greeting.lobby.LoadLobbyGreetingUseCase;
import com.mkemp.daggerandroidmvvm.data.greeting.lobby.LobbyGreetingRepository;
import com.mkemp.daggerandroidmvvm.ui.lobby.LobbyViewModelFactory;
import com.mkemp.daggerandroidmvvm.util.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;

/**
 * Define LobbyActivity-specific dependencies here.
 */
@Module
public class LobbyModule
{
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