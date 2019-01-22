package simple.project.giisdemo.fragment.main;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import simple.project.giisdemo.R;
import simple.project.giisdemo.adapter.PushAdapter;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.fragment.main.push.SearchPushFragment;
import simple.project.giisdemo.helper.custom.BannerView;
import simple.project.giisdemo.mvp.presenter.main.PushPresenter;
import simple.project.giisdemo.mvp.view.main.PushView;
import simple.project.giisdemo.testThing.Status;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initTitle;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PushFragmentCollsping extends BaseFragment<PushPresenter> implements PushView {


    @BindView(R.id.collapsing_topbar_layout)
    QMUICollapsingTopBarLayout mCollapsingTopBarLayout;
    @BindView(R.id.banner)
    BannerView banner;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_push_collasping, null);
        ButterKnife.bind(this, view);
        initBanner();
        initList();
        return view;
    }

    @Override
    protected boolean translucentFull() {
        return true;
    }

    @Override
    protected PushPresenter createPresenter() {
        return new PushPresenter();
    }

    private void initBanner() {
        List<String> urls = new ArrayList<>();
        urls.add("http://bugdoggy.com:7171/ad/company_ad1");
        urls.add("http://bugdoggy.com:7171/ad/company_ad2");
        //解耦
        banner.loadData(urls).display();//构建者模式返回对象本身
        banner.setBannerClicklistener(pos -> {
            switch (pos) {
                default:
            }
        });
    }

    String[] names = {"Tom", "Jerry", "Bob"};
    String[] texts = {"阿斯蒂芬", "去啊这些", "你就会被"};
    String[] dates = {"04/06/13", "04/06/13", "04/06/13"};
    ArrayList<Status> list = new ArrayList<>();

    private void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseFragmentActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        for (int pos = 0; pos < names.length * 3; pos++) {
            Status item = new Status();
            item.setUserName(names[pos / 3]);
            item.setText(texts[pos / 3]);
            item.setCreatedAt(dates[pos / 3]);
            list.add(item);
        }

        mSwipeRefreshLayout.setColorSchemeColors(QMUIResHelper
                .getAttrColor(getBaseFragmentActivity(), R.attr.colorPrimary))
        ;
        initAdapter();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setNewData(list);
            }, 1000);

        });
    }

    private PushAdapter mAdapter;

    private void initAdapter() {
        mAdapter = new PushAdapter(getBaseFragmentActivity(), list);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void toSearch() {
        startFragment(new SearchPushFragment());
    }

}