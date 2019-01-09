package simple.project.giisdemo.fragment;

import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.BannerView;
import simple.project.giisdemo.helper.utils.ToastUtil;
import simple.project.giisdemo.mvp.presenter.PushPresenter;
import simple.project.giisdemo.mvp.view.PushView;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PushFragment extends BaseFragment<PushPresenter> implements PushView {

    @BindView(R.id.banner)
    BannerView banner;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_push, null);
        unbinder = ButterKnife.bind(this, view);
        initBanner();
        return view;
    }

    @Override
    protected PushPresenter createPresenter() {
        return new PushPresenter();
    }



    private void initBanner() {

        List<String> urls = new ArrayList<>();
        urls.add("http://i2.bvimg.com/674115/addb8bf7318ec6c3.jpg");
        urls.add("http://i2.bvimg.com/674115/e3d0c9bbe4eda843t.jpg");
        urls.add("http://i2.bvimg.com/674115/819f73dc4ec87a7et.jpg");
        //解耦
        banner.loadData(urls).display();//构建者模式返回对象本身
        banner.setBannerClicklistener(new BannerView.BannerClicklistener() {
            @Override
            public void onClickListener(int pos) {

            }
        });
    }


}
