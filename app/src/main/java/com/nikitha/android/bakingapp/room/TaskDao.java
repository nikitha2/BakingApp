package com.nikitha.android.bakingapp.room;

import com.nikitha.android.bakingapp.pojo.ListItems;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM RecipieCards ORDER BY name")
    LiveData<List<ListItems>> loadAllRecipies();

    @Query("SELECT * FROM RecipieCards WHERE name=:name")
    LiveData<ListItems> loadTaskByName(int name);

    @Insert
    void insertTask(ListItems taskEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(ListItems taskEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTasks(ArrayList<ListItems> taskEntry);

    @Delete
    void deleteTask(ListItems taskEntry);

    @Insert
    void insertTasks(ArrayList<ListItems> taskEntry);
}
