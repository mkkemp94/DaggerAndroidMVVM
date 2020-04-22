package com.mkemp.daggerandroidmvvm.rx;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides various threading schedulers.
 *
 * A Scheduler can be thought of as a thread pool managing 1 or more threads.
 *
 * Created to avoid using Android-specific classes inside our ViewModel
 * so that our ViewModel can be easily unit tested.
 */
public class SchedulersFacade
{
    @Inject
    public SchedulersFacade() {}
    
    /**
     * IO thread pool scheduler
     *
     * Backed by an unbounded thread pool.
     *
     * This should be used for mom CPU-intensive I/O work, ie:
     * - interaction with the file system
     * - performing network calls
     * - database interactions
     *
     * This thread pool is intended to be used for asynchronously performing blocking IO.
     */
    public Scheduler io()
    {
        return Schedulers.io();
    }
    
    /**
     * Computation thread pool scheduler
     *
     * Backed by a bounded thread pool with size up to the number of available processors.
     *
     * Used for computational or CPU-intensive work, ie:
     * - resizing images
     * - processing large data sets
     *
     * Be careful: if more threads are allocated than available cores,
     * performance will degrade.
     */
    public Scheduler computation()
    {
        return Schedulers.computation();
    }
    
    /**
     * Main Thread scheduler
     *
     * User interaction happens here.
     *
     * Be careful not to overload this thread to prevent ANR.
     */
    public Scheduler ui()
    {
        return AndroidSchedulers.mainThread();
    }
}
