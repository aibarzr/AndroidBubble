package com.mirallax.android.bubble.sprite;

import android.graphics.Bitmap;

// Various classes take arguments of this type during construction.  We need
// one level of indirection in case we want to swap the images from under them
// (e.g., to resize them when the surface resolution changes.)  We couldn't
// do it if references to Bitmap were kept directly everywhere since you can't
// overwrite an Android Bitmap in place (or at least I haven't found how to
// do it.)
public class BmpWrap {
  public BmpWrap(int id)
  {
    this.id = id;
  }

  public Bitmap bmp;
  // Image id used for saving and restoring the image sprites.
  public int id;
}
