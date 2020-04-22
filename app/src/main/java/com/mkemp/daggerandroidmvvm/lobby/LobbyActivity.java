package com.mkemp.daggerandroidmvvm.lobby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mkemp.daggerandroidmvvm.R;

/**
 * This is the View.
 *
 * Makes up part of the Presentation layer.
 */
public class LobbyActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);
    }
}
