package com.nikitha.android.bakingapp;

import android.content.Context;
import android.os.Bundle;

import com.nikitha.android.bakingapp.pojo.IngredientItems;
import com.nikitha.android.bakingapp.pojo.ListItems;
import com.nikitha.android.bakingapp.pojo.StepItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class JSONListLoader extends AsyncTaskLoader<ArrayList<ListItems>> {
    String jsonResponse;
    Bundle args;
    ArrayList<ListItems> data=new ArrayList<ListItems>();

    public JSONListLoader(Context context, Bundle args) {
        super(context);
        this.args=args;
    }

    @Nullable
    @Override
    public ArrayList<ListItems> loadInBackground() {
        // as JSON is already provide we do not have to make a network call.
        jsonResponse = getJsonFromAssets(getContext(),"baking.json");

        try{
            data= extractFeatureFromJson(jsonResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;


    }

    static String getJsonFromAssets(Context context, String fileName) {
        StringBuilder jsonResponse= new StringBuilder();

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readLine = bufferedReader.readLine();
            while(readLine!=null){
                jsonResponse.append(readLine);
                readLine = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return jsonResponse.toString();
    }

    private ArrayList<ListItems> extractFeatureFromJson(String jsonResponse) throws JSONException, IOException {
        ArrayList<ListItems> extractedDataArray=new ArrayList<ListItems>();
        ListItems listItems=new ListItems();
        JSONArray arrayOfItems = new JSONArray(jsonResponse);
        int length = arrayOfItems.length();
        for(int i=0;i<length;i++){
            int id =0;
            String name=null;
            ArrayList<IngredientItems> ingredientsList = new ArrayList<>();
            ArrayList<StepItems> stepsList = new ArrayList<>();
            JSONObject items= arrayOfItems.getJSONObject(i);

                if(items.has("id")) {
                    id = items.getInt("id");
                }
                if(items.has("name")) {
                   name=items.getString("name");
                }
                if(items.has("ingredients")) {
                    JSONArray ingredients = items.getJSONArray("ingredients");
                    int numOfIngredeients= ingredients.length();
                    for(int j=0;j<numOfIngredeients;j++) {
                        JSONObject ingredianti = ingredients.getJSONObject(j);
                        int quantity = 0;
                        String measure = null;
                        String ingredient = null;
                        if (ingredianti.has("quantity")) {
                            quantity = ingredianti.getInt("quantity");
                        }
                        if (ingredianti.has("measure")) {
                            measure = ingredianti.getString("measure");
                        }
                        if (ingredianti.has("ingredient")) {
                            ingredient = ingredianti.getString("ingredient");
                        }
                        ingredientsList.add(new IngredientItems(quantity, measure, ingredient));
                    }
                }
                if(items.has("steps")) {
                    JSONArray steps = items.getJSONArray("steps");
                    for (int j = 0; j < steps.length(); j++) {
                        JSONObject stepsi = steps.getJSONObject(j);
                        int id1 = 0;
                        String shortDescription = null;
                        String description = null;
                        String videoURL = null;
                        String thumbnailURL = null;
                        if (stepsi.has("id1")) {
                            id1 = stepsi.getInt("id1");
                        }
                        if (stepsi.has("shortDescription")) {
                            shortDescription = stepsi.getString("shortDescription");
                        }
                        if (stepsi.has("description")) {
                            description = stepsi.getString("description");
                        }
                        if (stepsi.has("videoURL")) {
                            videoURL = stepsi.getString("videoURL");
                        }
                        if (stepsi.has("thumbnailURL")) {
                            thumbnailURL = stepsi.getString("thumbnailURL");
                        }
                        stepsList.add(new StepItems(id1, shortDescription, description, videoURL, thumbnailURL));
                    }
                }


            extractedDataArray.add(new ListItems( name,ingredientsList,stepsList));
        }
        return extractedDataArray;
    }
}

