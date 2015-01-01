/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.tuentitv.ui.picasso.transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.squareup.picasso.Transformation;

/**
 * Picasso Transformation created to apply a circular mask to the downloaded image.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class CircleTransform implements Transformation {

  private final int size;

  public CircleTransform(float size) {
    this.size = (int) size;
  }

  @Override public Bitmap transform(Bitmap source) {
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