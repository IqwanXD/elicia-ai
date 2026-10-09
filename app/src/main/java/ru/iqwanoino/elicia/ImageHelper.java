package ru.iqwanoino.elicia;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff;

public class ImageHelper {
	
	public static Bitmap cropCenter(Bitmap bitmap, int width, int height) {
		int cropWidth = Math.min(width, bitmap.getWidth());
		int cropHeight = Math.min(height, bitmap.getHeight());
		
		int x = Math.max(0, (bitmap.getWidth() - cropWidth) / 2);
		int y = Math.max(0, (bitmap.getHeight() - cropHeight) / 2);
		
		return Bitmap.createBitmap(bitmap, x, y, cropWidth, cropHeight);
	}
    
	public static Bitmap getCircularBitmap(Bitmap bitmap) {
		int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
		Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		
		Canvas canvas = new Canvas(output);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		
		float radius = size / 2f;
		canvas.drawCircle(radius, radius, radius, paint);
		
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), 
		new Rect(0, 0, size, size), paint);
		
		return output;
	}
	
	public static Bitmap cropAndCircleBitmap(Bitmap bitmap, int width, int height) {
		Bitmap croppedBitmap = cropCenter(bitmap, width, height);
		return getCircularBitmap(croppedBitmap);
	}
}
