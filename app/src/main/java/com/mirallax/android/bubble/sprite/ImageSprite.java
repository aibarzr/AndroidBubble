package com.mirallax.android.bubble.sprite;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.Vector;

public class ImageSprite extends Sprite {
    private BmpWrap image;

    public ImageSprite(Rect area, BmpWrap image) {
        super(area);

        this.image = image;
    }

    public void saveState(Bundle map, ArrayList savedSprites) {
        if (getSavedId() != -1) {
            return;
        }
        super.saveState(map, savedSprites);
        map.putInt(String.format("%d-imageId", getSavedId()), image.id);
    }

    public int getTypeId() {
        return Sprite.TYPE_IMAGE;
    }

    public void changeImage(BmpWrap image) {
        this.image = image;
    }

    public final void paint(Canvas c, double scale, int dx, int dy) {
        Point point = getSpritePosition();
        drawImage(image, point.x, point.y, c, scale, dx, dy);
    }
}
