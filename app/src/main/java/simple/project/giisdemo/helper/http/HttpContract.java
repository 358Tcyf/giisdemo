package simple.project.giisdemo.helper.http;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;
import simple.project.giisdemo.bean.UserBean;

import static simple.project.giisdemo.helper.constant.GlobalField.USER;

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
}
