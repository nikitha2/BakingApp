package com.nikitha.android.bakingapp;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.Dimension;

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
    public static int calcNumOfGrids(){

        int width = getScreenWidth();
        return width;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

}
