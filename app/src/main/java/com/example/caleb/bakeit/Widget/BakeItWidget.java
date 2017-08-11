package com.example.caleb.bakeit.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.ui.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class BakeItWidget extends AppWidgetProvider {

    public static final String EXTRA_LABEL = "what";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bake_it_widget);

        // Set the BakeItWidgetAdapter on the widget
        // Like a normal adapter, it defines the views to display
        // And adds the proper information to them
        Intent widgetIntent = new Intent(context, BakeItWidgetService.class);
        views.setRemoteAdapter(R.id.ingredients_widget, widgetIntent);

        views.setEmptyView(R.id.ingredients_widget, R.id.empty_widget_view);


        // Instruct the widget manager to update the widget
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.ingredients_widget);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
            ComponentName widgetComponent = new ComponentName(context, BakeItWidget.class);

            widgetManager.notifyAppWidgetViewDataChanged(widgetManager.getAppWidgetIds(widgetComponent), R.id.ingredients_widget);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bake_it_widget);
            Intent appIntent = new Intent(context, MainActivity.class);
            PendingIntent startAppIntent = PendingIntent.getActivity(context, 0, appIntent, 0);

            views.setPendingIntentTemplate(R.id.ingredients_widget, startAppIntent);
            views.setViewVisibility(R.id.empty_widget_view, View.GONE);
            appWidgetManager.updateAppWidget(appWidgetIds, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bake_it_widget);
        views.setEmptyView(R.id.ingredients_widget, R.id.empty_widget_view);
        views.setViewVisibility(R.id.empty_widget_view, View.VISIBLE);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

