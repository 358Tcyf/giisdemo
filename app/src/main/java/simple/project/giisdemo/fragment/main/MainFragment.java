package simple.project.giisdemo.fragment.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.base.BasePresenter;

import static simple.project.giisdemo.helper.utils.FlashBarUtil.exitActivity;

/**
 * @author Created by ys
 * @date at 2019/1/8 18:28
 * @describe
 */
public class MainFragment extends BaseFragment {
    @BindView(R.id.pager)
    QMUIViewPager pager;
    @BindView(R.id.tabs)
    QMUITabSegment tabs;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        initTitles();
        initTabs();
        initPagers();
        return view;
    }

    private CharSequence[] titles;

    private void initTitles() {
        titles = new String[3];
        titles[0] = getResources().getString(R.string.title_push);
        titles[1] = getResources().getString(R.string.title_people);
        titles[2] = getResources().getString(R.string.title_setting);
    }

    @SuppressWarnings("ConstantConditions")
    private void initTabs() {
//        int normalColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_gray_6);
//        int selectColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_blue);
        int normalColor = getBaseFragmentActivity().getResources().getColor(R.color.colorPrimaryDark, null);
        int selectColor = getBaseFragmentActivity().getResources().getColor(R.color.colorPrimary, null);
        tabs.setDefaultNormalColor(normalColor);
        tabs.setDefaultSelectedColor(selectColor);

        QMUITabSegment.Tab push = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_push),
                ContextCompat.getDrawable(getContext(), R.drawable.ic_push_selected),
                titles[0], false
        );

        QMUITabSegment.Tab people = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_people),
                ContextCompat.getDrawable(getContext(), R.drawable.ic_people_selected),
                titles[1], false
        );
        QMUITabSegment.Tab setting = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_setting),
                ContextCompat.getDrawable(getContext(), R.drawable.ic_setting_selected),
                titles[2], false
        );
        tabs.addTab(push)
                .addTab(people)
                .addTab(setting);

    }

    private HashMap<Pager, BaseFragment> mPages;

    private void initPagers() {
        mPages = new HashMap<>();
        BaseFragment pushFragment = new PushFragment();
        mPages.put(Pager.PUSH, pushFragment);
        BaseFragment peopleFragment = new PeopleFragment();
        mPages.put(Pager.PEOPLE, peopleFragment);
        BaseFragment settingFragment = new SettingFragment();
        mPages.put(Pager.SETTING, settingFragment);

        FragmentPagerAdapter mPageAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mPages.get(Pager.getPagerFromPosition(position));
            }

            @Override
            public int getCount() {
                return mPages.size();
            }
        };

        pager.setAdapter(mPageAdapter);
        pager.setCurrentItem(0);
        tabs.setupWithViewPager(pager, false);

    }

    enum Pager {
        PUSH, PEOPLE, SETTING;

        public static Pager getPagerFromPosition(int position) {
            switch (position) {
                case 0:
                    return PUSH;
                case 1:
                    return PEOPLE;
                case 2:
                    return SETTING;
                default:
                    return PUSH;
            }
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    //退出时的时间
    private long mExitTime;

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getView()).setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK) {
                exit();
                return true;
            }
            return false;
        });
    }


    //退出方法
    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
//            Toast.makeText(getBaseFragmentActivity(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitActivity(getBaseFragmentActivity(), "再按一次退出应用");
            mExitTime = System.currentTimeMillis();
        } else {
            //用户退出处理
            getBaseFragmentActivity().finish();
            System.exit(0);
        }
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }

}
