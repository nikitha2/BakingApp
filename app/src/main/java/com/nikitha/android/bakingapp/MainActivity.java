package com.nikitha.android.bakingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.nikitha.android.bakingapp.arch.MainViewModelFactory;
import com.nikitha.android.bakingapp.arch.ViewModelMain;
import com.nikitha.android.bakingapp.pojo.ListItems;
import java.util.ArrayList;
import java.util.List;

import static com.nikitha.android.bakingapp.CONSTANTS.RECIPIE_BY_NAME;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<ListItems>>, MainAdaptor.ListItemClickListener {
    MainAdaptor mainAdaptor;
    ArrayList<ListItems> data= new ArrayList<ListItems>();
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recipieNamesList);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mainAdaptor=new MainAdaptor(this,new ArrayList< ListItems>(),this);
        recyclerView.setAdapter(mainAdaptor);
        LoaderManager.getInstance(this).initLoader(1, null, this).forceLoad();
    }

    /*private void setupViewModel(ArrayList<ListItems> data) {
        MainViewModelFactory factory = new MainViewModelFactory(this,data);
        final ViewModelMain viewModel = ViewModelProviders.of(this, factory).get(ViewModelMain.class);
    }*/

    @NonNull
    @Override
    public Loader<ArrayList<ListItems>> onCreateLoader(int id, @Nullable Bundle args) {
        return new JSONListLoader(MainActivity.this,args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<ListItems>> loader, ArrayList<ListItems> data) {
        if(data!=null && data.size()!=0){
            mainAdaptor.setData(data);
            this.data=data;
            //setupViewModel(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<ListItems>> loader) {
       // mainAdaptor.clear();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent=new Intent(this,RecipieActivity.class);
        intent.putExtra(RECIPIE_BY_NAME,data.get(position));
        startActivity(intent);
    }
}