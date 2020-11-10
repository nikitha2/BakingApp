package com.nikitha.android.bakingapp.widgets;

/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import com.nikitha.android.bakingapp.R;
import com.nikitha.android.bakingapp.pojo.ListItems;
import static com.nikitha.android.bakingapp.CONSTANTS.COUNT;
import static com.nikitha.android.bakingapp.CONSTANTS.RECIPIE_ID;
import static com.nikitha.android.bakingapp.CONSTANTS.EXTRA_ID;
import static com.nikitha.android.bakingapp.CONSTANTS.INVALID_ID;
import static com.nikitha.android.bakingapp.CONSTANTS.getImageForName;
import static com.nikitha.android.bakingapp.MainActivity.DATA;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class PlantWateringService extends IntentService {
    Long plantId;
    public static final String ACTION_WATER_PLANT = "com.example.android.mygarden.action.water_plant";
    public static final String ACTION_UPDATE_PLANT_WIDGETS = "com.example.android.mygarden.action.update_plant_widgets";
    public PlantWateringService() {
        super("PlantWateringService");
    }

    /**
     * Starts this service to perform WaterPlants action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionWaterPlants(Context context) {
        Intent intent = new Intent(context, PlantWateringService.class);
        intent.setAction(ACTION_WATER_PLANT);
        context.startService(intent);
    }

    /**
     * Starts this service to perform UpdatePlantWidgets action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdatePlantWidgets(Context context) {
        Intent intent = new Intent(context, PlantWateringService.class);
        intent.setAction(ACTION_UPDATE_PLANT_WIDGETS);
        context.startService(intent);
    }

    /**
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            long plantId = intent.getLongExtra(EXTRA_ID,1);
            if (ACTION_WATER_PLANT.equals(action)) {
                //handleActionWaterPlant(plantId);
                handleActionUpdatePlantWidgets(plantId);
            } else if (ACTION_UPDATE_PLANT_WIDGETS.equals(action)) {
                handleActionUpdatePlantWidgets(plantId);
            }
        }
    }

    /**
     * Handle action WaterPlant in the provided background thread with the provided
     * parameters.
     */
    private void handleActionWaterPlant(long plantId) {
       /* Uri SINGLE_PLANT_URI = ContentUris.withAppendedId(
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLANTS).build(), plantId);
        ContentValues contentValues = new ContentValues();
        long timeNow = System.currentTimeMillis();
        contentValues.put(PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME, timeNow);
        // Update only if that plant is still alive
        getContentResolver().update(
                SINGLE_PLANT_URI,
                contentValues,
                PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME + ">?",
                new String[]{String.valueOf(timeNow - PlantUtils.MAX_AGE_WITHOUT_WATER)});
        // Always update widgets after watering plants
        startActionUpdatePlantWidgets(this);*/
    }


    /**
     * Handle action UpdatePlantWidgets in the provided background thread
     */
    private void handleActionUpdatePlantWidgets(long plantId) {
        //Query to get the plant that's most in need for water (last watered)

        ListItems cursor = null;
        for(int i = 1; i<=COUNT; i++){
            if((int)plantId ==i){
                cursor=DATA.get(i-1);
                break;
            }
        }
        // Extract the plant details
        int imgRes= R.drawable.nutellapie; // Default image in case our garden is empty
        boolean canWater = false; // Default to hide the water drop button
        if (cursor != null ) {
            String name = cursor.getName();
            imgRes= getImageForName(name);
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, WidgetProvider.class));
        //Now update all widgets
        WidgetProvider.updatePlantWidgets(this, appWidgetManager, imgRes, appWidgetIds,plantId,canWater);
    }
}
