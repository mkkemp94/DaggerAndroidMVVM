package com.mkemp.daggerandroidmvvm.data.greeting.base;

import io.reactivex.Single;

public interface ILoadGreetingUseCase
{
    Single<String> execute();
}
