package com.nikitha.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import com.nikitha.android.bakingapp.fragments.DescriptionFragment;
import com.nikitha.android.bakingapp.fragments.MediaPlayerFragment;
import com.nikitha.android.bakingapp.fragments.RecipieFragment;
import com.nikitha.android.bakingapp.pojo.ListItems;
import androidx.appcompat.app.AppCompatActivity;

import static com.nikitha.android.bakingapp.CONSTANTS.DESCRIPTION;
import static com.nikitha.android.bakingapp.CONSTANTS.POSITION_CLICKED;
import static com.nikitha.android.bakingapp.CONSTANTS.RECIPIE_BY_NAME;
import static com.nikitha.android.bakingapp.CONSTANTS.SINGLE_PANE;
import static com.nikitha.android.bakingapp.CONSTANTS.VIDEO_URL;

public class RecipieActivity extends AppCompatActivity implements RecipieFragment.OnRecipieStepClickListener {
    Intent intent;
    ListItems data;
    RecipieFragment recipieFragment;
    Bundle bundle;
    int positionClicked=0;
    DescriptionFragment descriptionFragment;
    MediaPlayerFragment mediaPlayerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie);

        intent = getIntent();
        if(intent!=null){
            data = (ListItems) intent.getSerializableExtra(RECIPIE_BY_NAME);
            setTitle(data.getName());
        }
        recipieFragment = new RecipieFragment();
        bundle=new Bundle();
        bundle.putSerializable(RECIPIE_BY_NAME,data);
        if(findViewById(R.id.doublePane_recipie) != null){
            descriptionFragment=new DescriptionFragment();
            mediaPlayerFragment=new MediaPlayerFragment();
//          bundle.putSerializable(RECIPIE_BY_NAME,data);
            recipieFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.steps, recipieFragment).commit();

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
        }
        else {
            bundle.putString(SINGLE_PANE,SINGLE_PANE);
            recipieFragment.setArguments(bundle);
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().add(R.id.steps, recipieFragment).commit();
        }


//        MainViewModelFactory factory = new MainViewModelFactory(this,data);
//        final ViewModelMain viewModel = ViewModelProviders.of(this, factory).get(ViewModelMain.class);



    }

    @Override
    public void OnRecipieStepSelected(int position) {

        if(position!=0){
            positionClicked=position;
            int numOfSteps= data.getSteps().size();
            replaceDescAndMediaFragments(numOfSteps,position);
        }
    }

    private void replaceDescAndMediaFragments(int numOfSteps,int positionClicked) {
        descriptionFragment=new DescriptionFragment();
        mediaPlayerFragment=new MediaPlayerFragment();
        bundle=new Bundle();
        Bundle bundleForMP=new Bundle();
        if(positionClicked< numOfSteps){

        }else{
            positionClicked=1;
        }

        String desc=data.getSteps().get(positionClicked).getDescription();
        bundle.putString(DESCRIPTION,desc);
        bundle.putInt(POSITION_CLICKED,positionClicked);

        String url=data.getSteps().get(positionClicked).getVideoURL();
        bundleForMP.putString(VIDEO_URL,url);
        bundleForMP.putInt(POSITION_CLICKED,positionClicked);
        descriptionFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.description, descriptionFragment).commit();

        mediaPlayerFragment.setArguments(bundleForMP);
        getSupportFragmentManager().beginTransaction().replace(R.id.mediaPlayer, mediaPlayerFragment).commit();
    }


}
