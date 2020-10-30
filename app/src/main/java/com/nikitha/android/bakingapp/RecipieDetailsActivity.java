package com.nikitha.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.nikitha.android.bakingapp.fragments.DescriptionFragment;
import com.nikitha.android.bakingapp.fragments.MediaPlayerFragment;
import com.nikitha.android.bakingapp.pojo.ListItems;

import static com.nikitha.android.bakingapp.CONSTANTS.DESCRIPTION;
import static com.nikitha.android.bakingapp.CONSTANTS.POSITION_CLICKED;
import static com.nikitha.android.bakingapp.CONSTANTS.RECIPIE_BY_NAME;
import static com.nikitha.android.bakingapp.CONSTANTS.VIDEO_URL;

public class RecipieDetailsActivity extends AppCompatActivity {
    int positionClicked;
    ListItems data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent!=null){
            data = (ListItems) intent.getSerializableExtra(RECIPIE_BY_NAME);
            setTitle(data.getName());
            positionClicked=intent.getIntExtra(POSITION_CLICKED,1);
        }

        if(findViewById(R.id.singlePane_recipieDetails) !=null){
            DescriptionFragment descriptionFragment=new DescriptionFragment();
            MediaPlayerFragment mediaPlayerFragment=new MediaPlayerFragment();

            Bundle bundle=new Bundle();
            bundle.putString(DESCRIPTION,data.getSteps().get(positionClicked).getDescription());
            bundle.putInt(POSITION_CLICKED,positionClicked);
            descriptionFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.description, descriptionFragment).commit();

            Bundle bundleForMP=new Bundle();
            bundleForMP.putString(VIDEO_URL,data.getSteps().get(positionClicked).getVideoURL());
            bundleForMP.putInt(POSITION_CLICKED,positionClicked);
            mediaPlayerFragment.setArguments(bundleForMP);
            getSupportFragmentManager().beginTransaction().add(R.id.mediaPlayer, mediaPlayerFragment).commit();

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