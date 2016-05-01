package com.hainiu.campuslife.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by haijun on 2016/5/1.
 */
public class LocalCacheUtils {

    public static void savePictureToLocal(Context context, Bitmap pic, String url) {
        final String urlfile = Md5Utils.getMd5Digest(url);
        final String cachePath = context.getCacheDir()+"/campuslife/cache";

        File cachefolderfile = new File(cachePath);
        if (!cachefolderfile.exists()) {
            cachefolderfile.mkdirs();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(cachefolderfile, urlfile));
            pic.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap getPictureFromLocal(Context context,String url){
        Bitmap bitmap=null;

        final String SdCardPath = context.getCacheDir()+"/campuslife/cache";

        String cachefolder = SdCardPath+"/MyNewsApp/Cache";
        final String urlfile = Md5Utils.getMd5Digest(url);

        File image = new File(cachefolder+"/"+urlfile);
        if (!image.exists()){
            return  null;
        }
        bitmap = BitmapFactory.decodeFile(cachefolder+"/"+urlfile);
        return  bitmap;
    }
}
