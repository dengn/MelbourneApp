package com.melbournestore.utils;

import android.content.Context;
import android.graphics.Bitmap;

public class BitmapUtils {
	
	//Í¼Æ¬Ëõ·Å
	public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context)
	{
	                                                                                                                                                                                                        
	    final float densityMultiplier = context.getResources().getDisplayMetrics().density;
	                                                                                                                                                                                                        
	    int h = (int) (newHeight * densityMultiplier);
	    int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));
	                                                                                                                                                                                                        
	    photo = Bitmap.createScaledBitmap(photo, w, h, true);
	                                                                                                                                                                                                        
	    return photo;
	}

}
