package com.nikitha.android.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetprovider extends AppWidgetProvider {
    private static Context context;
    private static RemoteViews views;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        CharSequence nutellapie = context.getString(R.string.nutellapie);
        // Construct the RemoteViews object
        views = new RemoteViews(context.getPackageName(), R.layout.widget_item_layout);
        views.setTextViewText(R.id.appwidget_text, nutellapie);
        views.setImageViewResource(R.id.appwidget_image, R.drawable.nutellapie);

        onclickWidgetOpenApp();
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    private static void onclickWidgetOpenApp() {
        Intent intent=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);

        views.setOnClickPendingIntent(R.id.appwidget_image,pendingIntent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        this.context=context;
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}