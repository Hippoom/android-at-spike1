package com.deppon.app.june.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.view.View;

public class ViewUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static byte[] cut(View v,int quality){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache(true);
		Bitmap bitmap = v.getDrawingCache();
		bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);//0-100
		return baos.toByteArray();
	}
	
	public static Bitmap cut(View v){
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache();
		return v.getDrawingCache();
	}
	public static String shoot(View v,int quality){
		String filepath = "/sdcard/"+System.currentTimeMillis()+".png";
		 File file=new File(filepath);
         if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				Logger.v("failed to create file("+filepath+") ! ");
				e2.printStackTrace();
			}
		FileOutputStream imgfile = null;
		try {
			imgfile = new FileOutputStream(filepath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			Logger.v("failed to create file IO! ");
			e1.printStackTrace();
		}
		Logger.v(v.toString());
		byte[] out = ViewUtil.cut(v, quality);
		Logger.v(out.toString());
		try {
			imgfile.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.e("failed to write!", e);
		}
		
		return filepath;
	}
}
