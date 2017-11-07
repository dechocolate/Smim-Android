package com.smim.smimandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.smim.smimandroid.Const;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




/**
 * Created by kakoapps on 2016-05-04.
 */
public class PhotoUtils implements Const {


    private static final int MAX_RESOLUTION = 1000;
    private static final int MAX_IMAGE_SIZE = 5120;

    /**
     * Uri 로부터 비트맵 옵션 가져오기
     * @param context
     * @param uri
     * @return
     */
    public static BitmapFactory.Options getBitmapFactoryFromUri(Context context, Uri uri) {
        InputStream input = null;
        try {
            input = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;// optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return onlyBoundsOptions;
    }


    /**
     * 비율 가져오기
     * @param onlyBoundsOptions
     * @return
     */
    public static double getRatio(BitmapFactory.Options onlyBoundsOptions) {
        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight
                : onlyBoundsOptions.outWidth;
        double ratio = (originalSize > MAX_RESOLUTION) ? (originalSize / MAX_RESOLUTION)
                : 1.0;
        return ratio;
    }


    /**
     * 비율에 맞게 리사이즈 하여 비트맵 가져오기
     * @param uri
     * @param ratio
     * @return
     */
    public static Bitmap getResizeBitmapFromUriWithRatio(Context context, Uri uri, double ratio) {
        InputStream input = null;
        try {
            input = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;// optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional

        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 샘플 비율 가져오기
     * @param ratio
     * @return
     */
    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0)
            return 1;
        else
            return k;
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
     * Bitmap이미지의 가로, 세로 사이즈를 리사이징 한다.
     *
     * @param source        원본 Bitmap 객체
     * @param maxResolution 제한 해상도
     * @return 리사이즈된 이미지 Bitmap 객체
     */
    public static Bitmap resizeBitmapImage(Bitmap source, int maxResolution) {
        int width = source.getWidth();
        int height = source.getHeight();
        int newWidth = width;
        int newHeight = height;

//        maxResolution = IMAGE_HEIGHT;
        float rate = 0.0f;

        if (width > height) {
            if (maxResolution < width) {
                rate = maxResolution / (float) width;
                newHeight = (int) (height * rate);
                newWidth = maxResolution;
//                newHeight = 680;
//                newWidth = 800;
            }
        } else {
            if (maxResolution < height) {
                rate = maxResolution / (float) height;
                newWidth = (int) (width * rate);
                newHeight = maxResolution;

//                newHeight = 800;
//                newWidth = 680;
            }
        }

        return Bitmap.createScaledBitmap(source, newWidth, newHeight, true);
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
     * 실제 경로 찾기
     * @param uri
     * @return
     */
    public static String getPath(Activity activity, Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    // 파일명 찾기
    public static String getName(Activity activity, Uri uri)
    {
        String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    // uri 아이디 찾기
    public static String getUriId(Activity activity, Uri uri)
    {
        String[] projection = { MediaStore.Images.ImageColumns._ID };
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    /**
     * Uri를 이용하여 이미지 경로 가져오기
     */
    public static String getPhotoUri(Activity activity, Uri uri){
        String imageDataPath = "";
        String[] proj = {MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE};
        Cursor imageCursor = activity.managedQuery(uri, proj, null, null, null);

//                Cursor imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        proj, "_ID='"+ thumbID +"'", null, null);
        if (imageCursor != null && imageCursor.moveToFirst()){
            if (imageCursor.getCount() > 0){
                int imgData = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                imageDataPath = imageCursor.getString(imgData);
            }
        }

        return imageDataPath;
    }

    /**
     * 최근 사진촬영한 이미지 가져오기
     */
    public static Uri getNewPhotoUri(Context context){
        Uri uri =null;
        String[] IMAGE_PROJECTION = {
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns._ID,
        };

        try {
            Cursor cursorImages = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    IMAGE_PROJECTION, null, null,null);
            if (cursorImages != null && cursorImages.moveToLast()) {
                uri = Uri.parse(cursorImages.getString(0)); //경로
                int id = cursorImages.getInt(1); //아이디
                cursorImages.close(); // 커서 사용이 끝나면 꼭 닫아준다.
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return uri;
    }


    /**
     * 사진 크롭하기
     * @param bitmap
     * @param filePath
     */
    private void storeCropImage(Context context, Bitmap bitmap, String filePath) {
        File copyFile = new File(filePath);
        BufferedOutputStream out = null;

        try {

            copyFile.createNewFile();

            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

            int file_size = Integer
                    .parseInt(String.valueOf(copyFile.length() / 1024));
            if (file_size > MAX_IMAGE_SIZE) {
//                mCallback.onFailedSelectImage(
//                        PHOTO_SELECT_FAILED_BY_SIZE_LIMIT,
//                        mActivity.getResources().getString(
//                                R.string.image_max_size_limit_msg));
                if (copyFile != null && copyFile.exists()) {
                    copyFile.delete();
                }
            }

            out.flush();
            out.close();

//            mCallback.onSelectImageDone(bitmap, copyFile);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("PhotoUtils", "Sorry, Camera Crashed in createNewFile");
            Toast.makeText(context, "사진을 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show();

//            try{
//                mCallback.onFailedSelectImage(PHOTO_SELECT_FAILED_BY_CRASH,  e.toString());
//                mUploadImage = null;
//                if (copyFile != null && copyFile.exists()) {
//                    copyFile.delete();
//                }
//            }catch (Exception e2){
//                e2.printStackTrace();
//            }

        }
    }


    /**
     * 비트맵을 파일을 저장
     * @param bitmap
     * @param filepath
     */
    public static File saveBitmapToFile(Bitmap bitmap, String filepath) {

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
     * 파일 복사
     * @param file
     * @param save_file
     * @return
     */
    private static boolean copyFile(File file , String save_file){
        boolean result;
        if(file!=null&&file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream newfos = new FileOutputStream(save_file);
                int readcount=0;
                byte[] buffer = new byte[1024];
                while((readcount = fis.read(buffer,0,1024))!= -1){
                    newfos.write(buffer,0,readcount);
                }
                newfos.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            result = true;
        }else{
            result = false;
        }
        return result;
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
