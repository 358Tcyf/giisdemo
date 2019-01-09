package simple.project.giisdemo.helper.http;

/**
 * @author Created by ys
 * @date at 2019/1/8 1:15
 * @describe
 */
public interface OnHttpCallBack<T> {
    void onSuccess(T t);

    void onFailed(String errorMsg);
}