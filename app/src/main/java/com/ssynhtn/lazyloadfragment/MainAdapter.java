package com.ssynhtn.lazyloadfragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ssynhtn.library.FragmentFactory;
import com.ssynhtn.library.LazyLoadFragment;

/**
 * Created by huangtongnao on 2019/7/16.
 * Email: huangtongnao@gmail.com
 */
public class MainAdapter extends FragmentPagerAdapter {

    private final int childCount;

    public MainAdapter(FragmentManager fm, int childCount) {
        super(fm);
        this.childCount = childCount;
    }

    @Override
    public Fragment getItem(final int position) {
        Fragment contentFragment = SimpleFragment.newInstance(position);
        if (position == 0) {
            return contentFragment;
        } else if (position == 1) {
            return LazyLoadFragment.newInstance(SimpleFragment.class, -1);
        } else if (position == 2){
            return LazyLoadFragment.newInstance(new FragmentFactory() {
                @Override
                public Fragment createFragment() {
                    return SimpleFragment.newInstance(position);
                }
            }, -1);
        } else {
            return LazyLoadFragment.newInstance(new FragmentFactory() {
                @Override
                public Fragment createFragment() {
                    return SimpleFragment.newInstance(position);
                }
            });
        }
    }

    @Override
    public int getCount() {
        return childCount;
    }
}