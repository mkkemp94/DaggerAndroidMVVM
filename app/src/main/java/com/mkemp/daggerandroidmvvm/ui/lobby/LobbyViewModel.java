package com.mkemp.daggerandroidmvvm.ui.lobby;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mkemp.daggerandroidmvvm.data.greeting.base.ILoadGreetingUseCase;
import com.mkemp.daggerandroidmvvm.data.greeting.common.LoadCommonGreetingUseCase;
import com.mkemp.daggerandroidmvvm.data.greeting.lobby.LoadLobbyGreetingUseCase;
import com.mkemp.daggerandroidmvvm.ui.lobby.models.Response;
import com.mkemp.daggerandroidmvvm.util.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;

/**
 * This ViewModel (presentation layer)
 * interacts with the repository (data layer)
 * by using the use cases (domain layer)
 */
public class LobbyViewModel extends ViewModel
{
    // Use cases
    // Notice we use these to access repository --
    // we don't access repository directly
    private final LoadCommonGreetingUseCase mLoadCommonGreetingUseCase;
    private final LoadLobbyGreetingUseCase mLoadLobbyGreetingUseCase;
    
    // Custom Scheduler to remove Android-specific class from our ViewModel.
    private final SchedulersFacade mSchedulersFacade;
    
    // Provided by rxJava. This is a container to hold onto disposables.
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    
    // MutableLiveData. This holds the responses from my Use Cases
    // Value will be set in subscribe()
    private final MutableLiveData<Response> mResponse = new MutableLiveData<>();
    
    LobbyViewModel(LoadCommonGreetingUseCase loadCommonGreetingUseCase,
                          LoadLobbyGreetingUseCase loadLobbyGreetingUseCase,
                          SchedulersFacade schedulersFacade)
    {
        mLoadCommonGreetingUseCase = loadCommonGreetingUseCase;
        mLoadLobbyGreetingUseCase = loadLobbyGreetingUseCase;
        mSchedulersFacade = schedulersFacade;
    }
    
    /**
     * This method is provided by the ViewModel we are extending.
     * Clear any disposables we are holding.
     */
    @Override
    protected void onCleared()
    {
        mDisposables.clear();
    }
    
    /**
     * This will presumably be called by the view. Maybe by the button.
     * <p>
     * It will call the private loadGreeting() method, passing in the appropriate use case.
     */
    void loadCommonGreeting()
    {
        loadGreeting(mLoadCommonGreetingUseCase);
    }
    
    /**
     * This will presumably be called by the view. Maybe by the button.
     * <p>
     * It will call the private loadGreeting() method, passing in the appropriate use case.
     */
    void loadLobbyGreeting()
    {
        loadGreeting(mLoadLobbyGreetingUseCase);
    }
    
    /**
     * This will presumably be called by the view.
     *
     * This gives back the mutable live data we are observing for use case responses.
     *
     * @return the mutable responses
     */
    MutableLiveData<Response> response()
    {
        return mResponse;
    }
    
    /**
     * Pass a use case to this method.
     * <p>
     * When we execute() this use case, it will return a Single which we can subscribe on.
     * <p>
     * Subscribe on the io thread, and observe the result on the ui thread.
     * <p>
     * When we subscribe, set the MutableLiveData mResponse value to loading.
     * <p>
     * When we get a successful response, set the mResponse value to greeting,
     * <p>
     * When we get an error, set the mResponse value to the throwable received.
     *
     * @param loadGreetingUseCase use case - we don't know which. But we know it can execute()
     */
    private void loadGreeting(ILoadGreetingUseCase loadGreetingUseCase)
    {
        mDisposables.add(
                loadGreetingUseCase.execute()
                                   .subscribeOn(mSchedulersFacade.io())
                                   .observeOn(mSchedulersFacade.ui())
                                   .doOnSubscribe(it -> mResponse.setValue(Response.loading()))
                                   .subscribe(
                                           greeting -> mResponse.setValue(Response.success(greeting)),
                                           throwable -> mResponse.setValue(Response.error(throwable))
                                   )
        );
    }
}
