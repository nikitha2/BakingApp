package com.nikitha.android.bakingapp;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.Dimension;

public class CONSTANTS {

    public static String RECIPIE_BY_NAME="SELECTED_RECIPIE_NAME";
    public static String POSITION_CLICKED="POSITION";
    public static String VIDEO_URL="VIDEO_URL";
    public static String DESCRIPTION="DESCRIPTION";
    public static String EMPTY_STRING="";
    public static String SINGLE_PANE="SINGLE_PANE";

    public static int calcNumOfGrids(){

        int width = getScreenWidth();
        return width;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

}
