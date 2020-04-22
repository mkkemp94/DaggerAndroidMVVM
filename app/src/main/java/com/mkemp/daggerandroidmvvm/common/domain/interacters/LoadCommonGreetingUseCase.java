package com.mkemp.daggerandroidmvvm.common.domain.interacters;

import com.mkemp.daggerandroidmvvm.common.domain.model.CommonGreetingRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Use Cases are the Domain layer.
 *
 * In a real app a use case would have more functionality,
 * possibly with the help of other components injected into them.
 *
 * The use cases and their helpers could handle validation, reporting and other pre-processing tasks.
 */
public class LoadCommonGreetingUseCase
{
    // Use case has access to the repository it is used for
    private final CommonGreetingRepository mGreetingRepository;
    
    @Inject
    public LoadCommonGreetingUseCase(CommonGreetingRepository greetingRepository)
    {
        mGreetingRepository = greetingRepository;
    }
    
    /**
     * Execute this use case.
     *
     * @return greeting from common greeting repository.
     */
    public Single<String> execute()
    {
        return mGreetingRepository.getGreeting();
    }
}
