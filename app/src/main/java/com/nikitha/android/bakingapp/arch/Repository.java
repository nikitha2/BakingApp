package com.nikitha.android.bakingapp.arch;

import android.content.Context;

import com.nikitha.android.bakingapp.pojo.ListItems;
import com.nikitha.android.bakingapp.room.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import androidx.lifecycle.LiveData;

public class Repository {
    Context context;
    AppDatabase database;
    ExecutorService executor;

    public Repository(Context context) {
        this.context=context;
        database = AppDatabase.getInstance(context);
        executor = MyApplication.getInstance().executorService;

    }

    public void updateTasks(final ArrayList<ListItems> listItems) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.taskDao().updateTasks(listItems);
            }
        });
    }

    public void insertTasks(final ArrayList<ListItems> taskEntry) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.taskDao().insertTasks(taskEntry);
            }
        });
    }

    public LiveData<List<ListItems>> loadAllRecipies(){
       return database.taskDao().loadAllRecipies();
    }
}
