package simple.project.giisdemo.fragment.main;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import simple.project.giisdemo.R;
import simple.project.giisdemo.adapter.PushAdapter;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.data.entity.Push;
import simple.project.giisdemo.fragment.main.push.SearchPushFragment;
import simple.project.giisdemo.mvp.presenter.main.PushPresenter;
import simple.project.giisdemo.mvp.view.main.PushView;

import static simple.project.giisdemo.helper.constant.GlobalField.DOWN_SYNC_PUSH;
import static simple.project.giisdemo.helper.constant.GlobalField.UP_SYNC_PUSH;
import static simple.project.giisdemo.helper.constant.HttpConstant.HTTP;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PushCollapsingFragment extends BaseFragment<PushPresenter> implements PushView, BGABanner.Adapter<ImageView, String> {


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
    @BindView(R.id.push_count)
    TextView pushCount;


    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_push_collasping, null);
        ButterKnife.bind(this, view);
        initBanner();
        getPresenter().getCount();
        initList();
        return view;
    }

    @OnClick({R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more:
                new QMUIBottomSheet.BottomListSheetBuilder(getCurContext())
                        .addItem(getCurContext().getResources().getString(R.string.up_sync_push))
                        .addItem(getCurContext().getResources().getString(R.string.down_sync_push))
                        .addItem(getCurContext().getResources().getString(R.string.cancel))
                        .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                            dialog.dismiss();
                            switch (position) {
                                case UP_SYNC_PUSH:
                                    getPresenter().upSync();
                                    break;
                                case DOWN_SYNC_PUSH:
                                    getPresenter().downSync();
                                    break;
                                default:
                            }
                        })
                        .build()
                        .show();
                break;
        }
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
        mContentBanner.setAdapter(PushCollapsingFragment.this);
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

    private void initAdapter() {
        PushAdapter mAdapter = new PushAdapter(getBaseFragmentActivity(), list);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void toSearch() {
        startFragment(new SearchPushFragment());
    }

    @Override
    public void setCount(int count) {
        pushCount.setText("共 " + count + " 条");
    }


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {

    }


}
