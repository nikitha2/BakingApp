package com.nikitha.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.nikitha.android.bakingapp.fragments.DescriptionFragment;
import com.nikitha.android.bakingapp.fragments.MediaPlayerFragment;
import com.nikitha.android.bakingapp.pojo.ListItems;

import static com.nikitha.android.bakingapp.CONSTANTS.DESCRIPTION;
import static com.nikitha.android.bakingapp.CONSTANTS.POSITION_CLICKED;
import static com.nikitha.android.bakingapp.CONSTANTS.RECIPIE_BY_NAME;
import static com.nikitha.android.bakingapp.CONSTANTS.VIDEO_URL;

public class RecipieDetailsActivity extends AppCompatActivity  {
    private static final String TAG = RecipieDetailsActivity.class.getSimpleName();
    int positionClicked;
    ListItems data;
    Button btn;
    int numOfSteps;
    DescriptionFragment descriptionFragment;
    MediaPlayerFragment mediaPlayerFragment;
    FrameLayout mPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn=findViewById(R.id.nextButton);

        Intent intent = getIntent();
        if(intent!=null){
            data = (ListItems) intent.getSerializableExtra(RECIPIE_BY_NAME);
            setTitle(data.getName());
            positionClicked=intent.getIntExtra(POSITION_CLICKED,1);
        }

        numOfSteps= data.getSteps().size();
        if(findViewById(R.id.singlePane_recipieDetails) !=null){
            descriptionFragment=new DescriptionFragment();
            mediaPlayerFragment=new MediaPlayerFragment();


            Bundle bundle=new Bundle();
            String desc=data.getSteps().get(positionClicked).getDescription();
            bundle.putString(DESCRIPTION,desc);
            bundle.putInt(POSITION_CLICKED,positionClicked);
            descriptionFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.description, descriptionFragment).commit();

            Bundle bundleForMP=new Bundle();
            String url=data.getSteps().get(positionClicked).getVideoURL();
            bundleForMP.putString(VIDEO_URL,url);
            bundleForMP.putInt(POSITION_CLICKED,positionClicked);
            mediaPlayerFragment.setArguments(bundleForMP);
            getSupportFragmentManager().beginTransaction().add(R.id.mediaPlayer, mediaPlayerFragment).commit();

            mPlayerView=findViewById(R.id.mediaPlayer);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionClicked=positionClicked+1;
                Bundle bundle=new Bundle();
                Bundle bundleForMP=new Bundle();
                descriptionFragment=new DescriptionFragment();
                mediaPlayerFragment=new MediaPlayerFragment();

                if(positionClicked< numOfSteps){
                    String desc=data.getSteps().get(positionClicked).getDescription();
                    bundle.putString(DESCRIPTION,desc);
                    bundle.putInt(POSITION_CLICKED,positionClicked);

                    String url=data.getSteps().get(positionClicked).getVideoURL();
                    bundleForMP.putString(VIDEO_URL,url);
                    bundleForMP.putInt(POSITION_CLICKED,positionClicked);

                }else{
                    positionClicked=1;
                    String desc=data.getSteps().get(positionClicked).getDescription();
                    bundle.putString(DESCRIPTION,desc);
                    bundle.putInt(POSITION_CLICKED,positionClicked);
                    getSupportFragmentManager().beginTransaction().replace(R.id.description, descriptionFragment).commit();

                    String url=data.getSteps().get(positionClicked).getVideoURL();
                    bundleForMP.putString(VIDEO_URL,url);
                    bundleForMP.putInt(POSITION_CLICKED,positionClicked);

                }
                descriptionFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.description, descriptionFragment).commit();

                mediaPlayerFragment.setArguments(bundleForMP);
                getSupportFragmentManager().beginTransaction().replace(R.id.mediaPlayer, mediaPlayerFragment).commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mPlayerView.getLayoutParams();
            params.width = params.MATCH_PARENT;
            params.height = params.MATCH_PARENT;
            mPlayerView.setLayoutParams(params);
            getSupportActionBar().hide();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}