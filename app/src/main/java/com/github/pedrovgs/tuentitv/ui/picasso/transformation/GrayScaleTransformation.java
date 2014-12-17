package com.github.pedrovgs.tuentitv.ui.picasso.transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import static android.graphics.Bitmap.createBitmap;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.graphics.Shader.TileMode.REPEAT;

/**
 * Picasso Transformation to change images color to grey scale.
 *
 * @author Pedro Vicente Gómez Sánchez <pgomez@tuenti.com>
 */
public class GrayScaleTransformation implements Transformation {
  private final Picasso picasso;

  public GrayScaleTransformation(Picasso picasso) {
    this.picasso = picasso;
  }

  @Override public Bitmap transform(Bitmap source) {
    Bitmap result = createBitmap(source.getWidth(), source.getHeight(), source.getConfig());

    BitmapShader shader = new BitmapShader(result, REPEAT, REPEAT);

    ColorMatrix colorMatrix = new ColorMatrix();
    colorMatrix.setSaturation(0);
    ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

    Paint paint = new Paint(ANTI_ALIAS_FLAG);
    paint.setColorFilter(filter);

    Canvas canvas = new Canvas(result);
    canvas.drawBitmap(source, 0, 0, paint);

    paint.setColorFilter(null);
    paint.setShader(shader);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));

    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

    source.recycle();
    return result;
  }

  @Override public String key() {
    return "grayscaleTransformation()";
  }
}