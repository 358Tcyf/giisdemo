package simple.project.giisdemo.helper.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;

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
        String fileName = SPUtils.get(context, USER_PHONE, "default") + "_pic.png";
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

    public static String getUserPicPath(Context context) {
        String picPath = getFilePath() + "/" + SPUtils.get(context, USER_PHONE, "default") + "_pic.png";
        return picPath;
    }

    public static Uri getUserPicPathUri(Context context) {
        String picPath = getFilePath() + "/" + SPUtils.get(context, USER_PHONE, "default") + "_pic.png";
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
}
