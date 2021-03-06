package com.mkemp.daggerandroidmvvm.data.greeting.lobby;

import io.reactivex.Single;

/**
 * The repository pattern is a way to organize your code such that
 * your ViewModel or Presenter class doesn't need to care about
 * where your data comes from.
 *
 * It only cares about how to request data and what it gets back.
 *
 * Repositories are the Data layer.
 */
public class LobbyGreetingRepository
{
    /**
     * Expose a lobby greeting string using RxJava 2
     *
     * A bit overkill for synchronous code like this...
     *
     * Would be better for asynchronous calls.
     *
     * @return a lobby greeting string
     */
    public Single<String> getGreeting()
    {
        return Single.just("Hello from LobbyGreetingRepository");
    }
}
