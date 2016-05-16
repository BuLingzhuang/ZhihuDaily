package com.blz.zhihudaily.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by BuLingzhuang
 * on 2016/5/16
 * E-mail bulingzhuang@foxmail.com
 */
public class CircleTransform implements Transformation{
    @Override
    public Bitmap transform(Bitmap source) {
        int minSize = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - minSize) / 2;
        int y = (source.getHeight() - minSize) / 2;
        Bitmap squareBitmap = Bitmap.createBitmap(source, x, y, minSize, minSize);
        if (squareBitmap != source){
            source.recycle();
        }
        Bitmap bitmap = Bitmap.createBitmap(minSize, minSize, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squareBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = minSize / 2f;
        canvas.drawCircle(r,r,r,paint);

        squareBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
