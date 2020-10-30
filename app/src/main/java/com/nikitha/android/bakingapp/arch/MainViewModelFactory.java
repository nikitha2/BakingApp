package com.nikitha.android.bakingapp.arch;

import android.content.Context;

import com.nikitha.android.bakingapp.pojo.ListItems;

import java.util.ArrayList;

import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    Context context;
    ArrayList<ListItems> data;
    public MainViewModelFactory(Context context, ArrayList<ListItems> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public <T extends androidx.lifecycle.ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ViewModelMain(context, data);
    }
}
