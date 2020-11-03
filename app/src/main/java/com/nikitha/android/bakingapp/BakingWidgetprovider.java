package com.nikitha.android.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.nikitha.android.bakingapp.widgets.StackWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetprovider extends AppWidgetProvider {
    private static Context context;
    private static RemoteViews views;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        CharSequence brownies = context.getString(R.string.brownies);
        // Construct the RemoteViews object
        views = new RemoteViews(context.getPackageName(), R.layout.baking_widgetprovider);
        views.setTextViewText(R.id.appwidget_text, brownies);
        views.setImageViewResource(R.id.appwidget_image, R.drawable.brownies);

        onclickWidgetOpenApp();

        // Set up the intent that starts the StackViewService, which will provide the views for this collection.
        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        // Instantiate the RemoteViews object for the app widget layout.
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.baking_widgetprovider);
        // Set up the RemoteViews object to use a RemoteViews adapter.This adapter connects to a RemoteViewsService  through the specified intent.This is how you populate the data.
        rv.setRemoteAdapter(R.id.stack_view, intent);
        // The empty view is displayed when the collection has no items.It should be in the same layout used to instantiate the RemoteViews object above.
        rv.setEmptyView(R.id.stack_view, R.id.empty_view);

        // Instruct the widget manager to update the widget
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