package com.nikitha.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import com.nikitha.android.bakingapp.arch.MainViewModelFactory;
import com.nikitha.android.bakingapp.arch.ViewModelMain;
import com.nikitha.android.bakingapp.fragments.RecipieFragment;
import com.nikitha.android.bakingapp.pojo.ListItems;

import java.io.Serializable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;

import static com.nikitha.android.bakingapp.CONSTANTS.RECIPIE_BY_NAME;

public class RecipieActivity extends AppCompatActivity {
    Intent intent;
    ListItems data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie);

        intent = getIntent();
        if(intent!=null){
            data = (ListItems) intent.getSerializableExtra(RECIPIE_BY_NAME);
            setTitle(data.getName());
        }

        if (findViewById(R.id.singlePane_recipie) != null) {
            RecipieFragment recipieFragment = new RecipieFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            Bundle bundle=new Bundle();
            bundle.putSerializable(RECIPIE_BY_NAME,data);
            recipieFragment.setArguments(bundle);

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().add(R.id.singlePane_recipie, recipieFragment).commit();
        }


//        MainViewModelFactory factory = new MainViewModelFactory(this,data);
//        final ViewModelMain viewModel = ViewModelProviders.of(this, factory).get(ViewModelMain.class);



    }
}
