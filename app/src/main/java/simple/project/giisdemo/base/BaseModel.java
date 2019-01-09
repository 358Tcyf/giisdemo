package simple.project.giisdemo.base;

import android.content.Context;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:53
 * @describe
 */
public abstract class BaseModel {
    private Context context;

    public abstract void init();

    public void setContext(Context context){
        this.context=context;
        init();
    }

    public Context getContext() {
        return context;
    }
}
