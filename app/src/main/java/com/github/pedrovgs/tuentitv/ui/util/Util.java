package com.github.pedrovgs.tuentitv.ui.util;

import android.content.Context;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Util {

  public static int convertDpToPixel(Context context, int dp) {
    float density = context.getResources().getDisplayMetrics().density;
    return Math.round((float) dp * density);
  }
}
