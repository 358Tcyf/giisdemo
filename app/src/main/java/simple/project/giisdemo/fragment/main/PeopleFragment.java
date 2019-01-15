package simple.project.giisdemo.fragment.main;

import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.mvp.presenter.main.PeoplePresenter;
import simple.project.giisdemo.mvp.view.main.PeopleView;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initTitle;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PeopleFragment extends BaseFragment<PeoplePresenter> implements PeopleView {


    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_people, null);
        unbinder = ButterKnife.bind(this, view);
        initTitle(mTopBar, getBaseFragmentActivity(), R.string.people);
        return view;
    }

    @Override
    protected PeoplePresenter createPresenter() {
        return new PeoplePresenter();
    }


}
