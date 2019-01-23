package simple.project.giisdemo.helper.http;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import simple.project.giisdemo.data.bean.UserBean;


/**
 * @author Created by ys
 * @date at 2019/1/8 1:21
 * @describe
 */
public interface HttpContract {
    @POST("/user/login")
    Observable<RetResult<UserBean>> login(@Query("phone") String phone, @Query("password") String password);

    @POST("/user/signUp")
    Observable<RetResult<UserBean>> register(@Query("name") String name, @Query("phone") String phone, @Query("password") String password);

    @POST("/user/updateInfo")
    Observable<RetResult> updateInfo(@Query("oldPhone") String oldPhone, @Query("newPhone") String newPhone, @Query("password") String password);

    @POST("file/upload")
    @Multipart
    Observable<RetResult> upload(@Part MultipartBody.Part file, @Query("phone") String phone);

    @POST("file/downloadPic")
    @Multipart
    Observable<RetResult> downloadPic(@Query("phone") String phone);
}
