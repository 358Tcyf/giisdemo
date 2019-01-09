package simple.project.giisdemo.helper.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Created by ys
 * @date at 2019/1/8 1:17
 * @describe
 */
public class RetrofitUtils {
    private RetrofitUtils() {
    }

    public static Retrofit newInstance(String url) {
        Retrofit mRetrofit;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit;
    }
}
