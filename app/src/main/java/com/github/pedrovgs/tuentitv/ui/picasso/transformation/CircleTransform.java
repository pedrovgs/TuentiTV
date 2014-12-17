package com.github.pedrovgs.tuentitv.ui.picasso.transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation {

  private final int size;

  public CircleTransform(float size) {
    this.size = (int) size;
  }

  @Override public Bitmap transform(Bitmap source) {

    int x = (source.getWidth() - size) / 2;
    int y = (source.getHeight() - size) / 2;

    //Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
    Bitmap squaredBitmap = Bitmap.createScaledBitmap(source, size, size, false);
    if (squaredBitmap != source) {
      source.recycle();
    }
    Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint();
    BitmapShader shader =
        new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
    paint.setShader(shader);
    paint.setAntiAlias(true);

    float r = size / 2f;
    canvas.drawCircle(r, r, r, paint);

    squaredBitmap.recycle();
    return bitmap;
  }

  @Override public String key() {
    return "CircleTransformation";
  }
}