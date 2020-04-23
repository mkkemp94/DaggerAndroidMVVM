package com.mkemp.daggerandroidmvvm.ui.lobby;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mkemp.daggerandroidmvvm.R;
import com.mkemp.daggerandroidmvvm.ui.lobby.models.Response;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * This is the View.
 *
 * Makes up part of the Presentation layer.
 *
 * Subscribed to receive the following events from ViewModel:
 * 1) Data loading began
 * 2) Data loading success
 * 3) Data loading error
 *
 * This data is exposed by the ViewModel via two live streams.
 * The data will be in the form of a Response object.
 */
public class LobbyActivity extends AppCompatActivity
{
    @Inject
    LobbyViewModelFactory mViewModelFactory;
    
    @BindView(R.id.greeting_textview)
    TextView mGreetingTextView;
    
    @BindView(R.id.loading_indicator)
    ProgressBar mLoadingIndicator;
    
    // Notice that this isn't injected... we use the view model factory to create this
    private LobbyViewModel mViewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);
    
        ButterKnife.bind(this);
        
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(LobbyViewModel.class);
        mViewModel.response().observe(this, response -> processResponse(response));
    }
   
    @OnClick(R.id.common_greeting_button)
    void onCommonGreetingButtonClicked()
    {
        mViewModel.loadCommonGreeting();
    }
    
    @OnClick(R.id.lobby_greeting_button)
    void onLobbyGreetingButtonClicked()
    {
        mViewModel.loadLobbyGreeting();
    }
    
    private void processResponse(Response response)
    {
        switch (response.mStatus)
        {
            case LOADING:
                renderLoadingState();
                break;
                
            case SUCCESS:
                renderDataState(response.mData);
                break;
                
            case ERROR:
                renderErrorState(response.mError);
        }
    }
    
    private void renderLoadingState()
    {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mGreetingTextView.setVisibility(View.GONE);
    }
    
    private void renderDataState(String data)
    {
        mLoadingIndicator.setVisibility(View.GONE);
        mGreetingTextView.setVisibility(View.VISIBLE);
        mGreetingTextView.setText(data);
    }
    
    private void renderErrorState(Throwable error)
    {
        Timber.e(error);
        mLoadingIndicator.setVisibility(View.GONE);
        mGreetingTextView.setVisibility(View.GONE);
        Toast.makeText(this, R.string.greeting_error, Toast.LENGTH_SHORT).show();
    }
}
