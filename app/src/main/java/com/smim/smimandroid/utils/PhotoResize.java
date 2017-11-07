package com.smim.smimandroid.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import com.smim.smimandroid.Const;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by kakoapps on 2017-01-09.
 */

public class PhotoResize implements Const {

    public static final int MAX_IMAGE_WIDTH = 640;
    public static final int MAX_IMAGE_HEIGHT = 360;

    public static final int MAX_PROFILE_IMAGE_WIDTH = 500;
    public static final int MAX_PROFILE_IMAGE_HEIGHT = 500;


    /**
     * http 통신을 이용하여 bitmap 가져오기
     * @param src
     * @return
     */
    public static Bitmap getBitmapFromURL(String src) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(src);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            if(connection!=null)connection.disconnect();
        }
    }

    /**
     * 디코딩없이 비트맵 width 구하기
     * @param fileName
     * @return
     */
    public static int getBitmapOfWidth( String fileName ){
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, options);
            return options.outWidth;
        } catch(Exception e) {
            return 0;
        }
    }

    /**
     * 디코딩없이 비트맵 height 구하기
     * @param fileName
     * @return
     */
    public static int getBitmapOfHeight( String fileName ){

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, options);

            return options.outHeight;
        } catch(Exception e) {
            return 0;
        }
    }



    /*
     * 비트맵(Bitmap) 이미지의 가로, 세로 이미지를 리사이징
     * @param bmpSource 원본 Bitmap 객체
     * @param maxResolution 제한 해상도
     * @return 리사이즈된 이미지 Bitmap 객체
    */
    public static Bitmap resizeBitmapImageFn(Bitmap bmpSource, int maxResolution) {
        int iWidth = bmpSource.getWidth();      //비트맵이미지의 넓이
        int iHeight = bmpSource.getHeight();     //비트맵이미지의 높이
        int newWidth = iWidth;
        int newHeight = iHeight;
        float rate = 0.0f;

        //이미지의 가로 세로 비율에 맞게 조절
        if (iWidth > iHeight) {
            if (maxResolution < iWidth) {
                rate = maxResolution / (float) iWidth;
                newHeight = (int) (iHeight * rate);
                newWidth = maxResolution;
            }
        } else {
            if (maxResolution < iHeight) {
                rate = maxResolution / (float) iHeight;
                newWidth = (int) (iWidth * rate);
                newHeight = maxResolution;
            }
        }

        return Bitmap.createScaledBitmap(bmpSource, newWidth, newHeight, true);
    }


    /**
     * inSampleSize 값 구하기
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }


    /**
     * Resource에서 비트맵구하기
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    /**
     * SD카드에있는 이미지 경로를 받아 비트맵으로 리사이징 하여 가져온다.
     * @param photourl
     * @return
     */
    public static Bitmap getSamplesizeBitmapFromSdcard(String photourl){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photourl, options);

        int scale = 1;
        // IF IMAGE IS BIGGER THAN MAX, DOWN SAMPLING

        if (options.outHeight > MAX_IMAGE_HEIGHT || options.outWidth > MAX_IMAGE_WIDTH) {
            scale = calculateInSampleSize(options, MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT);
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;

        return BitmapFactory.decodeFile(photourl, options);
    }


    /**
     * SD카드에있는 이미지 경로를 받아 비트맵으로 리사이징 하여 가져온다.
     * @param photourl
     * @return
     */
    public static Bitmap getProfileBitmapFromSdcard(String photourl){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photourl, options);

        int scale = 1;
        // IF IMAGE IS BIGGER THAN MAX, DOWN SAMPLING

        if (options.outHeight > MAX_PROFILE_IMAGE_HEIGHT || options.outWidth > MAX_PROFILE_IMAGE_WIDTH) {
            scale = calculateInSampleSize(options, MAX_PROFILE_IMAGE_WIDTH, MAX_PROFILE_IMAGE_HEIGHT);
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;

        return BitmapFactory.decodeFile(photourl, options);
    }


    /**
     * 비트맵 회전
     * @param bitmap
     * @param filename
     * @return
     */
    public static Bitmap checkOrientationAndRotate(Bitmap bitmap, String filename) {
        ExifInterface ei = null;
        int orientation = -1;
        try {
            ei = new ExifInterface(filename);
            orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                bitmap = rotateImage(bitmap, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                bitmap = rotateImage(bitmap, 180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                bitmap = rotateImage(bitmap, 270);
                break;
        }
        return bitmap;
    }


    /**
     * 회전 함수
     * @param source
     * @param angle
     * @return
     */
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
                source.getHeight(), matrix, true);
    }



    /**
     * 비트맵을 파일을 저장
     * @param bitmap
     */
    public static File saveBitmapToFile(Bitmap bitmap) {

        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();
        String filename = ts + ".png";

        File folder = new File(FILE_PATH);
        folder.mkdirs();

        OutputStream fOut = null;
//        File fileOrigin = new File(filepath);
//
//        copyFile(fileOrigin, folder.toString()+filename);

        File fileTmp = new File(folder.toString(), filename);

        try {
            fOut = new FileOutputStream(fileTmp);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);

        try {
            fOut.flush();
            fOut.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return fileTmp;
    }


    /**
     * 종훈추가 함수
     * 비트맵 리사이즈 사이즈 지정
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap.getWidth() != width || bitmap.getHeight() != height){
            float ratio = 1.0f;

            if (width > height) {
                ratio = (float)width / (float)bitmap.getWidth();
            } else {
                ratio = (float)height / (float)bitmap.getHeight();
            }

            bitmap = Bitmap.createScaledBitmap(bitmap,
                    (int)(((float)bitmap.getWidth()) * ratio), // Width
                    (int)(((float)bitmap.getHeight()) * ratio), // Height
                    false);
        }
        return bitmap;
    }

    /**
     * delete 파일
     */
    public static void deletePhotoFile(String path){
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

}
