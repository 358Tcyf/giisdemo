package simple.project.giisdemo.mvp.model.main.setting;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.IOException;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.helper.http.HttpContract;
import simple.project.giisdemo.helper.http.HttpFeedBackUtil;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.http.RetrofitUtils;
import simple.project.giisdemo.helper.utils.DialogUtil;
import simple.project.giisdemo.helper.utils.MediaUtil;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_UID;
import static simple.project.giisdemo.helper.constant.HttpConstant.PORT;
import static simple.project.giisdemo.helper.constant.HttpConstant.URL;
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


    public void uploadHeadImage(Uri uri, OnHttpCallBack<RetResult> callBack) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),
                Objects.requireNonNull(MediaUtil.getByteFromFile(MediaUtil.getFileFromMediaUri(getContext(), uri))));
        String filename = SPUtils.get(getContext(), USER_UID, "default") + "_pic.png";
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", filename, requestFile);
        QMUITipDialog loading = DialogUtil.showTipDialog(getContext(), QMUITipDialog.Builder.ICON_TYPE_LOADING, "上传头像中", false);
        RetrofitUtils.newInstance(URL + PORT + "/")
                .create(HttpContract.class)
                .upload(body, (String) SPUtils.get(getContext(), USER_PHONE, ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RetResult retResult) {
                        loading.dismiss();
                        HttpFeedBackUtil.handleRetResult(retResult, callBack);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.dismiss();
                        HttpFeedBackUtil.handleException(e, callBack);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
