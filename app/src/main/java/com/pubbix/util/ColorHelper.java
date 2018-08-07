package com.pubbix.util;

import com.pubbix.R;

public class ColorHelper {
    private static int[] categoryNameColors = new int[]{
            R.color.red_500,
            R.color.deep_purple_500,
            R.color.light_blue_500,
            R.color.green_500,
            R.color.yellow_800,
            R.color.deep_orange_500,
            R.color.blue_grey_500,
            R.color.indigo_500,
            R.color.pink_500,
            R.color.cyan_500,
            R.color.amber_500,
            R.color.brown_500,
            R.color.purple_500,
            R.color.blue_500,
            R.color.teal_500,
            R.color.lime_500,
            R.color.orange_500
    };

    public static int getRandomCategoryNameColor(int position) {
        return categoryNameColors[position % categoryNameColors.length];
    }
}
