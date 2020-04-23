package com.mkemp.daggerandroidmvvm.ui.lobby.models;

import android.support.annotation.NonNull;

import javax.annotation.Nullable;

/**
 * Response holder provided to the UI
 */
public class Response
{
    public final Status mStatus;
    
    @Nullable
    public final String mData;
    
    @Nullable
    public final Throwable mError;
    
    /**
     * Note that this is private. Can only be accessed by inner methods.
     *
     * @param status the status of this response
     * @param data this response's data
     * @param error this response's error
     */
    private Response(Status status, @Nullable String data, @Nullable Throwable error)
    {
        mStatus = status;
        mData = data;
        mError = error;
    }
    
    public static Response loading()
    {
        return new Response(Status.LOADING, null, null);
    }
    
    public static Response success(@NonNull String data)
    {
        return new Response(Status.SUCCESS, data, null);
    }
    
    public static Response error(@NonNull Throwable error)
    {
        return new Response(Status.ERROR, null, error);
    }
}
