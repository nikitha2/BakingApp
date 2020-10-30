package com.nikitha.android.bakingapp.arch;

import android.content.Context;
import android.os.Bundle;

import com.nikitha.android.bakingapp.pojo.ListItems;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

public class ViewModelMain  extends androidx.lifecycle.ViewModel {

    LiveData<List<ListItems>> listLiveData;
    Repository repository;
    public ViewModelMain(Context context, ArrayList<ListItems> data) {
        repository = new Repository(context);
        repository.insertTasks(data);
        listLiveData = repository.loadAllRecipies();
    }

    public LiveData<List<ListItems>> loadAllRecipies(){
        return listLiveData;
    }

    public void insertTasks(ArrayList<ListItems> taskEntry){
        repository.insertTasks(taskEntry);
    }

}
