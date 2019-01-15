package simple.project.giisdemo.fragment.main;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.BannerView;
import simple.project.giisdemo.mvp.presenter.main.PushPresenter;
import simple.project.giisdemo.mvp.view.main.PushView;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initTitle;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PushFragment extends BaseFragment<PushPresenter> implements PushView {

    @BindView(R.id.banner)
    BannerView banner;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_push, null);
        unbinder = ButterKnife.bind(this, view);
        initTitle(mTopBar, getBaseFragmentActivity(), R.string.push);
        initSearchIcon();
        initBanner();
        return view;
    }

    @Override
    protected PushPresenter createPresenter() {
        return new PushPresenter();
    }

    @SuppressLint("ResourceAsColor")
    private void initSearchIcon() {
        mTopBar.addRightImageButton(R.drawable.ic_search, R.id.search).setOnClickListener(v -> {
            toSearch();
        });

    }


    private void initBanner() {

        List<String> urls = new ArrayList<>();
        urls.add("http://bugdoggy.com:7171/file/company_ad1");
        urls.add("http://bugdoggy.com:7171/file/company_ad2");
        //解耦
        banner.loadData(urls).display();//构建者模式返回对象本身
        banner.setBannerClicklistener(pos -> {

        });
    }


    @Override
    public void toSearch() {
        startFragment(new SearchPushFragment());
    }
}
