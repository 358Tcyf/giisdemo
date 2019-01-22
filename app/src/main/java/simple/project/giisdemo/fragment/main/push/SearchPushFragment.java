package simple.project.giisdemo.fragment.main.push;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.mvp.presenter.main.SearchPushPresenter;
import simple.project.giisdemo.mvp.view.main.SearchPushView;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBack;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackName;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initSearchBar;

/**
 * @author Created by ys
 * @date at 2019/1/9 17:29
 * @describe
 */
public class SearchPushFragment extends BaseFragment<SearchPushPresenter> implements SearchPushView {

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    private Unbinder unbinder;


    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_search, null);
        unbinder = ButterKnife.bind(this, view);
        initBack(mTopBar, getBaseFragmentActivity());
        initSearchBar(mTopBar, getBaseFragmentActivity());
        return view;
    }


    @Override
    protected SearchPushPresenter createPresenter() {
        return null;
    }

}
