package com.mkemp.daggerandroidmvvm.lobby;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.mkemp.daggerandroidmvvm.common.domain.interacters.LoadCommonGreetingUseCase;
import com.mkemp.daggerandroidmvvm.rx.SchedulersFacade;

/**
 * LobbyViewModel requires certain dependencies to be provided at runtime.
 * Use this ViewModelFactory to instantiate it.
 */
public class LobbyViewModelFactory implements ViewModelProvider.Factory
{
    private final LoadCommonGreetingUseCase mLoadCommonGreetingUseCase;
    private final LoadLobbyGreetingUseCase mLoadLobbyGreetingUseCase;
    private final SchedulersFacade mSchedulersFacade;
    
    LobbyViewModelFactory(LoadCommonGreetingUseCase loadCommonGreetingUseCase,
                          LoadLobbyGreetingUseCase loadLobbyGreetingUseCase,
                          SchedulersFacade schedulersFacade)
    {
        mLoadCommonGreetingUseCase = loadCommonGreetingUseCase;
        mLoadLobbyGreetingUseCase = loadLobbyGreetingUseCase;
        mSchedulersFacade = schedulersFacade;
    }
    
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        if (modelClass.isAssignableFrom(LobbyViewModel.class))
        {
            return (T) new LobbyViewModel(mLoadCommonGreetingUseCase,
                                          mLoadLobbyGreetingUseCase,
                                          mSchedulersFacade);
        }
        
        throw  new IllegalArgumentException("Unknown ViewModel class");
    }
}
