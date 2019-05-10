package com.zkch.bugly.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.zkch.bugly.R;
import com.zkch.bugly.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewsFragment extends BaseFragment {

    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    /**
     * protected  修饰指继承该类可执行方法
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void init() {
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);
        tablayout.addTab(tablayout.newTab().setText(R.string.top));
        tablayout.addTab(tablayout.newTab().setText(R.string.nba));
        tablayout.addTab(tablayout.newTab().setText(R.string.cars));
        tablayout.addTab(tablayout.newTab().setText(R.string.jokes));
        tablayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter =new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP),getString(R.string.top));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA), getString(R.string.nba));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS), getString(R.string.cars));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES), getString(R.string.jokes));
        viewPager.setAdapter(adapter);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> mFragments =new ArrayList<>();
        List<String> mFragmentTitles =new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        private void addFragment(Fragment fragment, String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
