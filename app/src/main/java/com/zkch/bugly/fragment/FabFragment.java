package com.zkch.bugly.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
import com.zkch.bugly.R;
import com.zkch.bugly.base.BaseFragment;
import butterknife.BindView;

public class FabFragment extends BaseFragment {
    @BindView(R.id.rv_ctas)
    RecyclerView rvCtas;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fabMenu)
    FABRevealMenu fabMenu;


    /**
     * protected  修饰指继承该类可执行方法
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fab;
    }

    @Override
    protected void init() {

        if (fab != null && fabMenu != null) {
            fabMenu.bindAncherView(fab);
        }

        fabMenu.setOnFABMenuSelectedListener(view -> {
            int id = (int) view.getTag();
            switch (id) {
                case R.id.action_bank:

                    break;
                case R.id.action_email:

                    break;
                case R.id.action_social_network:

                    break;
                case R.id.action_ecommerce:

                    break;
                case R.id.action_others:

                    break;
            }
        });
    }



}
