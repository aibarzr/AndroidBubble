package com.mirallax.android.bubble.sprite;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.Locale;

public abstract class Sprite {
    public static int TYPE_BUBBLE = 1;
    public static int TYPE_IMAGE = 2;

    private Rect spriteArea;
    private int saved_id;

    public Sprite(Rect spriteArea) {
        this.spriteArea = spriteArea;
        saved_id = -1;
    }

    public void saveState(Bundle map, ArrayList<Sprite> saved_sprites) {
        if (saved_id != -1) {
            return;
        }
        saved_id = saved_sprites.size();
        saved_sprites.add(this);
        map.putInt(String.format(Locale.getDefault(), "%d-left", saved_id), spriteArea.left);
        map.putInt(String.format(Locale.getDefault(), "%d-right", saved_id), spriteArea.right);
        map.putInt(String.format(Locale.getDefault(), "%d-top", saved_id), spriteArea.top);
        map.putInt(String.format(Locale.getDefault(), "%d-bottom", saved_id), spriteArea.bottom);
        map.putInt(String.format(Locale.getDefault(), "%d-type", saved_id), getTypeId());
    }

    public final int getSavedId() {
        return saved_id;
    }

    public final void clearSavedId() {
        saved_id = -1;
    }

    public abstract int getTypeId();

    public final void absoluteMove(Point point) {
        spriteArea = new Rect(spriteArea);
        spriteArea.offsetTo(point.x, point.y);
    }

    public final Point getSpritePosition() {
        return new Point(spriteArea.left, spriteArea.top);
    }

    public final Rect getSpriteArea() {
        return spriteArea;
    }

    public static void drawImage(BmpWrap image, int x, int y,
                                 Canvas c, double scale, int dx, int dy) {
        c.drawBitmap(image.bmp, (float) (x * scale + dx), (float) (y * scale + dy),
                null);
    }


    public abstract void paint(Canvas c, double scale, int dx, int dy);
}
