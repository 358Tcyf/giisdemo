package simple.project.giisdemo.fragment.main;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import simple.project.giisdemo.R;
import simple.project.giisdemo.adapter.PushAdapter;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.data.DatabaseHelper;
import simple.project.giisdemo.data.entity.Push;
import simple.project.giisdemo.fragment.main.push.SearchPushFragment;
import simple.project.giisdemo.mvp.presenter.main.PushPresenter;
import simple.project.giisdemo.mvp.view.main.PushView;

import static simple.project.giisdemo.helper.constant.DateConstant.format1;
import static simple.project.giisdemo.helper.constant.HttpConstant.HTTP;
import static simple.project.giisdemo.helper.utils.DateUtil.strToLong;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PushFragmentCollapsing extends BaseFragment<PushPresenter> implements PushView, BGABanner.Adapter<ImageView, String> {


    @BindView(R.id.collapsing_topbar_layout)
    QMUICollapsingTopBarLayout mCollapsingTopBarLayout;
    /*    @BindView(R.id.banner)
        BannerView banner;*/
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.banner_content)
    BGABanner mContentBanner;


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
        urls.add(HTTP + "/ad/week");
        urls.add(HTTP + "/ad/month2");
/*        //解耦
        banner.loadData(urls).display();//构建者模式返回对象本身
        banner.setBannerClicklistener(pos -> {
            switch (pos) {
                default:
            }
        });*/
        // 设置网络数据源
//        mContentBanner.setAdapter((BGABanner.Adapter<ImageView, String>) (banner, itemView, model, position) ->
//                Glide.with(getBaseFragmentActivity())
//                        .load(model)
//                        .into(itemView));
//        mContentBanner.setData(urls, null);
        mContentBanner.setAdapter(PushFragmentCollapsing.this);
        // 设置图片数据源
        mContentBanner.setData(R.mipmap.weekly,
                R.mipmap.monthly);

    }


    private ArrayList<Push> list = new ArrayList<>();


    private void initList() {
        list = getPresenter().setDataList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseFragmentActivity());
        mRecyclerView.setLayoutManager(layoutManager);


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
//                mAdapter.setNewData(list);
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


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {

    }
}
