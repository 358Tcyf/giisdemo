package simple.project.giisdemo.fragment.main;

import android.os.Debug;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import simple.project.giisdemo.R;
import simple.project.giisdemo.adapter.PeopleAdapter;
import simple.project.giisdemo.adapter.SectionAdapter;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.data.bean.UserBean;
import simple.project.giisdemo.data.bean.UserSection;
import simple.project.giisdemo.fragment.main.push.SearchPushFragment;
import simple.project.giisdemo.mvp.presenter.main.PeoplePresenter;
import simple.project.giisdemo.mvp.view.main.PeopleView;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.HttpConstant.GET_USER_PIC;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initTitleAndBack;
import static simple.project.giisdemo.helper.utils.FileUtil.saveImageToGallery;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PeopleSectionFragment extends BaseFragment<PeoplePresenter> implements PeopleView {


    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_people, null);
        ButterKnife.bind(this, view);
        initTitleAndBack(mTopBar, getBaseFragmentActivity(), R.string.people)
                .setOnClickListener(v -> toSearch());
        getPresenter().loadUserList();
        initList();
        return view;
    }

    private void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseFragmentActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getBaseFragmentActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getCurContext(), R.drawable.custom_divider));
        mRecyclerView.addItemDecoration(divider);
//        for (int pos = 0; pos < names.length; pos++) {
//            UserBean item = new UserBean();
//            item.setName(names[pos]);
//            list.add(item);
//        }

        mSwipeRefreshLayout.setColorSchemeColors(QMUIResHelper
                .getAttrColor(getBaseFragmentActivity(), R.attr.colorPrimary));
        initAdapter();
        initRefreshLayout();

    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                mSwipeRefreshLayout.setRefreshing(false);
                getPresenter().loadUserList();
            }, 1000);

        });
    }

    private List<UserSection> data = new ArrayList<>();
    private SectionAdapter sAdapter;

    private void initAdapter() {
        sAdapter = new SectionAdapter(getCurContext(), R.layout.item_people, R.layout.item_section, data);
        sAdapter.isFirstOnly(true);
        sAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(sAdapter);
    }

    @Override
    protected PeoplePresenter createPresenter() {
        return new PeoplePresenter();
    }

    @Override
    public void toSearch() {
        startFragment(new SearchPushFragment());
    }

    @Override
    public void setUserList(Map<String, Object> result) {
        Log.d(DEBUG, result.toString());
        data.clear();
        if (result.containsKey("manager")) {
            List<UserBean> manager = JSON.parseArray(JSON.toJSONString(result.get("manager")), UserBean.class);
            data.add(new UserSection(true, "团部"));
            for (UserBean user : manager) {
                /*获取到账号信息之后，加载用户相片*/
//            saveImageToGallery(getCurContext(), GET_USER_PIC + user.getUid(),user.getUid());
                UserSection section = new UserSection(user);
                data.add(section);
            }
        }


        if (result.containsKey("produce staff")) {
            List<UserBean> produce = JSON.parseArray(JSON.toJSONString(result.get("produce staff")), UserBean.class);
            data.add(new UserSection(true, "警卫连"));
            for (UserBean user : produce) {
//            saveImageToGallery(getCurContext(), GET_USER_PIC + user.getUid(),user.getUid());
                UserSection section = new UserSection(user);
                data.add(section);
            }
        }

        if (result.containsKey("sale staff")) {
            List<UserBean> sale = JSON.parseArray(JSON.toJSONString(result.get("sale staff")), UserBean.class);
            data.add(new UserSection(true, "营部"));
            for (UserBean user : sale) {
//            saveImageToGallery(getCurContext(), GET_USER_PIC + user.getUid(),user.getUid());
                UserSection section = new UserSection(user);
                data.add(section);
            }
        }

        new MyThread().start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            getCurContext().runOnUiThread(() -> {
                sAdapter.setNewData(data);
            });
        }
    }
}
