package com.example.caleb.bakeit.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Widget service?
 */

public class BakeItWidgetService extends RemoteViewsService {

    private static final String LOG_TAG = BakeItWidgetService.class.getSimpleName();


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakeItWidgetAdapter(this);
    }
}
