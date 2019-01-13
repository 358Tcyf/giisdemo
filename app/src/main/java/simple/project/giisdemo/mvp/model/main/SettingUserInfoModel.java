package simple.project.giisdemo.mvp.model.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

import simple.project.giisdemo.base.BaseModel;

import static simple.project.giisdemo.helper.utils.FileUtil.saveImageToGallery;

/**
 * @author Created by ys
 * @date at 2019/1/13 0:14
 * @describe
 */
public class SettingUserInfoModel extends BaseModel {
    @Override
    public void init() {

    }

    public void savePic(Uri uri) {
        Bitmap bmp = null;
        try {
            bmp = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveImageToGallery(getContext(), bmp);
    }

    public void loadPic(File pic) {
        //TODO 进行用户相片的网络通信
    }
}
