package com.nikitha.android.bakingapp.room;

import com.google.gson.reflect.TypeToken;
import com.nikitha.android.bakingapp.pojo.IngredientItems;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.nikitha.android.bakingapp.pojo.StepItems;

import androidx.room.TypeConverter;

public class DataConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromIngredientItemsList(List<IngredientItems> IngredientItemsValues) {
        if (IngredientItemsValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<IngredientItems>>() {
        }.getType();
        String json = gson.toJson(IngredientItemsValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public ArrayList<IngredientItems> toIngredientItemsList(String IngredientItemsString) {
        if (IngredientItemsString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<IngredientItems>>() {
        }.getType();
        ArrayList<IngredientItems> productCategoriesList = gson.fromJson(IngredientItemsString, type);
        return productCategoriesList;
    }

    @TypeConverter // note this annotation
    public String fromStepItemsList(List<StepItems> stepItemsValues) {
        if (stepItemsValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StepItems>>() {
        }.getType();
        String json = gson.toJson(stepItemsValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public ArrayList<StepItems> toStepItemsList(String stepItemsString) {
        if (stepItemsString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StepItems>>() {
        }.getType();
        ArrayList<StepItems> stepItemsList = gson.fromJson(stepItemsString, type);
        return stepItemsList;
    }

}