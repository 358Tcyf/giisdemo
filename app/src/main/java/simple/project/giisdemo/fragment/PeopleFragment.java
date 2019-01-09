package simple.project.giisdemo.fragment;

import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.mvp.presenter.PeoplePresenter;
import simple.project.giisdemo.mvp.view.PeopleView;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PeopleFragment extends BaseFragment<PeoplePresenter> implements PeopleView {


    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_people, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected PeoplePresenter createPresenter() {
        return new PeoplePresenter();
    }


}
