package simple.project.giisdemo.fragment.main;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.adapter.PeopleAdapter;
import simple.project.giisdemo.adapter.PullToRefreshAdapter;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.bean.UserBean;
import simple.project.giisdemo.mvp.presenter.main.PeoplePresenter;
import simple.project.giisdemo.mvp.view.main.PeopleView;
import simple.project.giisdemo.testThing.Status;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initTitle;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PeopleFragment extends BaseFragment<PeoplePresenter> implements PeopleView {


    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_people, null);
        unbinder = ButterKnife.bind(this, view);
        initTitle(mTopBar, getBaseFragmentActivity(), R.string.people);
        initList();
        return view;
    }

    String[] names = {"Tom", "Jerry", "Bob"};
    ArrayList<UserBean> list = new ArrayList<>();

    private void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseFragmentActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getBaseFragmentActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseFragmentActivity(), R.drawable.custom_divider));
        mRecyclerView.addItemDecoration(divider);
        for (int pos = 0; pos < names.length; pos++) {
            UserBean item = new UserBean();
            item.setName(names[pos]);
            list.add(item);
        }

        mSwipeRefreshLayout.setColorSchemeColors(QMUIResHelper
                .getAttrColor(getBaseFragmentActivity(), R.attr.colorPrimary));
        initAdapter();
        initRefreshLayout();

    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.addData(list);
            }, 1000);

        });
    }

    private PeopleAdapter mAdapter;

    private void initAdapter() {
        mAdapter = new PeopleAdapter(list);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected PeoplePresenter createPresenter() {
        return new PeoplePresenter();
    }

}
