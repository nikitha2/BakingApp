package com.nikitha.android.bakingapp;

import android.content.res.Resources;
import android.widget.ImageView;

import com.nikitha.android.bakingapp.pojo.ListItems;

public class CONSTANTS {

    public final static String RECIPIE_BY_NAME="SELECTED_RECIPIE_NAME";
    public final static String POSITION_CLICKED="POSITION";
    public final static String VIDEO_URL="VIDEO_URL";
    public final static String DESCRIPTION="DESCRIPTION";
    public final static String EMPTY_STRING="";
    public final static String SINGLE_PANE="SINGLE_PANE";
    public final static String STATE_RESUME_WINDOW = "resumeWindow";
    public final static String STATE_RESUME_POSITION = "resumePosition";
    public final static String STATE_PLAYER_FULLSCREEN = "playerFullscreen";
    public final static int COUNT = 4;
    public static final String TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";
    public static final long INVALID_ID = -1;
    public static final String EXTRA_ID = "com.example.android.baking.extra.ID";
    public static int RECIPIE_ID=1;

    public static int calcNumOfGrids(){
        int width = getScreenWidth();
        return width;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getImageForName(String name) {
        switch (name) {
            case "Nutella Pie":
                return (R.drawable.nutellapie);
            case "Brownies":
                return (R.drawable.brownies);
            case "Yellow Cake":
                return (R.drawable.yellowcake);
            case "Cheesecake":
                return (R.drawable.cheesecake);
            default:

        }
        return 0;
    }

}
