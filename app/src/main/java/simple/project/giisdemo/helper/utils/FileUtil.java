package simple.project.giisdemo.helper.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.GET_NEW_APK;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_UID;

/**
 * @author Created by ys
 * @date at 2019/1/13 17:15
 * @describe
 */
public class FileUtil {
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "HelloGlass");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String picPace = appDir.getName();
        String fileName = SPUtils.get(context, USER_UID, "default") + "_pic.png";
        Log.d(DEBUG, "FileUtil: filename is " + fileName);
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));

    }

    public final static String getFilePath() {
        File appDir = new File(Environment.getExternalStorageDirectory(), "HelloGlass");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String filePath = appDir.toString();
        return filePath;
    }


    public static Uri getUserPicPathUri(Context context) {
        String picPath = getFilePath() + "/" + SPUtils.get(context, USER_UID, "default") + "_pic.png";
        if (existPath(picPath))
            return Uri.parse(picPath);
        else return null;
    }


    /**
     * 判断文件是否存在
     */
    private static Boolean existPath(String existPath) {
        File file = new File(existPath);
        return file.exists();

    }

    public static void saveImageToGallery(Context context, String uri) {
        new Thread() {
            @Override
            public void run() {
                //这里写入子线程需要做的工作
                try {
                    URL url = new URL(uri);
                    Log.d(DEBUG, "FileUtil: saveImageToGallery: url is " + url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();

                    InputStream input = connection.getInputStream();
                    Bitmap bmp = BitmapFactory.decodeStream(input);
                    saveImageToGallery(context, bmp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void downloadApk(Context context) {
        //下载路径，如果路径无效了，可换成你的下载路径
        String url = GET_NEW_APK;
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir("", "glass.apk");
        //获取下载管理器
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        downloadManager.enqueue(request);

    }
}
