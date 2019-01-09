package simple.project.giisdemo.fragment;

import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.mvp.presenter.SearchPushPresenter;
import simple.project.giisdemo.mvp.view.SearchPushView;

/**
 * @author Created by ys
 * @date at 2019/1/9 17:29
 * @describe
 */
public class SearchPushFragment extends BaseFragment<SearchPushPresenter> implements SearchPushView {

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_search, null);
        unbinder = ButterKnife.bind(this, view);
        initSearchBar();
        return view;
    }

    private void initSearchBar() {
        SearchView searchView = (SearchView) LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.search_bar, null);
        topbar.addRightView(searchView, R.id.search);

    }

    @Override
    protected SearchPushPresenter createPresenter() {
        return null;
    }

}
