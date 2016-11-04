package com.mirallax.android.bubble;

import android.graphics.Canvas;
import android.os.Bundle;

import com.mirallax.android.bubble.sprite.BmpWrap;

public class Compressor {
  private BmpWrap compressorHead;
  private BmpWrap compressor;
  int steps;

  public Compressor(BmpWrap compressorHead, BmpWrap compressor)
  {
    this.compressorHead = compressorHead;
    this.compressor = compressor;
    this.steps = 0;
  }

  public void saveState(Bundle map)
  {
    map.putInt("compressor-steps", steps);
  }

  public void restoreState(Bundle map)
  {
    steps = map.getInt("compressor-steps");
  }

  public void moveDown() {
    steps++;
  }

  public void paint(Canvas c, double scale, int dx, int dy)
  {
    for (int i = 0; i < steps; i++) {
      c.drawBitmap(compressor.bmp,
                   (float)(235 * scale + dx),
                   (float)((28 * i - 4) * scale + dy),
                   null);
      c.drawBitmap(compressor.bmp,
                   (float)(391 * scale + dx),
                   (float)((28 * i - 4) * scale + dy),
                   null);
    }
    c.drawBitmap(compressorHead.bmp,
                 (float)(160 * scale + dx),
                 (float)((-7 + 28 * steps) * scale + dy),
                 null);
  }
};
