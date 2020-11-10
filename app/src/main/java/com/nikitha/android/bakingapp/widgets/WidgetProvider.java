package com.nikitha.android.bakingapp.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.nikitha.android.bakingapp.MainActivity;
import com.nikitha.android.bakingapp.R;
import com.nikitha.android.bakingapp.RecipieActivity;

import static com.nikitha.android.bakingapp.CONSTANTS.EXTRA_ID;
import static com.nikitha.android.bakingapp.CONSTANTS.INVALID_ID;
import static com.nikitha.android.bakingapp.CONSTANTS.RECIPIE_BY_NAME;
import static com.nikitha.android.bakingapp.MainActivity.DATA;

public class WidgetProvider extends AppWidgetProvider {

    // setImageViewResource to update the widget’s image
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int imgRes, int appWidgetId,long  plantId, boolean showWater) {

        Bundle options=appWidgetManager.getAppWidgetOptions(appWidgetId);
        int width=options.getInt(appWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        RemoteViews rv;
        if(width<300){
            rv=getSinglePlantRemoteView(context,imgRes,plantId,showWater);
        }else{
            rv=getGardenGridRemoteView(context);
        }

        // Create an Intent to launch MainActivity when clicked

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }

    /**
     * Creates and returns the RemoteViews to be displayed in the GridView mode widget
     *
     * @param context The context
     * @return The RemoteViews for the GridView mode widget
     */
    private static RemoteViews getGardenGridRemoteView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widgetprovider);
        // Set the GridWidgetService intent to act as the adapter for the GridView
        Intent intent = new Intent(context, GridWidgetService.class);
        views.setRemoteAdapter(R.id.widget_grid_view, intent);


        // Set the PlantDetailActivity intent to launch when clicked
        Intent appIntent = new Intent(context, RecipieActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_grid_view, appPendingIntent);
        // Handle empty gardens
        views.setEmptyView(R.id.widget_grid_view, R.id.empty_view);
        return views;
    }

    private static RemoteViews getSinglePlantRemoteView(Context context, int imgRes, long recipieId, boolean showWater) {
        Intent intent;
        if(recipieId== INVALID_ID)
            intent = new Intent(context, MainActivity.class);
        else {
            Log.d(WidgetProvider.class.getSimpleName(), "recipieId=" + recipieId);
            intent = new Intent(context, RecipieActivity.class);
            intent.putExtra(RECIPIE_BY_NAME, DATA.get((int)recipieId-1));
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item_layout);
        // Update image
        views.setImageViewResource(R.id.appwidget_image, imgRes);
        views.setTextViewText(R.id.appwidget_text, DATA.get((int)recipieId-1).getName());

        // Widgets allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.appwidget_image, pendingIntent);
        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Start the intent service update widget action, the service takes care of updating the widgets UI
        PlantWateringService.startActionUpdatePlantWidgets(context);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        PlantWateringService.startActionUpdatePlantWidgets(context);
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    public static void updatePlantWidgets(Context context, AppWidgetManager appWidgetManager,
                                          int imgRes, int[] appWidgetIds,Long plantId , boolean showWater) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, imgRes, appWidgetId,plantId,showWater);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // Perform any action when one or more AppWidget instances have been deleted
    }

    @Override
    public void onEnabled(Context context) {
        // Perform any action when an AppWidget for this provider is instantiated
    }

    @Override
    public void onDisabled(Context context) {
        // Perform any action when the last AppWidget instance for this provider is deleted
    }

}

