package com.mkemp.daggerandroidmvvm.common.domain.interacters;

import io.reactivex.Single;

public interface ILoadGreetingUseCase
{
    Single<String> execute();
}
